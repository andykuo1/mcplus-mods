package net.minecraftplus.mcp_clay_tools;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.google.common.collect.Sets;

public class ItemSpadeClay extends ItemToolClay
{
	//Compare To: @ItemSpade
	private static final Set EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.clay, Blocks.dirt, Blocks.farmland, Blocks.grass, Blocks.gravel, Blocks.mycelium, Blocks.sand, Blocks.snow, Blocks.snow_layer, Blocks.soul_sand});

	public ItemSpadeClay(Item.ToolMaterial material)
	{
		super(1.0F, material, EFFECTIVE_ON);
	}

	@Override
	public boolean canHarvestBlock(Block blockIn)
	{
		//Compare To: @ItemSpade
		return blockIn == Blocks.snow_layer ? true : blockIn == Blocks.snow;
	}
}
