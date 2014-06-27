package com.minecraftplus.modSurvivor;

import net.minecraft.init.Items;
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
		par1Registry.addShapedRecipe(new ItemStack(MCP_Survivor.hatchet, 1), new Object[] {
			"X#", "X ",
			Character.valueOf('#'), Items.flint,
			Character.valueOf('X'), Items.stick});
		par1Registry.addShapedRecipe(new ItemStack(MCP_Survivor.hatchet, 1), new Object[] {
			"#X", " X",
			Character.valueOf('#'), Items.flint,
			Character.valueOf('X'), Items.stick});
		par1Registry.addShapedRecipe(new ItemStack(MCP_Survivor.dagger, 1), new Object[] {
			"#", "X",
			Character.valueOf('#'), Items.flint,
			Character.valueOf('X'), Items.stick});
	}
}
