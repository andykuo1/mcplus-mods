package net.minecraftplus.loosestone;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftplus._api.handler.WorldGenHandler;

public class WorldGenLooseStone extends WorldGenHandler
{
	@Override
	public void generateSurface(World par1World, Random par2Random, int par3, int par4)
	{
		for (int i = 0; i < 14; i++)
		{
			int randPosX = par3 + par2Random.nextInt(16);
			int randPosY = par2Random.nextInt(60) + 2;
			int randPosZ = par4 + par2Random.nextInt(16);

			new WorldGenMinable(ModLooseStone.looseStone, 24).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
			new WorldGenMinable(Blocks.air, 16).generate(par1World, par2Random, randPosX, randPosY - 2, randPosZ);
		}
	}

	@Override
	public void generateNether(World parWorld, Random parRandom, int parChunkX, int parChunkZ) {}

	@Override
	public void generateEnd(World parWorld, Random parRandom, int parChunkX, int parChunkZ) {}

	@Override
	public int getGenWeight()
	{
		return 0;
	}
}
