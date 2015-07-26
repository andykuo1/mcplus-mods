package net.minecraftplus.mcp_beetroot;

import net.minecraft.item.Item;
import net.minecraftplus._api.minecraft.base.BlockCropsBase;

public class BlockBeetroot extends BlockCropsBase
{
	@Override
	protected Item getSeed()
	{
		return _Beetroot.beetrootSeeds;
	}

	@Override
	protected Item getCrop()
	{
		return _Beetroot.beetroot;
	}
}
