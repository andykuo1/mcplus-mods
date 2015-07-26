package net.minecraftplus.mcp_clay_tools;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.minecraft.base.RecipesDyeable;

public class RecipesHoeClayDyeable extends RecipesDyeable
{
	public RecipesHoeClayDyeable(Item output)
	{
		super((ItemHoeClay) output);
	}

	@Override
	protected void setColor(ItemStack itemstack, int color)
	{
		((ItemHoeClay) this.recipeItem).setColor(itemstack, color);
	}

	@Override
	protected int getColor(ItemStack itemstack)
	{
		return ((ItemHoeClay) this.recipeItem).getColor(itemstack);
	}

	@Override
	protected boolean hasColor(ItemStack itemstack)
	{
		return ((ItemHoeClay) this.recipeItem).hasColor(itemstack);
	}
}