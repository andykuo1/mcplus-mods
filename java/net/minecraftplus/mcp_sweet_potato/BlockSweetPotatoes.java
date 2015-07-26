package net.minecraftplus.mcp_sweet_potato;

import net.minecraft.item.Item;
import net.minecraftplus._api.minecraft.base.BlockCropsBase;

public class BlockSweetPotatoes extends BlockCropsBase
{
	@Override
	protected Item getSeed()
	{
		return _Sweet_Potato.sweetPotato;
	}

	@Override
	protected Item getCrop()
	{
		return _Sweet_Potato.sweetPotato;
	}
}
