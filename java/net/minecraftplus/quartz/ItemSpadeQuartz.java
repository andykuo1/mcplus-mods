package net.minecraftplus.quartz;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.google.common.collect.Sets;

public class ItemSpadeQuartz extends ItemToolQuartz
{
	public ItemSpadeQuartz(ToolMaterial par1ToolMaterial)
	{
		super(1.0F, par1ToolMaterial, Sets.newHashSet(new Block[] {
				Blocks.gravel,
				Blocks.soul_sand}));
	}
}
