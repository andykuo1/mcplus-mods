package com.minecraftplus.modSatchel;

import net.minecraft.init.Blocks;
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
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Satchel.satchel, 1), new Object[] {
			"###", "YXY", "###",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Blocks.chest,
			Character.valueOf('Y'), Items.string});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Satchel.enderSatchel, 1), new Object[] {
			"###", "YXY", "###",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Blocks.ender_chest,
			Character.valueOf('Y'), Items.string});

		ModRegistry.addRecipe(new RecipesDyeable(MCP_Satchel.satchel));
		ModRegistry.addRecipe(new RecipesDyeable(MCP_Satchel.enderSatchel));
	}
}
