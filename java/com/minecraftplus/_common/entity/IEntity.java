package com.minecraftplus._common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IEntity
{
	public double getMoveSpeed();

	public interface Tameable extends IEntity
	{
		public int getTameChance();

		public void setTamed(EntityPlayer par1EntityPlayer, boolean par2);

		public Item getTamingItem();

		public Item getBreedingItem();

		public boolean isTamingItem(ItemStack par1ItemStack);

		public boolean isBreedingItem(ItemStack par1ItemStack);

		public boolean isOwner(EntityPlayer par1EntityPlayer);
		
		public void setSittingAI(boolean par1);

		public interface Skin
		{
			public int getTameSkin();

			public void setTameSkin(int par1);
		}
	}

	public interface Chestable extends IInvBasic
	{
		public void chestInit();

		public boolean isChested();

		public void setChested(EntityPlayer par1EntityPlayer, boolean par2);

		public AnimalChest getAnimalChest();

		public boolean canOpenChest(EntityPlayer par1EntityPlayer);

		public void openGui(EntityPlayer par1EntityPlayer);

		public void dropChest();

		public void dropChestItems();

		public AnimalChest createNewAnimalChest();
	}
}
