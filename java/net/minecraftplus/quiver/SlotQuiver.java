package net.minecraftplus.quiver;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotQuiver extends Slot
{
	public SlotQuiver(IInventory par1iInventory, int par2, int par3, int par4)
	{
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		Item item = (par1ItemStack == null ? null : par1ItemStack.getItem());
		return item != null && item == Items.arrow;
	}
}
