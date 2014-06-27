package com.minecraftplus.modLoom;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus.modWheel.MCP_Wheel;

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
		par1Registry.addTileEntity(TileEntityLoom.class, "wooden_loom");
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addShapedRecipe(new ItemStack(MCP_Loom.loom, 1, 0), new Object[] {
			"#X ","###",
			Character.valueOf('#'), Blocks.planks,
			Character.valueOf('X'), MCP_Wheel.wheel});
		par1Registry.addShapedRecipe(new ItemStack(MCP_Loom.loom, 1, 0), new Object[] {
			" X#","###",
			Character.valueOf('#'), Blocks.planks,
			Character.valueOf('X'), MCP_Wheel.wheel});
	}
}
