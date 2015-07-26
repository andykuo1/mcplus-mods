package net.minecraftplus._api.minecraft.base;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public abstract class WorldGenBase implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.getDimensionId())
		{
		case -1:
			this.generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 0:
			this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			this.generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	public abstract void generateNether(World parWorld, Random parRandom, int parBlockX, int parBlockZ);
	public abstract void generateSurface(World parWorld, Random parRandom, int parBlockX, int parBlockZ);
	public abstract void generateEnd(World parWorld, Random parRandom, int parBlockX, int parBlockZ);
}
