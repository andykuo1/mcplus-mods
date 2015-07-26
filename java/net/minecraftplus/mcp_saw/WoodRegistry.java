package net.minecraftplus.mcp_saw;

import java.util.Iterator;
import java.util.Map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.util.collection.SmallMap;

public class WoodRegistry
{
	public static final WoodRegistry INSTANCE = new WoodRegistry();
	private WoodRegistry() {}

	private final Map<IBlockState, ItemStack> plankMap = new SmallMap<IBlockState, ItemStack>();

	public void put(IBlockState parBlockState, ItemStack parItemStack)
	{
		assert(parBlockState != null);
		assert(parItemStack != null);

		plankMap.put(parBlockState, parItemStack);
	}

	public ItemStack get(IBlockState parBlockState)
	{
		return plankMap.get(parBlockState);
	}

	public Iterator<IBlockState> iterator()
	{
		return plankMap.keySet().iterator();
	}
}
