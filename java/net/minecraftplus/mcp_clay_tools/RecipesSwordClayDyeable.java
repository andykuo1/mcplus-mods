package net.minecraftplus.mcp_clay_tools;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.minecraft.base.RecipesDyeable;

public class RecipesSwordClayDyeable extends RecipesDyeable
{
	public RecipesSwordClayDyeable(Item output)
	{
		super((ItemSwordClay) output);
	}

	@Override
	protected void setColor(ItemStack itemstack, int color)
	{
		((ItemSwordClay) this.recipeItem).setColor(itemstack, color);
	}

	@Override
	protected int getColor(ItemStack itemstack)
	{
		return ((ItemSwordClay) this.recipeItem).getColor(itemstack);
	}

	@Override
	protected boolean hasColor(ItemStack itemstack)
	{
		return ((ItemSwordClay) this.recipeItem).hasColor(itemstack);
	}
}