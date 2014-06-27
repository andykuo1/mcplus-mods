package com.minecraftplus.modCopper;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.minecraftplus._base.worldgen.IWorldGenSurface;
import com.minecraftplus._base.worldgen.WorldGenBlock;

public class WorldGenBlockCopper extends WorldGenBlock implements IWorldGenSurface
{
	public WorldGenBlockCopper()
	{
		super(MCP_Copper.copperOre);
	}

	@Override
	public void generateSurface(World par1World, Random par2Random, int par3, int par4)
	{
		for (int i = 0; i < 12; i++)
		{
			int randPosX = par3 + par2Random.nextInt(16);
			int randPosY = par2Random.nextInt(50);
			int randPosZ = par4 + par2Random.nextInt(16);

			new WorldGenMinable(this.block, 6).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
		}
	}
}
