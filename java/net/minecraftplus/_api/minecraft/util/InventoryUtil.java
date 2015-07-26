package net.minecraftplus._api.minecraft.util;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtil
{
	private InventoryUtil() {}

	public static final ItemStack[] getItems(IInventory parInventory, Item... parItems)
	{
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		
		for(Item item : parItems)
		{
			for(int i = 0; i < parInventory.getSizeInventory(); ++i)
			{
				ItemStack itemstack = parInventory.getStackInSlot(i);
				if (itemstack == null) continue;
				if (item == itemstack.getItem()) list.add(itemstack);
			}
		}

		return list.toArray(new ItemStack[list.size()]);
	}

	public static final boolean hasItems(IInventory parInventory, Item... parItems)
	{
		return getItems(parInventory, parItems).length > 0;
	}
}
