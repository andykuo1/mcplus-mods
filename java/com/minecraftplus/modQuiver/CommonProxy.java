package com.minecraftplus.modQuiver;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;
import com.minecraftplus._common.dye.RecipesDyeable;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quiver.quiver, 1), new Object[] {
			"YYX", "##X", "##X",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Items.string,
			Character.valueOf('Y'), Items.feather});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quiver.quiver, 1), new Object[] {
			"XYY", "X##", "X##",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Items.string,
			Character.valueOf('Y'), Items.feather});

		ModRegistry.addRecipe(new RecipesDyeable(MCP_Quiver.quiver));
		ModRegistry.addRecipe(new RecipesQuiverExpanded());
	}
}
