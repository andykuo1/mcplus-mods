package com.minecraftplus.modQuiver;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.dye.RecipesDyeable;

public class CommonProxy implements ICommonProxy
{
	@Override
	public void register(Registry.RenderMode par1Registry)
	{

	}

	@Override
	public void register(Registry.CustomEntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.EntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addShapedRecipe(new ItemStack(MCP_Quiver.quiver, 1), new Object[] {
			"YYX", "##X", "##X",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Items.string,
			Character.valueOf('Y'), Items.feather});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quiver.quiver, 1), new Object[] {
			"XYY", "X##", "X##",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Items.string,
			Character.valueOf('Y'), Items.feather});

		par1Registry.addRecipe(new RecipesDyeable(MCP_Quiver.quiver));
		par1Registry.addRecipe(new RecipesQuiverExpanded());
	}
}
