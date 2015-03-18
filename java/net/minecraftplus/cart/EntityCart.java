package net.minecraftplus.cart;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftplus.cart.cargo.Cargo;
import net.minecraftplus.cart.cargo.CargoChest;
import net.minecraftplus.cart.cargo.CargoEnderChest;
import net.minecraftplus.cart.cargo.CargoFurnace;
import net.minecraftplus.cart.cargo.CargoJukeBox;
import net.minecraftplus.cart.cargo.CargoWorkbench;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCart extends EntityPullable
{
	private Cargo theCargo;

	@SideOnly(Side.CLIENT)
	private float wheelAngle;

	public EntityCart(World par1World)
	{
		super(par1World);
	}

	public EntityCart(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(19, 0);
	}

	@Override
	public void onUpdate()
	{
		if (this.hasCargo())
		{
			this.theCargo.onUpdate();
		}

		if (this.worldObj.isRemote)
		{
			this.wheelAngle += (float) (Math.abs(this.motionX) + Math.abs(this.motionZ)) * 45F;
			this.wheelAngle %= 360;
		}

		if (this.hasCargo() && !this.theCargo.isSteerableInUse())
		{
			if (this.worldObj.isAirBlock((int) this.posX, (int) this.posY - 1, (int) this.posZ))
			{
				this.dropCargo();
			}
		}
		else
		{
			super.onUpdate();
			this.onUpdateMovement();
		}
	}

	@Override
	public void onEntityUpdate()
	{
		if (this.worldObj.isRemote)
		{
			//Update client-side
			if (!this.hasCargo() && this.getCargoID() != 0)
			{
				this.setCargo(this.getCargoByID(this.getCargoID()));
			}
		}

		super.onEntityUpdate();
	}

	public boolean isSteerable()
	{
		return this.theCargo != null ? this.theCargo.isSteerableInUse() : true;
	}

	@Override
	public boolean interactFirst(EntityPlayer par1EntityPlayer)
	{
		if (par1EntityPlayer.isSneaking() && this.hasCargo())
		{
			this.theCargo.interact(par1EntityPlayer);
			return true;
		}

		if (par1EntityPlayer.getCurrentEquippedItem() != null && !this.hasCargo())
		{
			Item item = par1EntityPlayer.getCurrentEquippedItem().getItem();

			if (item == Item.getItemFromBlock(Blocks.chest))
			{
				this.setCargo(this.getCargoByID(1));
				if (!par1EntityPlayer.capabilities.isCreativeMode && --par1EntityPlayer.getCurrentEquippedItem().stackSize <= 0)
				{
					par1EntityPlayer.destroyCurrentEquippedItem();
				}

				return true;
			}
			else if (item == Item.getItemFromBlock(Blocks.crafting_table))
			{
				this.setCargo(this.getCargoByID(2));
				if (!par1EntityPlayer.capabilities.isCreativeMode && --par1EntityPlayer.getCurrentEquippedItem().stackSize <= 0)
				{
					par1EntityPlayer.destroyCurrentEquippedItem();
				}

				return true;
			}
			else if (item == Item.getItemFromBlock(Blocks.jukebox))
			{
				this.setCargo(this.getCargoByID(3));
				if (!par1EntityPlayer.capabilities.isCreativeMode && --par1EntityPlayer.getCurrentEquippedItem().stackSize <= 0)
				{
					par1EntityPlayer.destroyCurrentEquippedItem();
				}

				return true;
			}
			else if (item == Item.getItemFromBlock(Blocks.ender_chest))
			{
				this.setCargo(this.getCargoByID(4));
				if (!par1EntityPlayer.capabilities.isCreativeMode && --par1EntityPlayer.getCurrentEquippedItem().stackSize <= 0)
				{
					par1EntityPlayer.destroyCurrentEquippedItem();
				}

				return true;
			}
			else if (item == Item.getItemFromBlock(Blocks.furnace))
			{
				this.setCargo(this.getCargoByID(5));
				if (!par1EntityPlayer.capabilities.isCreativeMode && --par1EntityPlayer.getCurrentEquippedItem().stackSize <= 0)
				{
					par1EntityPlayer.destroyCurrentEquippedItem();
				}

				return true;
			}
		}

		return super.interactFirst(par1EntityPlayer);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			if (par1DamageSource.getEntity().isSneaking())
			{
				if (this.hasCargo())
				{
					this.dropCargo();
				}
			}
			else
			{
				this.setDead();
			}
		}

		return true;
	}

	@Override
	public String getHurtSound()
	{
		return null;
	}

	@Override
	public void setDead()
	{
		if (!this.isDead)
		{
			if (this.hasCargo())
			{
				this.dropCargo();
			}

			if (!this.worldObj.isRemote)
			{
				this.entityDropItem(new ItemStack(ModCart.cart), 0F);
			}
		}
		super.setDead();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setCargoID(par1NBTTagCompound.getInteger("CargoID"));

		if (this.getCargoID() > 0)
		{
			this.setCargo(this.getCargoByID(this.getCargoID()));
			this.theCargo.readCargoFromNBT(par1NBTTagCompound);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("CargoID", this.getCargoID());
		if (this.hasCargo())
		{
			this.theCargo.writeCargoToNBT(par1NBTTagCompound);
		}
	}

	public boolean hasCargo()
	{
		if (this.worldObj.isRemote)
		{
			//Update client-side
			if (this.theCargo == null && this.getCargoID() != 0)
			{
				this.setCargo(this.getCargoByID(this.getCargoID()));
			}
		}

		return this.theCargo != null;
	}

	public Cargo getCargo()
	{
		return this.theCargo;
	}

	public IInventory getCargoInventory()
	{
		if (this.hasCargo())
		{
			return this.theCargo.getInventory();
		}

		return null;
	}

	public void setCargo(Cargo par1Cargo)
	{
		this.theCargo = par1Cargo;
		this.setCargoID(par1Cargo != null ? par1Cargo.getCargoID() : 0);

		if (par1Cargo != null)
		{
			par1Cargo.initCargo();
		}
	}

	public void dropCargo()
	{
		this.theCargo.dropDeadItems();
		this.setCargo(null);
	}

	public int getCargoID()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	public void setCargoID(int par1)
	{
		this.dataWatcher.updateObject(19, Integer.valueOf(par1));
	}

	public Cargo getCargoByID(int par1)
	{
		switch(par1)
		{
		case 1:
			return new CargoChest(this);
		case 2:
			return new CargoWorkbench(this);
		case 3:
			return new CargoJukeBox(this);
		case 4:
			return new CargoEnderChest(this);
		case 5:
			return new CargoFurnace(this);
		default:
			return null;
		}
	}

	@SideOnly(Side.CLIENT)
	public float getWheelAngle()
	{
		return this.wheelAngle;
	}
}
