package com.minecraftplus.modLock;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntitySide extends Entity
{
	protected int tickCounter;

	public int xPosition;
	public int yPosition;
	public int zPosition;

	public EntitySide(World par1World)
	{
		super(par1World);
	}

	public EntitySide(World par1World, int par2, int par3, int par4, int par5)
	{
		this(par1World);
		this.xPosition = par2;
		this.yPosition = par3;
		this.zPosition = par4;
		this.setDirectionAndPosition(par5);
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(3, (byte) 0);
		this.dataWatcher.addObject(4, "");
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return !this.isDead ? this.boundingBox : null;
	}

	@Override
	//TODO: pushOutOfBlocks
	protected boolean func_145771_j(double par1, double par3, double par5)
	{
		return false;
	}

	public byte getDirection()
	{
		return this.dataWatcher.getWatchableObjectByte(3);
	}

	public void setDirection(int par1)
	{
		if (par1 == 0)
		{
			this.prevRotationPitch = this.rotationPitch = 90F;
		}
		else if (par1 == 1)
		{
			this.prevRotationPitch = this.rotationPitch = -90F;
		}
		else if (par1 == 2)
		{
			this.prevRotationYaw = this.rotationYaw = 180F;
		}
		else if (par1 == 3)
		{
			this.prevRotationYaw = this.rotationYaw = 0F;
		}
		else if (par1 == 4)
		{
			this.prevRotationYaw = this.rotationYaw = -90F;
		}
		else if (par1 == 5)
		{
			this.prevRotationYaw = this.rotationYaw = 90F;
		}

		if (!this.worldObj.isRemote)
		{
			this.dataWatcher.updateObject(3, (byte) par1);
		}
	}

	public void setDirectionAndPosition(int par1)
	{
		this.setDirection(par1);

		float f = (float)this.xPosition + 0.5F;
		float f1 = (float)this.yPosition + 0.5F;
		float f2 = (float)this.zPosition + 0.5F;
		float f3 = 0.5F;

		this.setPosition((double)f, (double)f1 - 0.5F, (double)f2);
		float f7 = -0.03125F;
		this.boundingBox.setBounds((double)(f - f7), (double)(f1 - f7), (double)(f2 - f7), (double)(f + f7), (double)(f1 + f7), (double)(f2 + f7));
	}

	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.tickCounter++ >= 100 && !this.worldObj.isRemote)
		{
			this.tickCounter = 0;

			if (!this.isDead && !this.onValidSurface())
			{
				this.setDead();
				this.onBroken((Entity) null);
			}
		}
	}

	/**Checks to make sure the entity can be placed there*/
	public boolean onValidSurface()
	{
		Material material = this.worldObj.getBlock(this.xPosition, this.yPosition, this.zPosition).getMaterial();

		if (material == Material.air)
		{
			return false;
		}

		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(-0.2D, -0.2D, -0.2D));
		Iterator iterator = list.iterator();
		Entity entity;
		do
		{
			if (!iterator.hasNext())
			{
				return true;
			}

			entity = (Entity)iterator.next();
		}
		while (!(entity instanceof EntityLock));

		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	@Override
	public boolean hitByEntity(Entity par1Entity)
	{
		return par1Entity instanceof EntityPlayer ? this.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)par1Entity), 0.0F) : false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (!this.isDead && !this.worldObj.isRemote)
		{
			this.setDead();
			this.setBeenAttacked();
			this.onBroken(par1DamageSource.getEntity());
		}
		return false;
	}

	@Override
	public void moveEntity(double par1, double par3, double par5)
	{
		if (!this.worldObj.isRemote && !this.isDead && par1 * par1 + par3 * par3 + par5 * par5 > 0.0D)
		{
			this.setDead();
			this.onBroken((Entity) null);
		}
	}

	@Override
	public void addVelocity(double par1, double par3, double par5)
	{
		if (!this.worldObj.isRemote && !this.isDead && par1 * par1 + par3 * par3 + par5 * par5 > 0.0D)
		{
			this.setDead();
			this.onBroken((Entity) null);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setByte("Direction", this.getDirection());
		par1NBTTagCompound.setInteger("TileX", this.xPosition);
		par1NBTTagCompound.setInteger("TileY", this.yPosition);
		par1NBTTagCompound.setInteger("TileZ", this.zPosition);
		par1NBTTagCompound.setString("CustomName", this.getCustomName());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		this.setDirection(par1NBTTagCompound.getByte("Direction"));
		this.xPosition = par1NBTTagCompound.getInteger("TileX");
		this.yPosition = par1NBTTagCompound.getInteger("TileY");
		this.zPosition = par1NBTTagCompound.getInteger("TileZ");
		if (par1NBTTagCompound.hasKey("CustomName") && par1NBTTagCompound.getString("CustomName").length() > 0)
		{
			this.setCustomName(par1NBTTagCompound.getString("CustomName"));
		}
	}

	public abstract void onBroken(Entity par1Entity);

	@Override
	public String getCommandSenderName()
	{
		return this.hasCustomName() ? this.getCustomName() : "Iron Lock";
	}

	public boolean hasCustomName()
	{
		return this.getCustomName().length() > 0;
	}

	public String getCustomName()
	{
		return this.dataWatcher.getWatchableObjectString(4);
	}

	public void setCustomName(String par1Str)
	{
		this.dataWatcher.updateObject(4, par1Str);
	}
}
