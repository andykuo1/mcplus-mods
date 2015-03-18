package net.minecraftplus._common;

import net.minecraft.item.ItemStack;

public interface IItemDyeable
{
	public int getColorFromItemStack(ItemStack parItemStack, int parColor);

	public boolean hasColor(ItemStack parItemStack);

	public int getColor(ItemStack parItemStack);

	public void removeColor(ItemStack parItemStack);

	public void setColor(ItemStack parItemStack, int parColor);
}
