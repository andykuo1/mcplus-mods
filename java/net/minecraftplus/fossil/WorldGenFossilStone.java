package net.minecraftplus.fossil;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftplus._api.handler.WorldGenHandler;

public class WorldGenFossilStone extends WorldGenHandler
{
	@Override
	public void generateSurface(World par1World, Random par2Random, int par3, int par4)
	{
		if(!par1World.getBiomeGenForCoords(par3, par4).isHighHumidity())
		{
			for (int i = 0; i < 12; i++)
			{
				int randPosX = par3 + par2Random.nextInt(16);
				int randPosY = par2Random.nextInt(60);
				int randPosZ = par4 + par2Random.nextInt(16);

				new WorldGenMinable(ModFossil.fossilStone, 4).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
			}
		}
		else
		{
			for (int i = 0; i < 8; i++)
			{
				int randPosX = par3 + par2Random.nextInt(16);
				int randPosY = par2Random.nextInt(40);
				int randPosZ = par4 + par2Random.nextInt(16);

				new WorldGenMinable(ModFossil.fossilStone, 4).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
			}
		}
	}

	@Override
	public void generateNether(World parWorld, Random parRandom, int parChunkX, int parChunkZ) {}

	@Override
	public void generateEnd(World parWorld, Random parRandom, int parChunkX, int parChunkZ) {}

	@Override
	public int getGenWeight()
	{
		return 1;
	}
}