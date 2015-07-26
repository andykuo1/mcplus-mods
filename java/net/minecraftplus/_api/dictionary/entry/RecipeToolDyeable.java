package net.minecraftplus._api.dictionary.entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.minecraft.base.ItemDyeable;
import net.minecraftplus._api.minecraft.base.ItemToolDyeable;
import net.minecraftplus._api.minecraft.base.RecipesDyeable;

public class RecipeToolDyeable extends RecipesDyeable
{
	public RecipeToolDyeable(Item parItem)
	{
		super((ItemToolDyeable) parItem);
	}

	@Override
	protected void setColor(ItemStack itemstack, int color)
	{
		((ItemDyeable) itemstack.getItem()).setColor(itemstack, color);
	}

	@Override
	protected int getColor(ItemStack itemstack)
	{
		return ((ItemDyeable) itemstack.getItem()).getColor(itemstack);
	}

	@Override
	protected boolean hasColor(ItemStack itemstack)
	{
		return ((ItemDyeable) itemstack.getItem()).hasColor(itemstack);
	}
}
