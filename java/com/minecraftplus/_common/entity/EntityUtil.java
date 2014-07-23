package com.minecraftplus._common.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;

public class EntityUtil
{
	public static Random rand = new Random();

	public static boolean isBreedingItem(IEntity.Tameable par1IEntityTameable, ItemStack par2ItemStack)
	{
		return par2ItemStack != null && par2ItemStack.getItem() == par1IEntityTameable.getBreedingItem();
	}

	public static boolean isTamingItem(IEntity.Tameable par1IEntityTameable, ItemStack par2ItemStack)
	{
		return par2ItemStack != null && par2ItemStack.getItem() == par1IEntityTameable.getTamingItem();
	}

	public static class DataWatcher
	{
		public static final int TAME_SKIN = 18;
		public static final int CHESTED = 19;
		public static final int DYE_COLOR = 20;
	}

	public static class Interact
	{
		public static <E extends EntityTameable & IEntity.Tameable> boolean sit(E par1Entity, EntityPlayer par2EntityPlayer)
		{
			ItemStack itemstack = par2EntityPlayer.inventory.getCurrentItem();

			if (par2EntityPlayer.getCommandSenderName().equalsIgnoreCase(par1Entity.getOwner().getCommandSenderName()) && !par1Entity.worldObj.isRemote && !par1Entity.isBreedingItem(itemstack) && !par1Entity.isTamingItem(itemstack))
			{
				par1Entity.setSittingAI(!par1Entity.isSitting());
				par1Entity.setJumping(false);
				par1Entity.setPathToEntity((PathEntity)null);
				par1Entity.setTarget((Entity)null);
				par1Entity.setAttackTarget((EntityLivingBase)null);
			}

			return false;
		}

		public static <E extends EntityTameable & IEntity.Tameable> boolean tame(E par1Entity, EntityPlayer par2EntityPlayer)
		{
			ItemStack itemstack = par2EntityPlayer.inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem() == par1Entity.getTamingItem())
			{
				if (!par2EntityPlayer.capabilities.isCreativeMode)
				{
					--itemstack.stackSize;
				}

				if (itemstack.stackSize <= 0)
				{
					par2EntityPlayer.inventory.setInventorySlotContents(par2EntityPlayer.inventory.currentItem, (ItemStack)null);
				}

				if (!par1Entity.worldObj.isRemote)
				{
					if (EntityUtil.rand.nextInt(par1Entity.getTameChance()) == 0)
					{
						par1Entity.setTamed(par2EntityPlayer, true);
					}
					else
					{
						par1Entity.setTamed(par2EntityPlayer, false);
					}
				}

				return true;
			}

			return false;
		}

		public static boolean openChest(IEntity.Chestable par1IEntityChestable, EntityPlayer par2EntityPlayer)
		{
			if (par1IEntityChestable.isChested() && par2EntityPlayer.isSneaking() && par1IEntityChestable.canOpenChest(par2EntityPlayer))
			{
				par1IEntityChestable.openGui(par2EntityPlayer);
				return true;
			}

			return false;
		}

		public static boolean putOnChest(IEntity.Chestable par1IEntityChestable, EntityPlayer par2EntityPlayer)
		{
			ItemStack itemstack = par2EntityPlayer.inventory.getCurrentItem();

			if (!par1IEntityChestable.isChested() && itemstack != null && itemstack.getItem() == Item.getItemFromBlock(Blocks.chest) && par1IEntityChestable.canOpenChest(par2EntityPlayer))
			{
				par1IEntityChestable.setChested(par2EntityPlayer, true);
				par1IEntityChestable.chestInit();
				return true;
			}

			return false;
		}
	}
}
