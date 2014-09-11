package com.minecraftplus.modSaw;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Saw.saw, 1), new Object[] {
			"XX", "##",
			Character.valueOf('#'), Blocks.log,
			Character.valueOf('X'), Items.flint});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Saw.saw, 1), new Object[] {
			"XX", "##",
			Character.valueOf('#'), Blocks.log2,
			Character.valueOf('X'), Items.flint});
	}
}
