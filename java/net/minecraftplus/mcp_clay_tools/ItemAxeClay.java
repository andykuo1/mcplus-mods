package net.minecraftplus.mcp_clay_tools;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

public class ItemAxeClay extends ItemToolClay
{
	//Compare To: @ItemAxe
	private static final Set EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block, Blocks.ladder});

	protected ItemAxeClay(Item.ToolMaterial material)
	{
		super(3.0F, material, EFFECTIVE_ON);
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block)
	{
		//Compare To: @ItemAxe
		return block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine ? super.getStrVsBlock(stack, block) : this.efficiencyOnProperMaterial;
	}
}
