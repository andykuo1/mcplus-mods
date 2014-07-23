package com.minecraftplus.modTurtle;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.minecraftplus._common.dye.Dyeable;
import com.minecraftplus._common.dye.IDyeable;
import com.minecraftplus._common.entity.EntityUtil;
import com.minecraftplus._common.entity.IEntity;

public class EntityTurtle extends EntityTameable implements IDyeable.Entity, IEntity.Tameable, IEntity.Tameable.Skin, IEntity.Chestable
{
	protected AnimalChest animalChest;

	private boolean toggleRiding = true;

	public EntityTurtle(World par1World)
	{
		super(par1World);
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

		this.setSize(0.6F, 0.7F);
		this.setRiding(null);
	}

	@Override
	public void entityInit()
	{
		super.entityInit();

		this.dataWatcher.addObject(EntityUtil.DataWatcher.TAME_SKIN, Byte.valueOf((byte) 0));
		this.dataWatcher.addObject(EntityUtil.DataWatcher.CHESTED, Byte.valueOf((byte) 0));
		Dyeable.Entity.entityInit(this.dataWatcher);
		this.dataWatcher.addObject(21, Byte.valueOf((byte) 0));
	}

	@Override
	public void chestInit()
	{
		AnimalChest animalchest = this.animalChest;
		this.animalChest = this.createNewAnimalChest();
		this.animalChest.func_110133_a(this.getCommandSenderName());

		if (animalchest != null)
		{
			animalchest.func_110132_b((IInvBasic) this);
			int i = Math.min(animalchest.getSizeInventory(), this.animalChest.getSizeInventory());

			for (int j = 0; j < i; ++j)
			{
				ItemStack itemstack = animalchest.getStackInSlot(j);

				if (itemstack != null)
				{
					this.animalChest.setInventorySlotContents(j, itemstack.copy());
				}
			}

			animalchest = null;
		}

		this.animalChest.func_110134_a(this);
		this.setChested(null, true);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public double getMoveSpeed()
	{
		return 0.6D;
	}

	@Override
	public int getTameChance()
	{
		return 6;
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	public Item getTamingItem()
	{
		return Items.fish;
	}

	@Override
	public Item getBreedingItem()
	{
		return Items.fish;
	}

	@Override
	public boolean isTamingItem(ItemStack par1ItemStack)
	{
		return EntityUtil.isTamingItem(this, par1ItemStack);
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return EntityUtil.isBreedingItem(this, par1ItemStack);
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if (EntityUtil.Interact.putOnChest(this, par1EntityPlayer)) return true;
			if (EntityUtil.Interact.openChest(this, par1EntityPlayer)) return true;
			if (Dyeable.Entity.interact(this, par1EntityPlayer)) return true;

			if (!par1EntityPlayer.isSneaking())
			{
				if (!this.getRiding())
				{
					if (par1EntityPlayer.getDistanceSqToEntity(this) < 0.8D)
					{
						this.setRiding(par1EntityPlayer);
						return true;
					}
				}
				else
				{
					this.setRiding(null);
					return true;
				}
			}

			if (EntityUtil.Interact.sit(this, par1EntityPlayer)) return true;
		}
		else
		{
			if (EntityUtil.Interact.tame(this, par1EntityPlayer)) return true;
		}

		return super.interact(par1EntityPlayer);
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
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		Entity entity = par1DamageSource.getEntity();

		if (this.ridingEntity != null && this.ridingEntity.equals(entity))
		{
			return false;
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	public void openGui(EntityPlayer par1EntityPlayer)
	{
		if (par1EntityPlayer != null && this.isChested())
		{
			this.animalChest.func_110133_a(this.getCommandSenderName());
			par1EntityPlayer.openGui(MCP_Turtle.INSTANCE, 0, this.worldObj, (int) this.posX, this.getEntityId(), (int) this.posZ);
		}
	}

	@Override
	public boolean canOpenChest(EntityPlayer par1EntityPlayer)
	{
		return  this.isOwner(par1EntityPlayer) && par1EntityPlayer.getDistanceSqToEntity(this) < 9.0D;
	}

	@Override
	public boolean isChested()
	{
		return this.dataWatcher.getWatchableObjectByte(EntityUtil.DataWatcher.CHESTED) == 1;
	}

	@Override
	public void setChested(EntityPlayer par1EntityPlayer, boolean par2)
	{
		this.dataWatcher.updateObject(EntityUtil.DataWatcher.CHESTED, Byte.valueOf((byte) (par2 ? 1 : 0)));
	}

	@Override
	public void dropChest()
	{
		if (!this.worldObj.isRemote && this.animalChest != null)
		{
			this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.chest)), 0F);
		}
	}

	@Override
	public void dropChestItems()
	{
		if (!this.worldObj.isRemote && this.animalChest != null)
		{
			for (int i = 0; i < this.animalChest.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.animalChest.getStackInSlot(i);

				if (itemstack != null)
				{
					this.entityDropItem(itemstack, 0F);
				}
			}
		}
	}

	@Override
	public AnimalChest createNewAnimalChest()
	{
		return new InventoryTurtle(null, this);
	}

	@Override
	public AnimalChest getAnimalChest()
	{
		return this.animalChest;
	}

	@Override
	public void onInventoryChanged(InventoryBasic par1InventoryBasic) {}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setBoolean("Chested", this.isChested());

		if (this.isChested())
		{
			NBTTagList nbttaglist = new NBTTagList();

			for (int i = 0; i < this.animalChest.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.animalChest.getStackInSlot(i);

				if (itemstack != null)
				{
					NBTTagCompound nbttagcompound1 = new NBTTagCompound();
					nbttagcompound1.setByte("Slot", (byte)i);
					itemstack.writeToNBT(nbttagcompound1);
					nbttaglist.appendTag(nbttagcompound1);
				}
			}

			par1NBTTagCompound.setTag("Items", nbttaglist);
		}

		if (this.isTamed())
		{
			par1NBTTagCompound.setByte("CollarColor", (byte) this.getCollarColor());
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		this.setChested(null, par1NBTTagCompound.getBoolean("Chested"));

		if (this.isChested())
		{
			NBTTagList nbttaglist = (NBTTagList) par1NBTTagCompound.getTag("Items");
			this.chestInit();

			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;

				if (j < this.animalChest.getSizeInventory())
				{
					this.animalChest.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
				}
			}
		}

		if (this.isTamed())
		{
			if (par1NBTTagCompound.hasKey("CollarColor"))
			{
				this.setCollarColor(par1NBTTagCompound.getByte("CollarColor"));
			}
		}
	}

	@Override
	public void setTamed(EntityPlayer par1EntityPlayer, boolean par2)
	{
		super.setTamed(par2);
		this.playTameEffect(par2);
		this.setSittingAI(par2);

		if (par2)
		{
			this.setTameSkin(1);
			this.func_152115_b(par1EntityPlayer.getUniqueID().toString());
			this.setPathToEntity((PathEntity)null);
			this.worldObj.setEntityState(this, (byte)7);
		}
		else
		{
			this.setTameSkin(0);
			this.worldObj.setEntityState(this, (byte)6);
		}
	}

	@Override
	public void setSittingAI(boolean par1)
	{
		this.aiSit.setSitting(par1);
	}

	@Override
	public boolean isRiding()
	{
		return false;
	}

	private boolean getRiding()
	{
		return this.ridingEntity != null;
	}

	private void setRiding(Entity par1Entity)
	{
		if (par1Entity != null && par1Entity.riddenByEntity != null && par1Entity.riddenByEntity instanceof EntityTurtle)
		{
			EntityTurtle entityturtle = (EntityTurtle) par1Entity.riddenByEntity;
			entityturtle.setRiding(null);
		}

		this.mountEntity(par1Entity);
	}

	@Override
	public double getYOffset()
	{
		return this.getRiding() ? this.ridingEntity.isSneaking() ? -2.35D : -2.3D : 0.0D;
	}

	@Override
	public boolean isEntityInsideOpaqueBlock()
	{
		return this.getRiding() ? false : super.isEntityInsideOpaqueBlock();
	}

	@Override
	protected boolean canDespawn()
	{
		return !this.isTamed() && this.ticksExisted > 2400;
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
	public String getCommandSenderName()
	{
		return this.hasCustomNameTag() ? this.getCustomNameTag() : super.getCommandSenderName();
	}

	@Override
	public boolean isOwner(EntityPlayer par1EntityPlayer)
	{
		return par1EntityPlayer.getCommandSenderName().equalsIgnoreCase(this.getOwner().getCommandSenderName());
	}

	@Override
	public int getTameSkin()
	{
		return this.dataWatcher.getWatchableObjectByte(EntityUtil.DataWatcher.TAME_SKIN);
	}

	@Override
	public void setTameSkin(int par1)
	{
		this.dataWatcher.updateObject(EntityUtil.DataWatcher.TAME_SKIN, Byte.valueOf((byte)par1));
	}

	@Override
	public int getCollarColor()
	{
		return Dyeable.Entity.getCollarColor(this.dataWatcher);
	}

	@Override
	public void setCollarColor(int par1)
	{
		Dyeable.Entity.setCollarColor(this.dataWatcher, par1);
	}

	@Override
	public boolean canMateWith(EntityAnimal par1EntityAnimal)
	{
		return this.isTamed() && super.canMateWith(par1EntityAnimal);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		EntityTurtle baby = new EntityTurtle(this.worldObj);

		if (this.isTamed())
		{
			baby.func_152115_b(this.getOwner().getUniqueID().toString());
			baby.setTamed(true);
			baby.setTameSkin(this.getTameSkin());
		}

		return baby;
	}
}