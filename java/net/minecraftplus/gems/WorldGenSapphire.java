package net.minecraftplus.gems;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftplus._api.handler.WorldGenHandler;

public class WorldGenSapphire extends WorldGenHandler
{
	@Override
	public void generateNether(World parWorld, Random parRandom, int parChunkX, int parChunkZ)
	{

	}

	@Override
	public void generateSurface(World parWorld, Random parRandom, int parChunkX, int parChunkZ)
	{
		for (int i = 0; i < 4; i++)
		{
			int randPosX = parChunkX + parRandom.nextInt(16);
			int randPosY = parRandom.nextInt(70);
			int randPosZ = parChunkZ + parRandom.nextInt(16);

			new WorldGenMinable(ModGems.sapphireOre, 6).generate(parWorld, parRandom, randPosX, randPosY, randPosZ);
		}
	}

	@Override
	public void generateEnd(World parWorld, Random parRandom, int parChunkX, int parChunkZ)
	{

	}

	@Override
	public int getGenWeight()
	{
		return 1;
	}

}
