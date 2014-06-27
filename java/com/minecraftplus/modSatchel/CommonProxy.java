package com.minecraftplus.modSatchel;

import net.minecraft.init.Blocks;
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
		par1Registry.addShapedRecipe(new ItemStack(MCP_Satchel.satchel, 1), new Object[] {
			"###", "YXY", "###",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Blocks.chest,
			Character.valueOf('Y'), Items.string});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Satchel.enderSatchel, 1), new Object[] {
			"###", "YXY", "###",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Blocks.ender_chest,
			Character.valueOf('Y'), Items.string});

		par1Registry.addRecipe(new RecipesDyeable(MCP_Satchel.satchel));
		par1Registry.addRecipe(new RecipesDyeable(MCP_Satchel.enderSatchel));
	}
}
