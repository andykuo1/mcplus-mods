package com.minecraftplus.modFoodEssentials;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import com.minecraftplus._common.render.RenderBlock;

public class RenderBlockCabbagePlant extends RenderBlock
{
	@Override
	public boolean renderInWorld(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, Block par5Block)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(par5Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		this.renderCrossedSquares(par5Block, par2, par3, par4);
		return true;
	}
}
