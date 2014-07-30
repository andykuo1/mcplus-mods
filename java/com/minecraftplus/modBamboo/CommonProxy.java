package com.minecraftplus.modBamboo;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.registry.GameRegistry;

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
		GameRegistry.addShapedRecipe(new ItemStack(MCP_Bamboo.bamboo, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), Items.reeds});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.reeds, 9), MCP_Bamboo.bamboo);
	}
}
