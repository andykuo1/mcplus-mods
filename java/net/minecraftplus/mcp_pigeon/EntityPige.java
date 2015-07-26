package net.minecraftplus.mcp_pigeon;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
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

import com.google.common.base.Predicate;

public class EntityPige extends EntityTameable
{
	//Compare to: @EntityOcelot
	private EntityAIAvoidEntity field_175545_bm;
	/** The tempt AI task for this mob, used to prevent taming while it is fleeing. */
	private EntityAITempt aiTempt;

	public EntityPige(World worldIn)
	{
		//Compare to: @EntityOcelot
		super(worldIn);
		this.setSize(0.4F, 0.6F);
		((PathNavigateGround)this.getNavigator()).func_179690_a(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, this.aiTempt = new EntityAITempt(this, 0.6D, Items.fish, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.8D, 3.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIMate(this, 0.8D));
		this.tasks.addTask(7, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
	}
	
	@Override
	protected void setupTamedAI()
	{
		//Compare to: @EntityOcelot
		if (this.field_175545_bm == null)
		{
			this.field_175545_bm = new EntityAIAvoidEntity(this, new Predicate()
			{
				public boolean func_179874_a(Entity p_179874_1_)
				{
					return p_179874_1_ instanceof EntityPlayer;
				}
				public boolean apply(Object p_apply_1_)
				{
					return this.func_179874_a((Entity)p_apply_1_);
				}
			}, 16.0F, 0.8D, 1.0D);
		}

		this.tasks.removeTask(this.field_175545_bm);

		if (!this.isTamed())
		{
			this.tasks.addTask(4, this.field_175545_bm);
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		//Compare to: @EntityWolf
		this.dataWatcher.addObject(20, new Byte((byte)EnumDyeColor.RED.getMetadata()));
	}

	@Override
	public void updateAITasks()
	{
		//Compare to: @EntityOcelot
		if (this.getMoveHelper().isUpdating())
		{
			double d0 = this.getMoveHelper().getSpeed();

			if (d0 == 0.6D)
			{
				this.setSneaking(true);
				this.setSprinting(false);
			}
			else if (d0 == 1.33D)
			{
				this.setSneaking(false);
				this.setSprinting(true);
			}
			else
			{
				this.setSneaking(false);
				this.setSprinting(false);
			}
		}
		else
		{
			this.setSneaking(false);
			this.setSprinting(false);
		}
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
		//Compare to: @EntityOcelot
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
	}

	//Compare to: @EntityOcelot
	@Override
	public void fall(float distance, float damageMultiplier) {}

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
			this.aiSit.setSitting(false);
			return super.attackEntityFrom(source, amount);
		}
	}

	//Compare to: @EntityOcelot
	@Override
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {}

	@Override
	public boolean interact(EntityPlayer player)
	{
		//Compare to: @EntityOcelot
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if (this.isOwner(player) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
			{
				//Compare to: @EntityWolf
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
				//Compare To: @EntityOcelot
				else
				{
					this.aiSit.setSitting(!this.isSitting());
				}
			}
		}//this.aiTempt.isRunning() && 
		else if (itemstack != null && itemstack.getItem() == Items.fish && player.getDistanceSqToEntity(this) < 9.0D)
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
				if (this.rand.nextInt(3) == 0)
				{
					this.setTamed(true);
					this.setOwnerId(player.getUniqueID().toString());
					this.playTameEffect(true);
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

	public EntityPige func_180493_b(EntityAgeable p_180493_1_)
	{
		//Compare to: @EntityOcelot
		EntityPige entitypigeon = new EntityPige(this.worldObj);

		if (this.isTamed())
		{
			entitypigeon.setOwnerId(this.getOwnerId());
			entitypigeon.setTamed(true);
		}

		return entitypigeon;
	}


	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		//Compare to: @EntityOcelot
		return stack != null && stack.getItem() == Items.fish;
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
		else if (!(otherAnimal instanceof EntityPige))
		{
			return false;
		}
		else
		{
			EntityPige entitypigeon = (EntityPige)otherAnimal;
			return !entitypigeon.isTamed() ? false : this.isInLove() && entitypigeon.isInLove();
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
		return this.hasCustomName() ? this.getCustomNameTag() : (this.isTamed() ? StatCollector.translateToLocal("entity.Pigeon.name") : super.getName());
	}

	@Override
	public void setTamed(boolean tamed)
	{
		//Compare to: @EntityOcelot
		super.setTamed(tamed);
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
				EntityPige entitypigeon = new EntityPige(this.worldObj);
				entitypigeon.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
				entitypigeon.setGrowingAge(-24000);
				this.worldObj.spawnEntityInWorld(entitypigeon);
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
}