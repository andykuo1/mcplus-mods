package com.minecraftplus.modCocoa;

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
		par1Registry.addShapedRecipe(new ItemStack(MCP_Cocoa.chocolateBar, 1), new Object[] {
			"###", "###",
			Character.valueOf('#'), new ItemStack(Items.dye, 1, 3),});
		par1Registry.addShapelessRecipe(new ItemStack(Items.dye, 6, 3), new Object[] {MCP_Cocoa.chocolateBar});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_Cocoa.chocolatePie), new Object[] {MCP_Cocoa.chocolateBar, MCP_Cocoa.chocolateBar, Items.egg, Items.sugar});
	}
}
