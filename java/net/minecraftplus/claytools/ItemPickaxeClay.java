package net.minecraftplus.claytools;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Sets;

public class ItemPickaxeClay extends ItemToolClay
{
	private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});

	protected ItemPickaxeClay(ToolMaterial par1ToolMaterial)
	{
		super(2.0F, par1ToolMaterial, blocksEffectiveAgainst);
	}

	@Override
	public boolean canHarvestBlock(Block parBlock, ItemStack parItemStack)
	{
		if (parBlock == Blocks.obsidian) return this.toolMaterial.getHarvestLevel() == 3;
		if ((parBlock == Blocks.diamond_block) || (parBlock == Blocks.diamond_ore)) return this.toolMaterial.getHarvestLevel() >= 2;
		if ((parBlock == Blocks.emerald_ore) || (parBlock == Blocks.emerald_block)) return this.toolMaterial.getHarvestLevel() >= 2;
		if ((parBlock == Blocks.gold_block) || (parBlock == Blocks.gold_ore)) return this.toolMaterial.getHarvestLevel() >= 2;
		if ((parBlock == Blocks.iron_block) || (parBlock == Blocks.iron_ore)) return this.toolMaterial.getHarvestLevel() >= 1;
		if ((parBlock == Blocks.lapis_block) || (parBlock == Blocks.lapis_ore)) return this.toolMaterial.getHarvestLevel() >= 1;
		if ((parBlock == Blocks.redstone_ore) || (parBlock == Blocks.lit_redstone_ore)) return this.toolMaterial.getHarvestLevel() >= 2;
		if (parBlock.getMaterial() == Material.iron) return true;
		if (parBlock.getMaterial() == Material.anvil) return true;
		if (parBlock.getMaterial() == Material.rock) return true;
		return false;
	}

	@Override
	public float func_150893_a(ItemStack par1ItemStack, Block par2Block)
	{
		if ((par2Block.getMaterial() == Material.iron) || (par2Block.getMaterial() == Material.rock) || (par2Block.getMaterial() == Material.anvil))
		{
			return this.efficiencyOnProperMaterial;
		}

		return super.func_150893_a(par1ItemStack, par2Block);
	}
}
