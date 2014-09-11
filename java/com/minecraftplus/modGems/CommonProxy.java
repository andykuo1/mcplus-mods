package com.minecraftplus.modGems;

import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Gems.rubyBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), MCP_Gems.ruby});
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Gems.sapphireBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), MCP_Gems.sapphire});
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Gems.amethystBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), MCP_Gems.amethyst});

		ModRegistry.addShapelessRecipe(new ItemStack(MCP_Gems.ruby, 9), MCP_Gems.rubyBlock);
		ModRegistry.addShapelessRecipe(new ItemStack(MCP_Gems.sapphire, 9), MCP_Gems.sapphireBlock);
		ModRegistry.addShapelessRecipe(new ItemStack(MCP_Gems.amethyst, 9), MCP_Gems.amethystBlock);
	}
}
