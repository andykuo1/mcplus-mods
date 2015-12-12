package net.minecraftplus.mcp_loose_stone;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftplus._api.minecraft.base.WorldGenBase;

public class WorldGenLooseStone extends WorldGenBase
{
	@Override
	public void generateSurface(World parWorld, Random parRandom, int parBlockX, int parBlockZ)
	{
		for(int i = 0; i < 14; i++)
		{
			BlockPos pos = new BlockPos(
					parBlockX + parRandom.nextInt(16),
					parRandom.nextInt(60) + 4,
					parBlockZ + parRandom.nextInt(16)
					);
			new WorldGenMinable(_Loose_Stone.looseStone.getDefaultState(), 24).generate(parWorld, parRandom, pos);
			new WorldGenMinable(Blocks.air.getDefaultState(), 16).generate(parWorld, parRandom, pos.down(2));
		}
	}

	@Override
	public void generateNether(World parWorld, Random parRandom, int parBlockX, int parBlockZ) {}

	@Override
	public void generateEnd(World parWorld, Random parRandom, int parBlockX, int parBlockZ) {}
}
