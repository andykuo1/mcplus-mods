package net.minecraftplus.claytools;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

public class ItemSpadeClay extends ItemToolClay
{
	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});

	public ItemSpadeClay(Item.ToolMaterial par1ToolMaterial)
	{
		super(1.0F, par1ToolMaterial, blocksEffectiveAgainst);
	}

	@Override
	public boolean canHarvestBlock(Block parBlock, ItemStack parItemStack)
	{
		if (parBlock == Blocks.snow_layer) return true;
		if (parBlock == Blocks.snow) return true;
		return false;
	}
}
