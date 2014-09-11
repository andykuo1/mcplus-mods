package com.minecraftplus.modCocoa;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Cocoa.chocolateBar, 1), new Object[] {
			"###", "###",
			Character.valueOf('#'), new ItemStack(Items.dye, 1, 3),});
		ModRegistry.addShapelessRecipe(new ItemStack(Items.dye, 6, 3), new Object[] {MCP_Cocoa.chocolateBar});
		ModRegistry.addShapelessRecipe(new ItemStack(MCP_Cocoa.chocolatePie), new Object[] {MCP_Cocoa.chocolateBar, MCP_Cocoa.chocolateBar, Items.egg, Items.sugar});
	}
}
