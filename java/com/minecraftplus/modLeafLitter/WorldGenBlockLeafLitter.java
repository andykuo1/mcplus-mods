package com.minecraftplus.modLeafLitter;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.TerrainGen;

import com.minecraftplus._base.worldgen.IWorldGenSurface;
import com.minecraftplus._base.worldgen.WorldGenBlock;

public class WorldGenBlockLeafLitter extends WorldGenBlock implements IWorldGenSurface
{
	public WorldGenBlockLeafLitter()
	{
		super(MCP_LeafLitter.leafLitter);
	}

	public void generateSurface(World par1World, Random par2Random, int par3, int par4)
	{
		BiomeGenBase biome = par1World.getBiomeGenForCoords(par3, par4);
		boolean doGen = TerrainGen.decorate(par1World, par2Random, par3, par4, FLOWERS);
		for (int j = 0; doGen && j < 1; ++j)
		{
			int k = par3 + par2Random.nextInt(16) + 8;
			int l = par4 + par2Random.nextInt(16) + 8;
			int i1 = 40 + par2Random.nextInt(par1World.getHeightValue(k, l) + 32);
			WorldGenFlowers leafLitterGen = new WorldGenFlowers(this.block);
			leafLitterGen.func_150550_a(this.block, par2Random.nextInt(4));
			leafLitterGen.generate(par1World, par2Random, k, i1, l);
		}
	}
}