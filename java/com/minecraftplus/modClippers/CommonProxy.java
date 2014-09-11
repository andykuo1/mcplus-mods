package com.minecraftplus.modClippers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addEntity(EntityChickenNaked.class, "naked_chicken");

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Clippers.clippers, 1),
				" #", "# ",
				Character.valueOf('#'), new ItemStack(Items.gold_ingot));
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Clippers.clippers, 1),
				"# ", " #",
				Character.valueOf('#'), new ItemStack(Items.gold_ingot));
	}
}
