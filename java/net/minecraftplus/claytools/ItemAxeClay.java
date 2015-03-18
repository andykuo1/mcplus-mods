package net.minecraftplus.claytools;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

public class ItemAxeClay extends ItemToolClay
{
	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});

	public ItemAxeClay(ToolMaterial par1ToolMaterial)
	{
		super(3.0F, par1ToolMaterial, blocksEffectiveAgainst);
	}

	@Override
	public float func_150893_a(ItemStack parItemStack, Block parBlock)
	{
		if ((parBlock.getMaterial() == Material.wood) || (parBlock.getMaterial() == Material.plants) || (parBlock.getMaterial() == Material.vine))
		{
			return this.efficiencyOnProperMaterial;
		}

		return super.func_150893_a(parItemStack, parBlock);
	}
}
