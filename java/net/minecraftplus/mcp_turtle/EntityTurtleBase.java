package net.minecraftplus.mcp_turtle;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityTurtleBase extends EntityTameable
{
	private double getMoveSpeed()
	{
		return 0.6D;
	}

	private boolean toggleRiding = true;

	public EntityTurtleBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.6F, 0.7F);
		((PathNavigateGround)this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAITempt(this, 1.2D, Items.fish, false));
		this.tasks.addTask(4, new EntityAIMate(this, this.getMoveSpeed()));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, this.getMoveSpeed(), 2.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIFollowParent(this, this.getMoveSpeed() * 1.1D));
		this.tasks.addTask(7, new EntityAIWander(this, this.getMoveSpeed()));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.setTamed(false);

		this.setRiding(null);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		//Compare to: @EntityWolf
		this.dataWatcher.addObject(20, new Byte((byte)EnumDyeColor.RED.getMetadata()));
	}

	@Override
	protected boolean canDespawn()
	{
		//Compare to: @EntityOcelot
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	@Override
	protected void applyEntityAttributes()
	{
		//Compare to: @EntityWolf
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

	public EnumDyeColor getCollarColor()
	{
		//Compare to: @EntityWolf
		return EnumDyeColor.byDyeDamage(this.dataWatcher.getWatchableObjectByte(20) & 15);
	}

	public void setCollarColor(EnumDyeColor collarcolor)
	{
		//Compare to: @EntityWolf
		this.dataWatcher.updateObject(20, Byte.valueOf((byte)(collarcolor.getDyeDamage() & 15)));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);
		tagCompound.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund)
	{
		super.readEntityFromNBT(tagCompund);
		if (tagCompund.hasKey("CollarColor", 99))
		{
			this.setCollarColor(EnumDyeColor.byDyeDamage(tagCompund.getByte("CollarColor")));
		}
	}

	@Override
	protected String getLivingSound()
	{
		//Compare to: @EntityOcelot
		return this.isTamed() ? (this.isInLove() ? "mob.cat.purr" : (this.rand.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
	}

	@Override
	protected String getHurtSound()
	{
		//Compare to: @EntityOcelot
		return "mob.cat.hitt";
	}

	@Override
	protected String getDeathSound()
	{
		//Compare to: @EntityOcelot
		return "mob.cat.hitt";
	}

	@Override
	protected float getSoundVolume()
	{
		//Compare to: @EntityOcelot
		return 0.4F;
	}

	@Override
	protected Item getDropItem()
	{
		//Compare to: @EntityOcelot
		return Items.feather;
	}

	@Override
	public boolean attackEntityAsMob(Entity p_70652_1_)
	{
		//Compare to: @EntityOcelot
		return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		//Compare to: @EntityOcelot
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			Entity entity = source.getEntity();

			if (this.ridingEntity != null && this.ridingEntity.equals(entity))
			{
				return false;
			}

			this.aiSit.setSitting(false);
			return super.attackEntityFrom(source, amount);
		}
	}

	//Compare to: @EntityOcelot
	@Override
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {}


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
					this.pushOutOfBlocks(this.posX, this.posY, this.posZ);
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
	public boolean interact(EntityPlayer player)
	{
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if (itemstack != null)
			{
				Item item = itemstack.getItem();
				if (item == Items.dye)
				{
					EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(itemstack.getMetadata());

					if (enumdyecolor != this.getCollarColor())
					{
						this.setCollarColor(enumdyecolor);

						if (!player.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
						{
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
						}

						return true;
					}
				}
			}

			if (!player.isSneaking())
			{
				if (!this.getRiding())
				{
					if (player.getDistanceSqToEntity(this) < 0.8D)
					{
						this.setRiding(player);
						return true;
					}
				}
				else
				{
					this.setRiding(null);
					return true;
				}
			}

			if (this.isOwner(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
			{
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
			}
		}
		else if (itemstack != null && itemstack.getItem() == Items.fish)
		{
			if (!player.capabilities.isCreativeMode)
			{
				--itemstack.stackSize;
			}

			if (itemstack.stackSize <= 0)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
			}

			if (!this.worldObj.isRemote)
			{
				if (this.rand.nextInt(6) == 0)
				{
					this.setTamed(true);
					this.setOwnerId(player.getUniqueID().toString());
					this.playTameEffect(true);
					this.setHealth(20.0F);
					this.aiSit.setSitting(true);
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

		return super.interact(player);
	}


	public EntityTurtle func_180493_b(EntityAgeable p_180493_1_)
	{
		//Compare to: @EntityOcelot
		EntityTurtle entityturtle = new EntityTurtle(this.worldObj);

		if (this.isTamed())
		{
			entityturtle.setOwnerId(this.getOwnerId());
			entityturtle.setTamed(true);
		}

		return entityturtle;
	}


	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		//Compare to: @EntityOcelot
		return stack != null && stack.getItem() == Items.fish;
	}

	@Override
	public boolean canMateWith(EntityAnimal otherAnimal)
	{
		//Compare to: @EntityOcelot
		if (otherAnimal == this)
		{
			return false;
		}
		else if (!this.isTamed())
		{
			return false;
		}
		else if (!(otherAnimal instanceof EntityTurtle))
		{
			return false;
		}
		else
		{
			EntityTurtle entityturtle = (EntityTurtle)otherAnimal;
			return !entityturtle.isTamed() ? false : this.isInLove() && entityturtle.isInLove();
		}
	}

	@Override
	public boolean getCanSpawnHere()
	{
		//Compare to: @EntityOcelot
		return this.worldObj.rand.nextInt(3) != 0;
	}

	@Override
	public boolean handleLavaMovement()
	{
		//Compare to: @EntityOcelot
		if (this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(this.getEntityBoundingBox()))
		{
			BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

			if (blockpos.getY() < 63)
			{
				return false;
			}

			Block block = this.worldObj.getBlockState(blockpos.down()).getBlock();

			if (block == Blocks.grass || block.isLeaves(worldObj, blockpos.down()))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public String getName()
	{
		//Compare to: @EntityOcelot
		return this.hasCustomName() ? this.getCustomNameTag() : (this.isTamed() ? StatCollector.translateToLocal("entity.Turtle.name") : super.getName());
	}

	@Override
	public void setTamed(boolean tamed)
	{
		//Compare to: @EntityWolf
		super.setTamed(tamed);

		if (tamed)
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		}
	}

	@Override
	public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_)
	{
		//Compare to: @EntityOcelot
		p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);

		if (this.worldObj.rand.nextInt(7) == 0)
		{
			for (int i = 0; i < 2; ++i)
			{
				EntityTurtle entityturtle = new EntityTurtle(this.worldObj);
				entityturtle.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
				entityturtle.setGrowingAge(-24000);
				this.worldObj.spawnEntityInWorld(entityturtle);
			}
		}

		return p_180482_2_;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		//Compare to: @EntityOcelot
		return this.func_180493_b(ageable);
	}

	/*
	protected boolean getRiding()
	{
		return this.dataWatcher.getWatchableObjectByte(18) == (byte)1;
	}*/

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
		return this.getRiding() ? this.ridingEntity.isSneaking() ? -0.85D : -0.8D : 0.0D;
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
}