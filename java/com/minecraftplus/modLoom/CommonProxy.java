package com.minecraftplus.modLoom;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;
import com.minecraftplus.modWheel.MCP_Wheel;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addTileEntity(TileEntityLoom.class, "wooden_loom");

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Loom.loom, 1, 0), new Object[] {
			"#X ","###",
			Character.valueOf('#'), Blocks.planks,
			Character.valueOf('X'), MCP_Wheel.wheel});
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Loom.loom, 1, 0), new Object[] {
			" X#","###",
			Character.valueOf('#'), Blocks.planks,
			Character.valueOf('X'), MCP_Wheel.wheel});
	}
}
