package com.minecraftplus.modSkullCandle;

import net.minecraft.init.Blocks;

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
		par1Registry.addTileEntity(TileEntitySkullCandle.class, "skull_candles");
		par1Registry.addTileEntity(TileEntityRedstoneSkullCandle.class, "redstone_skull_candles");
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addRecipe(new RecipesSkullCandle(Blocks.torch, MCP_SkullCandle.skullCandle));
		par1Registry.addRecipe(new RecipesSkullCandle(Blocks.redstone_torch, MCP_SkullCandle.redstoneSkullCandle));
	}
}
