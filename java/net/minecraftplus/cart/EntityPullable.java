package net.minecraftplus.cart;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPullable extends EntityLivingBase
{
	private EntityLivingBase pullingEntity;
	private NBTTagCompound pullingTag;

	public EntityPullable(World par1World)
	{
		super(par1World);

		this.setSize(1.4F, 1.2F);
		this.stepHeight = 1F;
	}

	public EntityPullable(World par1World, double par2, double par4, double par6)
	{
		this(par1World);
		this.posX = par2;
		this.posY = par4;
		this.posZ = par6;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}

	public void onUpdateMovement()
	{
		this.updatePullingState();

		if (this.worldObj.isAABBInMaterial(this.boundingBox, Material.water))
		{
			this.setDead();
		}

		if (this.pullingEntity == null) return;

		if (this.pullingEntity instanceof EntityPlayer)
		{
			if (this.width != 1.4F)
			{
				this.setSize(1.4F, 0.8F);
			}
		}
		else
		{
			this.setSize(0.8F, 0.8F);
		}

		//Rotation
		this.rotationPitch = 0.0F;
		double d11 = this.prevPosX - (this.isPulling() ? this.pullingEntity.posX : this.posX);
		double d13 = this.prevPosZ - (this.isPulling() ? this.pullingEntity.posZ : this.posZ);

		if (d11 * d11 + d13 * d13 > 0.001D)
		{
			this.rotationYaw = (float)((Math.atan2(d13, d11) * 180D) / Math.PI);
		}

		this.setRotation(this.rotationYaw, this.rotationPitch);

		//Position
		double entity_x = this.pullingEntity.posX;
		double entity_y = this.pullingEntity.posY;
		double entity_z = this.pullingEntity.posZ;

		double speed = (((this.posX - entity_x) * (this.posX - entity_x) + (this.posZ - entity_z) * (this.posZ - entity_z)) - 2D - this.pullingEntity.width) / 2D;
		if (this.pullingEntity.isSprinting())
		{
			speed += 0.3D;
		}

		double d1 = this.prevPosX;
		double d2 = this.prevPosZ;
		double distx = getDistanceBetween(d1, entity_x);
		double disty = getDistanceBetween(d2, entity_z);
		double d3 = 1.0D;
		double d4 = 1.0D;

		if (distx > disty)
		{
			d4 = (disty /= distx) / 3D;
			d3 = 0.33333333333333333D;
		}
		else if (distx < disty)
		{
			d3 = (distx /= disty) / 3D;
			d4 = 0.33333333333333333D;
		}

		this.motionX = entity_x > d1 ? d3 * speed : -d3 * speed;
		this.motionZ = entity_z > d2 ? d4 * speed : -d4 * speed;
	}

	@Override
	public boolean interactFirst(EntityPlayer par1EntityPlayer)
	{
		if (par1EntityPlayer.isSneaking())
		{
			if (this.riddenByEntity != null)
			{
				Entity entity = this.riddenByEntity;

				this.riddenByEntity.mountEntity(null);
				entity.posX = this.posX;
				entity.posY = this.posY + 1F;
				entity.posZ = this.posZ;
				return true;
			}

			return false;
		}

		EntityLiving firstentity = getFirstLeashedEntity(par1EntityPlayer, this.worldObj, (int) par1EntityPlayer.posX, (int) par1EntityPlayer.posY, (int) par1EntityPlayer.posZ);

		if (firstentity != null)
		{
			if (this.riddenByEntity == null)
			{
				firstentity.clearLeashed(true, false);
				if (firstentity.height > 1.5D)
				{
					firstentity.posX = this.posX;
					firstentity.posY = this.posY + 1F;
					firstentity.posZ = this.posZ;
					this.setDead();
				}
				else
				{
					firstentity.mountEntity(this);
				}

				if (!this.worldObj.isRemote)
				{
					this.entityDropItem(new ItemStack(Items.lead), 0F);
				}
			}
		}
		else if (this.isPulling(par1EntityPlayer))
		{
			this.setPulling(null, false);
		}
		else if (this.isPulling() && this.isAnimalPulling(par1EntityPlayer))
		{
			this.setPulling(null, false);
		}
		else
		{
			this.setPulling(par1EntityPlayer, false);
		}

		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (par1DamageSource.getEntity() instanceof EntityPlayer)
		{
			this.setDead();
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("Pulling", 10))
		{
			this.pullingTag = par1NBTTagCompound.getCompoundTag("Pulling");
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		if (this.getPulling() != null)
		{
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();

			if (this.getPulling() instanceof EntityLivingBase)
			{
				nbttagcompound1.setLong("UUIDMost", this.getPulling().getUniqueID().getMostSignificantBits());
				nbttagcompound1.setLong("UUIDLeast", this.getPulling().getUniqueID().getLeastSignificantBits());
			}

			par1NBTTagCompound.setTag("Pulling", nbttagcompound1);
		}
	}

	public EntityLivingBase getPulling()
	{
		return this.pullingEntity;
	}

	public void setPulling(EntityLivingBase par1EntityLivingBase, boolean par2)
	{
		this.pullingEntity = par1EntityLivingBase;
	}

	public boolean isPulling()
	{
		return this.pullingEntity != null;
	}

	public boolean isPulling(EntityLivingBase par1EntityLivingBase)
	{
		return this.pullingEntity == par1EntityLivingBase;
	}

	public boolean isAnimalPulling(EntityLivingBase par1EntityLivingBase)
	{
		return !(this.pullingEntity instanceof EntityPlayer);
	}

	private static double getDistanceBetween(double par1, double par2)
	{
		double d = Math.abs(par1);
		double d1 = Math.abs(par2);

		if (par1 > 0.0D && par2 > 0.0D || par1 < 0.0D && par2 < 0.0D)
		{
			if (d > d1)
			{
				return d - d1;
			}
			else if (d < d1)
			{
				return d1 - d;
			}
		}
		else
		{
			return d + d1;
		}

		return 0D;
	}

	protected void updatePullingState()
	{
		if (this.pullingTag != null)
		{
			this.recreatePulling();
		}

		if (this.isPulling())
		{
			if (this.getPulling().isDead)
			{
				this.setPulling(null, false);
			}
			else if (this.getPulling().ridingEntity != null)
			{
				Entity entity = this.getPulling().ridingEntity;
				if (entity instanceof EntityLivingBase)
				{
					this.setPulling((EntityLivingBase) entity, false);
				}
			}
			else if (this.isRiding())
			{
				this.setPulling(null, false);
			}
		}
	}

	private void recreatePulling()
	{
		this.pullingTag = null;
	}

	private static EntityLiving getFirstLeashedEntity(EntityPlayer par1EntityPlayer, World par2World, int par3, int par4, int par5)
	{
		double d0 = 7D;
		List list = par2World.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox((double)par3 - d0, (double)par4 - d0, (double)par5 - d0, (double)par3 + d0, (double)par4 + d0, (double)par5 + d0));

		if (list != null)
		{
			Iterator iterator = list.iterator();

			while (iterator.hasNext())
			{
				EntityLiving entityliving = (EntityLiving)iterator.next();

				if (entityliving.getLeashed() && entityliving.getLeashedToEntity() == par1EntityPlayer)
				{
					return entityliving;
				}
			}
		}

		return null;
	}

	@Override
	public ItemStack getHeldItem()
	{
		return null;
	}

	@Override
	public ItemStack getEquipmentInSlot(int var1)
	{
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int var1, ItemStack var2) {}

	@Override
	public ItemStack[] getLastActiveItems()
	{
		return new ItemStack[0];
	}
}
