package com.minecraftplus.modPigeon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityAgeable;
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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.minecraftplus._common.entity.EntityUtil;
import com.minecraftplus._common.entity.IEntity;

public class CopyOfEntityPigeon extends EntityTameable implements IEntity.Tameable
{
	private ChunkCoordinates targetPosition;
	private EntityPlayer travelPlayer;

	public CopyOfEntityPigeon(World par1World)
	{
		super(par1World);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, this.aiSit);

		this.tasks.addTask(3, new EntityAITempt(this, this.getMoveSpeed() * 1.2D, this.getTamingItem(), false));
		this.tasks.addTask(3, new EntityAITempt(this, this.getMoveSpeed() * 1.2D, this.getBreedingItem(), false));

		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.getMoveSpeed(), 2.0F, 2.0F));
		this.tasks.addTask(5, new EntityAIMate(this, this.getMoveSpeed()));
		this.tasks.addTask(6, new EntityAIFollowParent(this, this.getMoveSpeed() * 1.1D));

		this.tasks.addTask(7, new EntityAIWander(this, this.getMoveSpeed()));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6F));
		this.tasks.addTask(8, new EntityAILookIdle(this));

		this.setSize(0.2F, 0.2F);
		this.setIsBirdPerched(true);
		this.setTravelingState((byte) 0);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(18, new Byte((byte)0));
		this.dataWatcher.addObject(19, new Byte((byte)0));
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
	}

	@Override
	protected boolean canDespawn()
	{
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	@Override
	public double getMoveSpeed()
	{
		return 0.9D;
	}

	@Override
	public int getTameChance()
	{
		return 3;
	}

	public boolean getIsBirdPerched()
	{
		return (this.dataWatcher.getWatchableObjectByte(18) & 1) != 0;
	}

	public void setIsBirdPerched(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(18);

		if (par1)
		{
			this.dataWatcher.updateObject(18, Byte.valueOf((byte)(b0 | 1)));
		}
		else
		{
			this.dataWatcher.updateObject(18, Byte.valueOf((byte)(b0 & -2)));
		}

		if (!par1)
		{
			this.setSittingAI(false);
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (!this.getIsBirdPerched())
		{
			if (this.isTamed())
			{
				this.setIsBirdPerched(true);
			}
			this.motionY *= 0.6000000238418579D;
		}
	}

	@Override
	protected void updateAITasks()
	{
		if (this.isTraveling())
		{
			this.travelTo(this.travelPlayer);
			return;
		}

		if (this.isTamed())
		{
			super.updateAITasks();
			return;
		}

		if (this.getIsBirdPerched())
		{
			if (this.isTamed()) return;
			
			if (this.worldObj.isAirBlock((int) this.posX, (int) this.posY + 1, (int) this.posZ))
			{
				EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 4.0D);

				boolean flag = player != null;
				if (flag)
				{
					if (this.isTamed() && player.getCommandSenderName().equals(this.getOwnerName()))
					{
						flag = false;
					}
					else if (player.getCurrentEquippedItem() != null)
					{
						Item item = player.getCurrentEquippedItem().getItem();
						if (item == this.getTamingItem())
						{
							flag = false;
						}
					}
				}

				if (flag || this.rand.nextInt(300) == 0)
				{
					this.setIsBirdPerched(false);
					this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1015, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
				}
			}
		}
		else
		{
			System.out.println(this.isTamed());
			
			if (this.targetPosition != null && (!this.worldObj.isAirBlock(this.targetPosition.posX, this.targetPosition.posY, this.targetPosition.posZ) || this.targetPosition.posY < 1))
			{
				this.targetPosition = null;
			}

			if (this.targetPosition == null || this.rand.nextInt(40) == 0 || this.targetPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F)
			{
				int y = -2 - this.rand.nextInt(3);
				for(int i = 0; i < 6; i++)
				{
					if (!this.worldObj.isAirBlock((int) this.posX, (int) this.posY - i, (int) this.posZ))
					{
						y = 4 + this.rand.nextInt(6);
						break;
					}
				}

				this.targetPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(18) - this.rand.nextInt(18), (int)this.posY + y, (int)this.posZ + this.rand.nextInt(18) - this.rand.nextInt(18));
			}

			double d0 = (double)this.targetPosition.posX + 0.5D - this.posX;
			double d1 = (double)this.targetPosition.posY + 0.1D - this.posY;
			double d2 = (double)this.targetPosition.posZ + 0.5D - this.posZ;
			this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
			this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
			this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
			float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
			this.moveForward = 0.4F;
			this.rotationYaw += f1;

			if (this.rand.nextInt(200) == 0)
			{
				boolean flag = false;
				for(int i = 0; i < 4; i++)
				{
					if (!this.worldObj.isAirBlock((int) this.posX, (int) this.posY - i, (int) this.posZ))
					{
						flag = true;
						break;
					}
				}

				if (flag)
				{
					this.setIsBirdPerched(true);
				}
			}
		}
	}

	public boolean isTraveling()
	{
		return this.dataWatcher.getWatchableObjectByte(19) != (byte) 0;
	}

	public int getTravelingState()
	{
		return this.dataWatcher.getWatchableObjectByte(19);
	}

	public void startTraveling(EntityPlayer par1EntityPlayer)
	{
		this.setIsBirdPerched(false);
		this.setTravelingState((byte) 1);
		this.rotationYaw = -(float) (Math.toDegrees(Math.atan2(this.posZ - par1EntityPlayer.posZ, par1EntityPlayer.posX - this.posX)) + 360) % 360;
		this.travelPlayer = par1EntityPlayer;
	}

	public void stopTraveling()
	{
		this.setTravelingState((byte) 0);
		this.setIsBirdPerched(true);
		this.travelPlayer = null;
	}

	public void setTravelingState(byte par1)
	{
		this.dataWatcher.updateObject(19, par1);
	}

	public void travelTo(EntityPlayer par1EntityPlayer)
	{
		if (this.getTravelingState() == (byte) 1)
		{
			this.rotationYaw = -(float) (Math.toDegrees(Math.atan2(this.posZ - par1EntityPlayer.posZ, par1EntityPlayer.posX - this.posX)) + 360) % 360;
			this.moveForward = 0.4F;
			if (this.isCollidedHorizontally) this.jump();

			if (this.getDistanceSqToEntity(this.getOwner()) > 8)
			{
				System.out.println("YAY");
				this.setTravelingState((byte) 2);
				int dist = 6;
				this.posX = Math.cos(Math.toRadians(this.rotationYaw + 180)) * dist;
				this.posZ = -Math.sin(Math.toRadians(this.rotationYaw + 180)) * dist;

				while(!this.worldObj.isAirBlock((int) this.posX, (int) this.posY, (int) this.posZ))
				{
					dist--;
					this.rotationYaw = this.rand.nextFloat() * 360;
					this.posX = Math.cos(Math.toRadians(this.rotationYaw)) * dist;
					this.posY = par1EntityPlayer.posY + this.rand.nextInt(16) - 6;
					this.posZ = -Math.sin(Math.toRadians(this.rotationYaw)) * dist;
				}
			}
		}
		else if (this.getTravelingState() == (byte) 2)
		{
			this.rotationYaw = -(float) (Math.toDegrees(Math.atan2(this.posZ - par1EntityPlayer.posZ, par1EntityPlayer.posX - this.posX)) + 360) % 360;
			this.moveForward = 0.4F;
			if (this.isCollidedHorizontally) this.jump();

			if (this.getDistanceSqToEntity(par1EntityPlayer) < 4)
			{
				if (this.getDistanceSqToEntity(par1EntityPlayer) < 2)
				{
					this.setTravelingState((byte) 3);
				}
			}
		}
		else if (this.getTravelingState() == (byte) 3)
		{
			this.setIsBirdPerched(true);
			this.rotationYaw = -(float) (Math.toDegrees(Math.atan2(this.posZ - par1EntityPlayer.posZ, par1EntityPlayer.posX - this.posX)) + 360) % 360;
			if (this.getDistanceSqToEntity(par1EntityPlayer) > 2)
			{
				this.moveForward = 0.4F;
				if (this.isCollidedHorizontally)
				{
					this.jump();
				}
			}
		}
		else if (this.getTravelingState() == (byte) 4)
		{
			this.rotationYaw = -(float) (Math.toDegrees(Math.atan2(par1EntityPlayer.posZ - this.posZ, this.posX - par1EntityPlayer.posX)) + 360) % 360;
			this.moveForward = 0.4F;
			if (this.isCollidedHorizontally) this.jump();

			if (this.getDistanceSqToEntity(par1EntityPlayer) < 4)
			{
				if (this.getDistanceSqToEntity(par1EntityPlayer) < 2)
				{
					this.stopTraveling();
				}
			}
		}
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected void fall(float par1) {}

	@Override
	protected void updateFallState(double par1, boolean par3) {}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		else
		{
			if (!this.worldObj.isRemote && this.getIsBirdPerched())
			{
				this.setIsBirdPerched(false);
			}

			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.dataWatcher.updateObject(18, Byte.valueOf(par1NBTTagCompound.getByte("BirdFlags")));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setByte("BirdFlags", this.dataWatcher.getWatchableObjectByte(18));
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		if (itemstack != null && itemstack.getItem() == Items.paper)
		{
			this.startTraveling(par1EntityPlayer);
		}

		return EntityUtil.Interact.tame(this, par1EntityPlayer) || EntityUtil.Interact.sit(this, par1EntityPlayer) || super.interact(par1EntityPlayer);
	}

	public Item getTamingItem()
	{
		return Items.melon_seeds;
	}

	public Item getBreedingItem()
	{
		return Items.melon_seeds;
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
	public EntityAgeable createChild(EntityAgeable par1EntityAgeable)
	{
		CopyOfEntityPigeon baby = new CopyOfEntityPigeon(this.worldObj);

		if (this.isTamed())
		{
			baby.setOwner(this.getOwnerName());
			baby.setTamed(true);
		}

		return baby;
	}

	@Override
	public void setTamed(EntityPlayer par1EntityPlayer, boolean par2)
	{
		super.setTamed(par2);
		this.playTameEffect(par2);
		this.setSittingAI(par2);

		if (par2)
		{
			this.setOwner(par1EntityPlayer.getCommandSenderName());
			this.setPathToEntity((PathEntity)null);
			this.worldObj.setEntityState(this, (byte)7);
		}
		else
		{
			this.worldObj.setEntityState(this, (byte)6);
		}
	}

	@Override
	public boolean isOwner(EntityPlayer par1EntityPlayer)
	{
		return par1EntityPlayer.getCommandSenderName().equalsIgnoreCase(this.getOwnerName());
	}

	@Override
	public boolean canMateWith(EntityAnimal par1EntityAnimal)
	{
		return this.isTamed() && super.canMateWith(par1EntityAnimal);
	}

	@Override
	public void setSittingAI(boolean par1)
	{
		this.aiSit.setSitting(par1);
	}
}
