package com.minecraftplus.modSkullCandle;

import net.minecraft.init.Blocks;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addTileEntity(TileEntitySkullCandle.class, "skull_candles");
		ModRegistry.addTileEntity(TileEntityRedstoneSkullCandle.class, "redstone_skull_candles");

		ModRegistry.addRecipe(new RecipesSkullCandle(Blocks.torch, MCP_SkullCandle.skullCandle));
		ModRegistry.addRecipe(new RecipesSkullCandle(Blocks.redstone_torch, MCP_SkullCandle.redstoneSkullCandle));
	}
}
