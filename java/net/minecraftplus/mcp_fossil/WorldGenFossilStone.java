package net.minecraftplus.mcp_fossil;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftplus._api.dictionary.WorldGens;
import net.minecraftplus._api.minecraft.base.WorldGenBase;

public class WorldGenFossilStone extends WorldGenBase
{
	@Override
	public void generateNether(World parWorld, Random parRandom, int parBlockX, int parBlockZ) {}

	@Override
	public void generateSurface(World parWorld, Random parRandom, int parBlockX, int parBlockZ)
	{
		if (parWorld.getBiomeGenForCoords(new BlockPos(parBlockX, 0, parBlockZ)).isHighHumidity())
		{
			WorldGens.SPAWN_ORE(_Fossil.fossilStone.getDefaultState(), parWorld, parRandom, parBlockX, parBlockZ, 4, 4, 12, 0, 60);
		}
		else
		{
			WorldGens.SPAWN_ORE(_Fossil.fossilStone.getDefaultState(), parWorld, parRandom, parBlockX, parBlockZ, 4, 4, 8, 0, 40);
		}
	}

	@Override
	public void generateEnd(World parWorld, Random parRandom, int parBlockX, int parBlockZ) {}

}
