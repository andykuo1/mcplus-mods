package com.minecraftplus.modGems;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.minecraftplus._base.worldgen.IWorldGenNether;
import com.minecraftplus._base.worldgen.WorldGenBlock;

public class WorldGenBlockAmethyst extends WorldGenBlock implements IWorldGenNether
{
	public WorldGenBlockAmethyst()
	{
		super(MCP_Gems.amethystOre);
	}

	public void generateNether(World par1World, Random par2Random, int par3, int par4)
	{
		for (int i = 0; i < 8; i++)
		{
			int randPosX = par3 + par2Random.nextInt(16);
			int randPosY = par2Random.nextInt(par1World.getActualHeight());
			int randPosZ = par4 + par2Random.nextInt(16);

			new WorldGenMinable(this.block, 6).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
		}
	}
}