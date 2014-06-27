package com.minecraftplus.modSatchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.minecraftplus._common.container.InventoryItem;

public class InventorySatchel extends InventoryItem
{
	public InventorySatchel(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
	{
		super(par1EntityPlayer, par2ItemStack, 18);
	}

	public ItemStack getCurrentItemStack(EntityPlayer par1EntityPlayer)
	{
		return par1EntityPlayer.getCurrentEquippedItem();
	}
}
