package com.minecraftplus.modRabbit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.minecraftplus._common.entity.EntityUtil;
import com.minecraftplus._common.entity.IEntity;

public class EntityRabbit extends EntityTameable implements IEntity.Tameable
{
	public EntityRabbit(World par1World)
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

		this.setSize(0.3F, 0.3F);
	}

	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10D);
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
		return 1.4D;
	}

	@Override
	public int getTameChance()
	{
		return 6;
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		return EntityUtil.Interact.tame(this, par1EntityPlayer) || EntityUtil.Interact.sit(this, par1EntityPlayer) || super.interact(par1EntityPlayer);
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (this.isCollidedVertically && (((int) this.posX - (int) this.prevPosX != 0) || ((int) this.posZ - (int) this.prevPosZ != 0)))
		{
			this.motionY = 0.28D;
		}
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	public Item getTamingItem()
	{
		return Items.carrot;
	}

	public Item getBreedingItem()
	{
		return Items.golden_carrot;
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
		EntityRabbit baby = new EntityRabbit(this.worldObj);

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
		this.setTamed(par2);
		this.playTameEffect(par2);
		this.aiSit.setSitting(par2);
		
		if (par2)
		{
			this.setOwner(par1EntityPlayer.getCommandSenderName());
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
	public boolean isTamingItem(ItemStack par1ItemStack)
	{
		return EntityUtil.isTamingItem(this, par1ItemStack);
	}

	@Override
	public void setSittingAI(boolean par1)
	{
		this.aiSit.setSitting(par1);
	}
}
