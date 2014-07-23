package com.minecraftplus.modSaw;

import net.minecraft.init.Blocks;
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
		par1Registry.addShapedRecipe(new ItemStack(MCP_Saw.saw, 1), new Object[] {
			"XX", "##",
			Character.valueOf('#'), Blocks.log,
			Character.valueOf('X'), Items.flint});
		
		par1Registry.addShapedRecipe(new ItemStack(MCP_Saw.saw, 1), new Object[] {
			"XX", "##",
			Character.valueOf('#'), Blocks.log2,
			Character.valueOf('X'), Items.flint});
	}
}
