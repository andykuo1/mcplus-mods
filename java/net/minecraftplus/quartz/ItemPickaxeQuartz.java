package net.minecraftplus.quartz;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.google.common.collect.Sets;

public class ItemPickaxeQuartz extends ItemToolQuartz
{
	public ItemPickaxeQuartz(ToolMaterial par1ToolMaterial)
	{
		super(2.0F, par1ToolMaterial, Sets.newHashSet(new Block[] {
				Blocks.netherrack,
				Blocks.nether_brick,
				Blocks.nether_brick_stairs,
				Blocks.nether_brick_fence,
				Blocks.quartz_ore,
				Blocks.quartz_block,
				Blocks.quartz_stairs,
				Blocks.obsidian}));
	}
}
