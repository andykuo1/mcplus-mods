package com.minecraftplus.modWhetstone;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Whetstone.whetstone, 1, 0), new Object[] {
			"###","###",
			Character.valueOf('#'), Items.flint});

		ModRegistry.addRecipe(new RecipesWhetstone());
	}
}
