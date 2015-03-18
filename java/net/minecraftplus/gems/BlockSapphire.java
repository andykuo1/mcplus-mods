package net.minecraftplus.gems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;

public class BlockSapphire extends BlockCompressed
{
	public BlockSapphire()
	{
		super(MapColor.blueColor);

		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypePiston);

		this.setCreativeTab(CreativeTabs.tabBlock);
	}
}
