package net.minecraftplus.daycounter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityDayCounter extends EntityHanging
{
	private int timeRelative;

	public EntityDayCounter(World par1World)
	{
		super(par1World);
	}

	public EntityDayCounter(World par1World, int par2, int par3, int par4, int par5)
	{
		super(par1World, par2, par3, par4, par5);
		this.setDirection(par5);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(2, (int) 0);
		this.dataWatcher.addObject(3, (int) 0);
	}

	@Override
	public int getWidthPixels()
	{
		return 9;
	}

	@Override
	public int getHeightPixels()
	{
		return 9;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		double d1 = 16.0D;
		d1 *= 64.0D * this.renderDistanceWeight;
		return par1 < d1 * d1;
	}

	@Override
	public void onBroken(Entity par1Entity)
	{
		if (!this.worldObj.isRemote && (par1Entity instanceof EntityPlayer ? !((EntityPlayer) par1Entity).capabilities.isCreativeMode : true))
		{
			ItemStack itemstack = new ItemStack(ModDayCounter.dayCounter);
			ItemDayCounter.setItemStackTime(this, itemstack);

			this.entityDropItem(itemstack, 0.0F);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("Days", this.getTimeDay());
		par1NBTTagCompound.setInteger("Eras", this.getTimeEra());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setTime(par1NBTTagCompound.getInteger("Days"), par1NBTTagCompound.getInteger("Eras"));
		this.setTimeRelative(this.getCurrentWorldTime());
	}

	@Override
	public boolean interactFirst(EntityPlayer par1EntityPlayer)
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.worldObj.isRemote)
		{
			return;
		}

		int worldTime = this.getCurrentWorldTime();

		if (worldTime > this.getTimeRelative())
		{
			for(int i = worldTime; i > this.getTimeRelative(); i--)
			{
				this.addTime(1, 0);
				this.addTimeRelative(1);
				if (this.getTimeDay() / 256 >= 1)
				{
					this.addTime(-256, 1);
				}
			}
		}
		else
		{
			if (worldTime < this.getTimeRelative())
			{
				this.setTimeRelative(worldTime);
			}
		}
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition par1MovingObjectPosition)
	{
		return new ItemStack(ModDayCounter.dayCounter);
	}

	public int getTimeDay()
	{
		return this.dataWatcher.getWatchableObjectInt(2);
	}

	public int getTimeEra()
	{
		return this.dataWatcher.getWatchableObjectInt(3);
	}

	public int getTimeRelative()
	{
		return this.timeRelative;
	}

	public void setTime(int par1, int par2)
	{
		this.dataWatcher.updateObject(2, par1);
		this.dataWatcher.updateObject(3, par2);
	}

	public void addTime(int par1, int par2)
	{
		this.dataWatcher.updateObject(2, this.getTimeDay() + par1);
		this.dataWatcher.updateObject(3, this.getTimeEra() + par2);
	}

	public void setTimeRelative(int par1)
	{
		this.timeRelative = par1;
	}

	public void addTimeRelative(int par1)
	{
		this.timeRelative += par1;
	}

	public int getCurrentWorldTime()
	{
		return (int) (this.worldObj.getWorldTime() / 24000);
	}

	public static Entity setEntityTime(EntityDayCounter par1EntityDayCounter, ItemStack par2ItemStack)
	{
		if (par2ItemStack.hasTagCompound())
		{
			par1EntityDayCounter.setTime(par2ItemStack.getTagCompound().getInteger("Days"), par2ItemStack.getTagCompound().getInteger("Eras"));
		}
		else
		{
			par1EntityDayCounter.setTime(0, 0);
		}

		par1EntityDayCounter.setTimeRelative(par1EntityDayCounter.getCurrentWorldTime());

		return par1EntityDayCounter;
	}
}