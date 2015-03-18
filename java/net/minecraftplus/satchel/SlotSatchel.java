package net.minecraftplus.satchel;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSatchel extends Slot
{
	public SlotSatchel(IInventory par1IInventory, int par2, int par3, int par4)
	{
		super(par1IInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		Item item = (par1ItemStack == null ? null : par1ItemStack.getItem());
		return item != null && !(item instanceof ItemSatchel) && !(item instanceof ItemEnderSatchel);
	}
}
