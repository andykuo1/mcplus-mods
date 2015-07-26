package net.minecraftplus._api.dictionary;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public final class WorldGens
{
	private WorldGens() {}

	public static void SPAWN_ORE(IBlockState parBlock, World parWorld, Random parRandom, int parBlockX, int parBlockZ, int parMinVeinSize, int parMaxVeinSize, int parSpawnFrequency, int parMinY, int parMaxY )
	{
		assert(parMaxVeinSize >= parMinVeinSize);
		assert(parMaxY >= parMinY);

		if (parMinVeinSize == parMaxVeinSize) parMinVeinSize = parMaxVeinSize - 1;
		if (parMinY == parMaxY) parMaxY = parMinY - 1;
		
		for(int i = 0; i < parSpawnFrequency; i++)
		{
			BlockPos pos = new BlockPos(
					parBlockX + parRandom.nextInt(16),
					parMinY + parRandom.nextInt(parMaxY - parMinY),
					parBlockZ + parRandom.nextInt(16)
					);
			new WorldGenMinable(parBlock, (parMinVeinSize + parRandom.nextInt(parMaxVeinSize - parMinVeinSize))).generate(parWorld, parRandom, pos);
		}
	}
}
