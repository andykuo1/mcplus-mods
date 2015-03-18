package net.minecraftplus.quartz;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import com.google.common.collect.Sets;

public class ItemAxeQuartz extends ItemToolQuartz
{
	public ItemAxeQuartz(ToolMaterial par1ToolMaterial)
	{
		super(3.0F, par1ToolMaterial, Sets.newHashSet(new Block[] {
				Blocks.nether_brick_fence}));
	}

	public boolean isBlockEffective(Block par1Block)
	{
		return par1Block.getMaterial() == Material.wood  || super.isBlockEffective(par1Block);
	}
}
