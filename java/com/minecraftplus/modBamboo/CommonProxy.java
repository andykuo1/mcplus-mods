package com.minecraftplus.modBamboo;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Bamboo.bamboo, 1), "###", "###", "###", Character.valueOf('#'), Items.reeds);
		ModRegistry.addShapelessRecipe(new ItemStack(Items.reeds, 9), MCP_Bamboo.bamboo);
	}
}
