package com.minecraftplus.modTrappedLadder;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_TrappedLadder.ladderTrapped, 1), new Object[] {
			"#X",
			Character.valueOf('#'), Blocks.ladder,
			Character.valueOf('X'), Blocks.tripwire_hook});
		ModRegistry.addShapedRecipe(new ItemStack(MCP_TrappedLadder.ladderTrapped, 1), new Object[] {
			"X#",
			Character.valueOf('#'), Blocks.ladder,
			Character.valueOf('X'), Blocks.tripwire_hook});
	}
}
