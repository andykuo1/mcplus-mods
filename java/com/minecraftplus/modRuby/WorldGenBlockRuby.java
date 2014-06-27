package com.minecraftplus.modRuby;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.minecraftplus._base.worldgen.IWorldGenSurface;
import com.minecraftplus._base.worldgen.WorldGenBlock;
import com.minecraftplus.modFossil.MCP_Fossil;

public class WorldGenBlockRuby extends WorldGenBlock implements IWorldGenSurface
{
	public WorldGenBlockRuby()
	{
		super(MCP_Ruby.rubyOre);
	}

	public void generateSurface(World par1World, Random par2Random, int par3, int par4)
	{
		for (int i = 0; i < 4; i++)
		{
			int randPosX = par3 + par2Random.nextInt(16);
			int randPosY = par2Random.nextInt(70);
			int randPosZ = par4 + par2Random.nextInt(16);

			new WorldGenMinable(this.block, 2).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
		}
	}
}