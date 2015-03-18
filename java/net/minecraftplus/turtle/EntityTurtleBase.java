package net.minecraftplus.turtle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTurtleBase extends EntityTameable
{
	private double getMoveSpeed()
	{
		return 0.6D;
	}

	private Item getTamingItem()
	{
		return Items.fish;
	}

	private Item getBreedingItem()
	{
		return Items.fish;
	}

	private boolean toggleRiding = true;

	public EntityTurtleBase(World parWorld)
	{
		super(parWorld);
		this.setSize(0.6F, 0.7F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, this.aiSit);
		this.tasks.addTask(2, new EntityAITempt(this, this.getMoveSpeed() * 1.2D, this.getTamingItem(), false));
		this.tasks.addTask(2, new EntityAITempt(this, this.getMoveSpeed() * 1.2D, this.getBreedingItem(), false));
		this.tasks.addTask(3, new EntityAIFollowOwner(this, this.getMoveSpeed(), 2.0F, 2.0F));
		this.tasks.addTask(4, new EntityAIMate(this, this.getMoveSpeed()));
		this.tasks.addTask(5, new EntityAIAvoidEntity(this, EntityPlayer.class, 8F, this.getMoveSpeed(), this.getMoveSpeed() * 1.1D));
		this.tasks.addTask(6, new EntityAIFollowParent(this, this.getMoveSpeed() * 1.1D));
		this.tasks.addTask(7, new EntityAIWander(this, this.getMoveSpeed()));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.setTamed(false);

		this.setRiding(null);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);

		if (this.isTamed())
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		}
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		//this.dataWatcher.addObject(18, new Byte((byte)0));
		//this.dataWatcher.addObject(19, new Byte((byte)0));
		this.dataWatcher.addObject(20, new Byte((byte)BlockColored.func_150032_b(1)));
	}

	protected int getCollarColor()
	{
		return this.dataWatcher.getWatchableObjectByte(20) & 15;
	}

	protected void setCollarColor(int parColor)
	{
		this.dataWatcher.updateObject(20, Byte.valueOf((byte)(parColor & 15)));
	}

	/*
	protected boolean getRiding()
	{
		return this.dataWatcher.getWatchableObjectByte(18) == (byte)1;
	}*/

	@Override
	public void writeEntityToNBT(NBTTagCompound parNBTTagCompound)
	{
		super.writeEntityToNBT(parNBTTagCompound);

		parNBTTagCompound.setByte("CollarColor", (byte)this.getCollarColor());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound parNBTTagCompound)
	{
		super.readEntityFromNBT(parNBTTagCompound);

		if (parNBTTagCompound.hasKey("CollarColor", 99))
		{
			this.setCollarColor(parNBTTagCompound.getByte("CollarColor"));
		}
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.isInWater() && !this.getRiding())
		{
			this.motionX += Math.cos(Math.toRadians((this.rotationYaw + 90D) % 360)) * 0.02D;
			this.motionY += 0.05F;
			this.motionZ += -Math.sin(Math.toRadians((this.rotationYaw - 90D) % 360)) * 0.02D;
		}

		if (this.isTamed())
		{
			if (this.getRiding())
			{
				this.rotationYaw = this.ridingEntity.rotationYaw;
				if (this.ridingEntity.isInWater())
				{
					this.ridingEntity.motionX += Math.cos(Math.toRadians((this.ridingEntity.rotationYaw + 90D) % 360)) * 0.02D;
					this.ridingEntity.motionY += this.ridingEntity.isSneaking() ? 0.01F : 0.025;
					this.ridingEntity.motionZ += -Math.sin(Math.toRadians((this.ridingEntity.rotationYaw - 90D) % 360)) * 0.02D;
				}

				if (this.toggleRiding)
				{
					this.ignoreFrustumCheck = true;
					this.setSize(0.05F, 0.05F);
					this.toggleRiding = false;
				}
			}
			else
			{
				if (!this.toggleRiding)
				{
					this.ignoreFrustumCheck = false;
					this.setSize(0.6F, 0.7F);
					this.toggleRiding = true;
				}

				if (this.isEntityInsideOpaqueBlock())
				{
					//TODO: pushOutOfBlocks
					this.func_145771_j(this.posX, this.posY, this.posZ);
					this.motionY += 0.2F;
				}
			}
		}

		if (!this.worldObj.isRemote)
		{
			if (this.rand.nextInt(900) == 0 && this.deathTime == 0)
			{
				this.heal(1.0F);
			}

			if (this.isTamed())
			{
				if (this.isInWater())
				{
					this.aiSit.setSitting(false);
				}
			}
		}
	}

	@Override
	public boolean interact(EntityPlayer parEntityPlayer)
	{
		ItemStack itemstack = parEntityPlayer.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if (itemstack != null)
			{
				if (itemstack.getItem() == Items.dye)
				{
					int i = BlockColored.func_150032_b(itemstack.getItemDamage());

					if (i != this.getCollarColor())
					{
						this.setCollarColor(i);

						if (!parEntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
						{
							parEntityPlayer.inventory.setInventorySlotContents(parEntityPlayer.inventory.currentItem, (ItemStack)null);
						}

						return true;
					}
				}
			}

			if (!parEntityPlayer.isSneaking())
			{
				if (!this.getRiding())
				{
					if (parEntityPlayer.getDistanceSqToEntity(this) < 0.8D)
					{
						this.setRiding(parEntityPlayer);
						return true;
					}
				}
				else
				{
					this.setRiding(null);
					return true;
				}
			}

			if (this.func_152114_e(parEntityPlayer) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
			{
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.setPathToEntity((PathEntity)null);
				this.setTarget((Entity)null);
				this.setAttackTarget((EntityLivingBase)null);
			}
		}
		else if (itemstack != null && itemstack.getItem() == this.getTamingItem())
		{
			if (!parEntityPlayer.capabilities.isCreativeMode)
			{
				--itemstack.stackSize;
			}

			if (itemstack.stackSize <= 0)
			{
				parEntityPlayer.inventory.setInventorySlotContents(parEntityPlayer.inventory.currentItem, (ItemStack)null);
			}

			if (!this.worldObj.isRemote)
			{
				if (this.rand.nextInt(6) == 0)
				{
					this.setTamed(true);
					this.setPathToEntity((PathEntity)null);
					this.setAttackTarget((EntityLivingBase)null);
					this.aiSit.setSitting(true);
					this.setHealth(20.0F);
					this.func_152115_b(parEntityPlayer.getUniqueID().toString());
					this.playTameEffect(true);
					this.worldObj.setEntityState(this, (byte)7);
				}
				else
				{
					this.playTameEffect(false);
					this.worldObj.setEntityState(this, (byte)6);
				}
			}

			return true;
		}

		return super.interact(parEntityPlayer);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		Entity entity = par1DamageSource.getEntity();

		if (this.ridingEntity != null && this.ridingEntity.equals(entity))
		{
			return false;
		}

		this.setSitting(false);

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public void setTamed(boolean parIsTame)
	{
		super.setTamed(parIsTame);

		if (parIsTame)
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		}
	}

	@Override
	public boolean isBreedingItem(ItemStack parItemStack)
	{
		return parItemStack == null ? false : parItemStack.getItem() == this.getBreedingItem();
	}

	@Override
	public boolean isRiding()
	{
		return false;
	}

	protected boolean getRiding()
	{
		return this.ridingEntity != null;
	}

	protected void setRiding(Entity parEntity)
	{
		if (parEntity != null && parEntity.riddenByEntity != null && parEntity.riddenByEntity instanceof EntityTurtleBase)
		{
			EntityTurtleBase entityturtle = (EntityTurtleBase) parEntity.riddenByEntity;
			entityturtle.setRiding(null);
		}

		this.mountEntity(parEntity);
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 0.2F;
	}

	@Override
	public double getYOffset()
	{
		return this.getRiding() ? this.ridingEntity.isSneaking() ? -2.35D : -2.3D : 0.0D;
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	public boolean isEntityInsideOpaqueBlock()
	{
		return this.getRiding() ? false : super.isEntityInsideOpaqueBlock();
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 8;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if (this.worldObj.rand.nextInt(4) == 0)
		{
			return false;
		}
		else
		{
			if (this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty())
			{
				int i = MathHelper.floor_double(this.posX);
				int j = MathHelper.floor_double(this.boundingBox.minY);
				int k = MathHelper.floor_double(this.posZ);

				if (j < 63)
				{
					return false;
				}

				Block block = this.worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial() == Material.grass || block.getMaterial() == Material.ground)
				{
					return true;
				}
			}

			return false;
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable parEntityAgeable)
	{
		EntityTurtleBase pigeon = new EntityTurtleBase(this.worldObj);
		String s = this.func_152113_b();

		if (s != null && s.trim().length() > 0)
		{
			pigeon.func_152115_b(s);
			pigeon.setTamed(true);
		}

		return pigeon;
	}

	@Override
	public boolean canMateWith(EntityAnimal parEntity)
	{
		if (parEntity == this)
		{
			return false;
		}
		else if (!this.isTamed())
		{
			return false;
		}
		else if (!(parEntity instanceof EntityTurtleBase))
		{
			return false;
		}
		else
		{
			EntityTurtleBase entitypigeon = (EntityTurtleBase)parEntity;
			return !entitypigeon.isTamed() ? false : (entitypigeon.isSitting() ? false : this.isInLove() && entitypigeon.isInLove());
		}
	}

	@Override
	protected boolean canDespawn()
	{
		return !this.isTamed() && this.ticksExisted > 2400;
	}
}