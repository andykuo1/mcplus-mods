package net.minecraftplus.berries;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftplus._api.handler.WorldGenHandler;

public class WorldGenBerryBush extends WorldGenHandler
{
	private final Block block;

	public WorldGenBerryBush(Block parBlock)
	{
		this.block = parBlock;
	}

	@Override
	public void generateNether(World parWorld, Random parRandom, int parChunkX, int parChunkZ) {}

	@Override
	public void generateSurface(World parWorld, Random parRandom, int parChunkX, int parChunkZ)
	{
		BiomeGenBase biome = parWorld.getBiomeGenForCoords(parChunkX, parChunkZ);
		boolean doGen = TerrainGen.decorate(parWorld, parRandom, parChunkX, parChunkZ, FLOWERS);
		if (parRandom.nextBoolean())
		{
			for (int j = 0; doGen && j < 1; ++j)
			{
				int k = parChunkX + parRandom.nextInt(16) + 8;
				int l = parChunkZ + parRandom.nextInt(16) + 8;
				int i1 = 40 + parRandom.nextInt(parWorld.getHeightValue(k, l) + 32);
				WorldGenFlowers berryBushGen = new WorldGenFlowers(this.block);
				berryBushGen.func_150550_a(this.block, parRandom.nextInt(4));
				berryBushGen.generate(parWorld, parRandom, k, i1, l);
			}
		}
	}

	@Override
	public void generateEnd(World parWorld, Random parRandom, int parChunkX, int parChunkZ) {}

	@Override
	public int getGenWeight()
	{
		return 1;
	}
}
