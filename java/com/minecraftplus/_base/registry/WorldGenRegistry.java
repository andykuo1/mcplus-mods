package com.minecraftplus._base.registry;

import com.minecraftplus._base.worldgen.WorldGenBlock;
import com.minecraftplus._base.worldgen.WorldGenHandler;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenRegistry
{
	private static WorldGenHandler worldGenHandler = new WorldGenHandler();

	public static void add(WorldGenBlock par1WorldGenBlock)
	{
		worldGenHandler.add(par1WorldGenBlock);
	}

	public static IWorldGenerator getWorldGenHandler()
	{
		return worldGenHandler;
	}
}
