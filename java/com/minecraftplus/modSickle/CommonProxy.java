package com.minecraftplus.modSickle;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Sickle.sickle, 1), new Object[] {
			"X ", " X", "# ",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), Items.iron_ingot});
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Sickle.sickle, 1), new Object[] {
			" X", "X ", " #",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), Items.iron_ingot});
	}
}
