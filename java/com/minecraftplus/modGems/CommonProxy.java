package com.minecraftplus.modGems;

import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

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
		par1Registry.addShapedRecipe(new ItemStack(MCP_Gems.rubyBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), MCP_Gems.ruby});
		par1Registry.addShapedRecipe(new ItemStack(MCP_Gems.sapphireBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), MCP_Gems.sapphire});
	}
}
