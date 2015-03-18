package net.minecraftplus.pigeon;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
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
import net.minecraft.world.World;

public class EntityPigeonBase extends EntityTameable
{
	public EntityPigeonBase(World parWorld)
	{
		super(parWorld);
		this.setSize(0.4F, 0.6F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.8D, 1.0D));
		this.tasks.addTask(4, new EntityAIFollowOwner(this, 0.8D, 3.0F, 2.0F));
		this.tasks.addTask(5, new EntityAIMate(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.setTamed(false);
		this.setIsFlying(false);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);

		if (this.isTamed())
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
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
		this.dataWatcher.addObject(18, new Byte((byte)0));
		this.dataWatcher.addObject(19, new Byte((byte)0));
		this.dataWatcher.addObject(20, new Byte((byte)BlockColored.func_150032_b(1)));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound parNBTTagCompound)
	{
		super.writeEntityToNBT(parNBTTagCompound);

		parNBTTagCompound.setByte("BirdFlags", this.dataWatcher.getWatchableObjectByte(18));
		parNBTTagCompound.setByte("CollarColor", (byte)this.getCollarColor());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound parNBTTagCompound)
	{
		super.readEntityFromNBT(parNBTTagCompound);

		this.dataWatcher.updateObject(18, Byte.valueOf(parNBTTagCompound.getByte("BirdFlags")));

		if (parNBTTagCompound.hasKey("CollarColor", 99))
		{
			this.setCollarColor(parNBTTagCompound.getByte("CollarColor"));
		}
	}
	
	@Override
	protected void fall(float parFloat) {}

	@Override
	protected Item getDropItem()
	{
		return Items.feather;
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 0.8F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource parDamageSource, float parDamage)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		else
		{
			this.aiSit.setSitting(false);
			return super.attackEntityFrom(parDamageSource, parDamage);
		}
	}

	@Override
	public void setTamed(boolean parIsTame)
	{
		super.setTamed(parIsTame);

		if (parIsTame)
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		}
	}

	@Override
	public boolean interact(EntityPlayer p_70085_1_)
	{
		ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

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

						if (!p_70085_1_.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
						{
							p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack)null);
						}

						return true;
					}
				}
			}

			if (this.func_152114_e(p_70085_1_) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack))
			{
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.setPathToEntity((PathEntity)null);
				this.setTarget((Entity)null);
				this.setAttackTarget((EntityLivingBase)null);
			}
		}
		else if (itemstack != null && itemstack.getItem() == Items.melon_seeds)
		{
			if (!p_70085_1_.capabilities.isCreativeMode)
			{
				--itemstack.stackSize;
			}

			if (itemstack.stackSize <= 0)
			{
				p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack)null);
			}

			if (!this.worldObj.isRemote)
			{
				if (this.rand.nextInt(3) == 0)
				{
					this.setTamed(true);
					this.setPathToEntity((PathEntity)null);
					this.setAttackTarget((EntityLivingBase)null);
					this.aiSit.setSitting(true);
					this.setHealth(20.0F);
					this.func_152115_b(p_70085_1_.getUniqueID().toString());
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

		return super.interact(p_70085_1_);
	}

	@Override
	public boolean isBreedingItem(ItemStack parItemStack)
	{
		return parItemStack == null ? false : parItemStack.getItem() == Items.melon;
	}

	@Override
	public int getMaxSpawnedInChunk()
	{
		return 8;
	}

	/**
	 * Return this pigeon's collar color.
	 */
	public int getCollarColor()
	{
		return this.dataWatcher.getWatchableObjectByte(20) & 15;
	}

	/**
	 * Set this pigeon's collar color.
	 */
	public void setCollarColor(int parColor)
	{
		this.dataWatcher.updateObject(20, Byte.valueOf((byte)(parColor & 15)));
	}

	public boolean getIsFlying()
	{
		return (this.dataWatcher.getWatchableObjectByte(18) & 1) != 0;
	}

	public void setIsFlying(boolean parIsFlying)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(18);

		if (parIsFlying)
		{
			this.dataWatcher.updateObject(18, Byte.valueOf((byte)(b0 | 1)));
		}
		else
		{
			this.dataWatcher.updateObject(18, Byte.valueOf((byte)(b0 & -2)));
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable parEntityAgeable)
	{
		EntityPigeonBase pigeon = new EntityPigeonBase(this.worldObj);
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
		else if (!(parEntity instanceof EntityPigeonBase))
		{
			return false;
		}
		else
		{
			EntityPigeonBase entitypigeon = (EntityPigeonBase)parEntity;
			return !entitypigeon.isTamed() ? false : (entitypigeon.isSitting() ? false : this.isInLove() && entitypigeon.isInLove());
		}
	}

	@Override
	protected boolean canDespawn()
	{
		return !this.isTamed() && this.ticksExisted > 2400;
	}
}