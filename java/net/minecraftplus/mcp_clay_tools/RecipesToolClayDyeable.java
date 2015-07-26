package net.minecraftplus.mcp_clay_tools;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.minecraft.base.RecipesDyeable;

public class RecipesToolClayDyeable extends RecipesDyeable
{
	public RecipesToolClayDyeable(Item output)
	{
		super((ItemToolClay) output);
	}

	@Override
	protected void setColor(ItemStack itemstack, int color)
	{
		((ItemToolClay) this.recipeItem).setColor(itemstack, color);
	}

	@Override
	protected int getColor(ItemStack itemstack)
	{
		return ((ItemToolClay) this.recipeItem).getColor(itemstack);
	}

	@Override
	protected boolean hasColor(ItemStack itemstack)
	{
		return ((ItemToolClay) this.recipeItem).hasColor(itemstack);
	}
}