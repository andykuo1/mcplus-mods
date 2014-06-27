package com.minecraftplus._base.worldgen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenHandler implements IWorldGenerator
{
	private ArrayList<WorldGenBlock> worldGenList = new ArrayList<WorldGenBlock>();

	@Override
	public void generate(Random par1Random, int par2, int par3, World par4World, IChunkProvider par5ChunkGenerator, IChunkProvider par6ChunkProvider)
	{
		switch (par4World.provider.dimensionId)
		{
		case -1:
			generateNether(par4World, par1Random, par2 * 16, par3 * 16);
			break;
		case 0:
			generateSurface(par4World, par1Random, par2 * 16, par3 * 16);
			break;
		case 1:
			generateEnd(par4World, par1Random, par2 * 16, par3 * 16);
		}
	}

	public void generateNether(World par1World, Random par2Random, int par3, int par4)
	{
		for(WorldGenBlock worldgen : this.worldGenList)
		{
			if (worldgen instanceof IWorldGenNether)
			{
				((IWorldGenNether) worldgen).generateNether(par1World, par2Random, par3, par4);
			}
		}
	}

	public void generateSurface(World par1World, Random par2Random, int par3, int par4)
	{
		for(WorldGenBlock worldgen : this.worldGenList)
		{
			if (worldgen instanceof IWorldGenSurface)
			{
				((IWorldGenSurface) worldgen).generateSurface(par1World, par2Random, par3, par4);
			}
		}
	}

	public void generateEnd(World par1World, Random par2Random, int par3, int par4)
	{
		for(WorldGenBlock worldgen : this.worldGenList)
		{
			if (worldgen instanceof IWorldGenEnd)
			{
				((IWorldGenEnd) worldgen).generateEnd(par1World, par2Random, par3, par4);
			}
		}
	}

	public void add(WorldGenBlock par1WorldGenBlock)
	{
		this.worldGenList.add(par1WorldGenBlock);
	}
	
	public void remove(WorldGenBlock par1WorldGenBlock)
	{
		this.worldGenList.remove(par1WorldGenBlock);
	}
}
