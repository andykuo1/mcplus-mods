package net.minecraftplus._api.minecraft.base;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftplus._api.dictionary.WorldGens;

public class WorldGenOreBase implements IWorldGenerator
{
	private final IBlockState oreBlock;
	private final int veinSize;
	private final int spawnFrequency;
	private final int dimensionID;

	private int spawnHeight = -1;

	public WorldGenOreBase(IBlockState parBlock, int parSpawnFrequency, int parVeinSize, int parDimensionID)
	{
		this.oreBlock = parBlock;
		this.spawnFrequency = parSpawnFrequency;
		this.veinSize = parVeinSize;
		this.dimensionID = parDimensionID;
	}

	public WorldGenOreBase setSpawnHeight(int parHeight)
	{
		this.spawnHeight = parHeight;
		return this;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.getDimensionId() == this.dimensionID)
		{
			WorldGens.SPAWN_ORE(this.oreBlock, world, random, chunkX * 16, chunkZ * 16, this.veinSize, this.veinSize, this.spawnFrequency, 0, this.spawnHeight < 0 ? world.getActualHeight() : this.spawnHeight);
		}
	}
}
