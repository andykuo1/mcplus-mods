package net.minecraftplus.claytools;

import net.minecraft.item.ItemStack;

public interface IDyeable
{
	public interface Entity
	{
		public int getCollarColor();

		public void setCollarColor(int par1);
	}

	public interface Item
	{
		public boolean requiresMultipleRenderPasses();

		public int getColorFromItemStack(ItemStack par1ItemStack, int par2);

		public boolean hasColor(ItemStack par1ItemStack);

		public int getColor(ItemStack par1ItemStack);

		public void setColor(ItemStack par1ItemStack, int par2);

		public void removeColor(ItemStack par1ItemStack);
	}
}
