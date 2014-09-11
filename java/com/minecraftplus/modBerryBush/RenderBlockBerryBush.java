package com.minecraftplus.modBerryBush;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.minecraftplus._client.RenderBlock;

public class RenderBlockBerryBush extends RenderBlock
{
	@Override
	public boolean renderInWorld(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, Block par5Block)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(par5Block.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
		int l = par5Block.colorMultiplier(this.blockAccess, par2, par3, par4);
		float f = (float)(l >> 16 & 255) / 255.0F;
		float f1 = (float)(l >> 8 & 255) / 255.0F;
		float f2 = (float)(l & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable)
		{
			float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
			float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
			float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
			f = f3;
			f1 = f4;
			f2 = f5;
		}

		tessellator.setColorOpaque_F(f, f1, f2);
		double d1 = (double)par2;
		double d2 = (double)par3;
		double d0 = (double)par4;
		long i1;

		i1 = (long)(par2 * 3129871) ^ (long)par4 * 116129781L ^ (long)par3;
		i1 = i1 * i1 * 42317861L + i1 * 11L;
		d1 += ((double)((float)(i1 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
		d2 += ((double)((float)(i1 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
		d0 += ((double)((float)(i1 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;

		IIcon iicon = ((BlockBerryBush) par5Block).getIcon(this.blockAccess.getBlockMetadata(par2, par3, par4));
		this.drawCrossedSquares(((BlockBerryBush) par5Block).getIcon(-1), d1, d2, d0, 1.325F);
		this.drawCrossedSquares(iicon, d1 + 0.06D, d2, d0, 1.325F);
		this.drawCrossedSquares(iicon, d1 - 0.06D, d2, d0, 1.325F);
		return true;
	}
}
