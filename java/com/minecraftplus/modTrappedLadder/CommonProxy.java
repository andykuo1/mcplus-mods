package com.minecraftplus.modTrappedLadder;

import net.minecraft.init.Blocks;
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
		par1Registry.addShapedRecipe(new ItemStack(MCP_TrappedLadder.ladderTrapped, 1), new Object[] {
			"#X",
			Character.valueOf('#'), Blocks.ladder,
			Character.valueOf('X'), Blocks.tripwire_hook});
		par1Registry.addShapedRecipe(new ItemStack(MCP_TrappedLadder.ladderTrapped, 1), new Object[] {
			"X#",
			Character.valueOf('#'), Blocks.ladder,
			Character.valueOf('X'), Blocks.tripwire_hook});
	}
}
