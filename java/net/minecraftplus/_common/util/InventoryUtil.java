package net.minecraftplus._common.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtil
{
	public static int getTotalAmount(IInventory parInventory, Item parItem)
	{
		int sum = 0;
		for(int i = 0; i < parInventory.getSizeInventory(); i++)
		{
			ItemStack itemstack = parInventory.getStackInSlot(i);
			if (itemstack != null && itemstack.getItem() == parItem)
			{
				sum += itemstack.stackSize;
			}
		}

		return sum;
	}

	public static int getEmptySlot(IInventory parInventory)
	{
		for (int i = 0; i < parInventory.getSizeInventory(); ++i)
		{
			if (parInventory.getStackInSlot(i) == null)
			{
				return i;
			}
		}

		return -1;
	}

	public static int getSlotWithItem(IInventory parInventory, Item parItem)
	{
		for(int i = 0; i < parInventory.getSizeInventory(); i++)
		{
			if (parInventory.getStackInSlot(i) != null && parInventory.getStackInSlot(i).getItem() == parItem) return i;
		}

		return -1;
	}

	public static boolean hasItem(IInventory parInventory, Item parItem)
	{
		for(int i = 0; i < parInventory.getSizeInventory(); i++)
		{
			if (parInventory.getStackInSlot(i) != null && parInventory.getStackInSlot(i).getItem() == parItem) return true;
		}

		return false;
	}

	public static ItemStack takeOne(IInventory parInventory, Item parItem)
	{
		int slot = InventoryUtil.getSlotWithItem(parInventory, parItem);
		if (slot == -1) return null;

		return parInventory.decrStackSize(slot, 1);
	}

	public static ItemStack takeSome(IInventory parInventory, Item parItem, int parAmount)
	{
		int i = 0;

		while(i < parAmount)
		{
			int slot = InventoryUtil.getSlotWithItem(parInventory, parItem);
			if (slot == -1) break;

			ItemStack itemstack = parInventory.getStackInSlot(slot);
			parInventory.decrStackSize(slot, 1);
			i++;
		}

		return new ItemStack(parItem, i);
	}
}
