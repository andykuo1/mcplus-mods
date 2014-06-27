package com.minecraftplus.modFossil;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.minecraftplus._base.worldgen.IWorldGenSurface;
import com.minecraftplus._base.worldgen.WorldGenBlock;

public class WorldGenBlockFossil extends WorldGenBlock implements IWorldGenSurface
{
	public WorldGenBlockFossil()
	{
		super(MCP_Fossil.fossilStone);
	}

	public void generateSurface(World par1World, Random par2Random, int par3, int par4)
	{
		if(!par1World.getBiomeGenForCoords(par3, par4).isHighHumidity())
		{
			for (int i = 0; i < 12; i++)
			{
				int randPosX = par3 + par2Random.nextInt(16);
				int randPosY = par2Random.nextInt(60);
				int randPosZ = par4 + par2Random.nextInt(16);

				new WorldGenMinable(this.block, 4).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
			}
		}
		else
		{
			for (int i = 0; i < 8; i++)
			{
				int randPosX = par3 + par2Random.nextInt(16);
				int randPosY = par2Random.nextInt(40);
				int randPosZ = par4 + par2Random.nextInt(16);

				new WorldGenMinable(this.block, 4).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
			}
		}
	}
}