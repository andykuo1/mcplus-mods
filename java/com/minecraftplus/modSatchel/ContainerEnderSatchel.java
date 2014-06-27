package com.minecraftplus.modSatchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.minecraftplus._common.container.ContainerItem;

public class ContainerEnderSatchel extends ContainerItem
{
	protected int numRows = 0;

	public ContainerEnderSatchel(EntityPlayer par1EntityPlayer, InventoryEnderChest par2InventoryEnderChest, ItemStack par3ItemStack)
	{
		super(par1EntityPlayer, par2InventoryEnderChest, par3ItemStack, par1EntityPlayer.inventory.currentItem);

		this.numRows = par2InventoryEnderChest.getSizeInventory() / 9;

		this.addChestSlots(0, par2InventoryEnderChest);
		this.addSlotPlayerMainInventory(0, 32);
		this.addSlotPlayerHotBar(0, 32);
	}

	public void addChestSlots(int par1, InventoryEnderChest par2InventoryEnderChest)
	{
		for (int j = 0; j < this.numRows; ++j)
		{
			for (int k = 0; k < 9; ++k)
			{
				this.addSlotToContainer(new Slot(par2InventoryEnderChest, k + j * 9, 8 + k * 18, par1 + 18 + j * 18));
			}
		}
	}

	public boolean canItemStackBeShifted(ItemStack par1ItemStack)
	{
		return true;
	}
}