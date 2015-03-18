package net.minecraftplus._api.handler;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public abstract class WorldGenHandler implements IWorldGenerator
{
	@Override
	public void generate(Random parRandom, int parChunkX, int parChunkZ, World parWorld, IChunkProvider parChunkGenerator, IChunkProvider parCahunkProvider)
	{
		switch (parWorld.provider.dimensionId)
		{
		case -1:
			this.generateNether(parWorld, parRandom, parChunkX * 16, parChunkZ * 16);
			break;
		case 0:
			this.generateSurface(parWorld, parRandom, parChunkX * 16, parChunkZ * 16);
			break;
		case 1:
			this.generateEnd(parWorld, parRandom, parChunkX * 16, parChunkZ * 16);
		}
	}

	public abstract void generateNether(World parWorld, Random parRandom, int parChunkX, int parChunkZ);
	public abstract void generateSurface(World parWorld, Random parRandom, int parChunkX, int parChunkZ);
	public abstract void generateEnd(World parWorld, Random parRandom, int parChunkX, int parChunkZ);
	public abstract int getGenWeight();
}
