package com.minecraftplus.modClayWallSlab;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class BlockClayWallSlab1 extends BlockClayWallSlab
{
	public BlockClayWallSlab1(boolean par1, Material par2Material, boolean par3)
	{
		super(par1, par2Material, par3);
	}

	public int getColorRange()
	{
		return 8;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition par1MovingObjectPosition, World par2World, int par3, int par4, int par5)
	{
		return new ItemStack(MCP_ClayWallSlab.clayWallHalfSlabNS1, 1, (par2World.getBlockMetadata(par3, par4, par5) & 7) + this.getColorRange());
	}
}
