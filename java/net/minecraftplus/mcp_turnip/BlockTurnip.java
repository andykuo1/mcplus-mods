package net.minecraftplus.mcp_turnip;

import net.minecraft.item.Item;
import net.minecraftplus._api.minecraft.base.BlockCropsBase;

public class BlockTurnip extends BlockCropsBase
{
	@Override
	protected Item getSeed()
	{
		return _Turnip.turnipSeeds;
	}

	@Override
	protected Item getCrop()
	{
		return _Turnip.turnip;
	}
}
