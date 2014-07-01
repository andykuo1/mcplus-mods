package com.minecraftplus.modLooseStone;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.minecraftplus._base.worldgen.IWorldGenSurface;
import com.minecraftplus._base.worldgen.WorldGenBlock;

public class WorldGenBlockLooseStone extends WorldGenBlock implements IWorldGenSurface
{
	public WorldGenBlockLooseStone()
	{
		super(MCP_LooseStone.looseStone);
	}

	@Override
	public void generateSurface(World par1World, Random par2Random, int par3, int par4)
	{
		for (int i = 0; i < 12; i++)
		{
			int randPosX = par3 + par2Random.nextInt(16);
			int randPosY = par2Random.nextInt(60);
			int randPosZ = par4 + par2Random.nextInt(16);

			new WorldGenMinable(this.block, 24).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
			new WorldGenMinable(Blocks.air, 16).generate(par1World, par2Random, randPosX, randPosY, randPosZ);
		}
	}
}
