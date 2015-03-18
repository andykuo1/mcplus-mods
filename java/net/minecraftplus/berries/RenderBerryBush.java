package net.minecraftplus.berries;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftplus._api.handler.RenderSimpleBlockHandler;

public class RenderBerryBush extends RenderSimpleBlockHandler
{
	@Override
	public void renderInventoryBlock(Block parBlock, int parMetadata, int parRenderID, RenderBlocks parRenderBlock) {}

	@Override
	public boolean renderWorldBlock(IBlockAccess parBlockAccess, int parBlockX, int parBlockY, int parBlockZ, Block parBlock, int parRenderID, RenderBlocks parRenderBlock)
	{
		if (parBlock == null) return false;
		if (parRenderID == this.getRenderId())
		{
			Tessellator tessellator = Tessellator.instance;
			tessellator.setBrightness(parBlock.getMixedBrightnessForBlock(parBlockAccess, parBlockX, parBlockY, parBlockZ));
			int l = parBlock.colorMultiplier(parBlockAccess, parBlockX, parBlockY, parBlockZ);
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
			double d1 = (double)parBlockX;
			double d2 = (double)parBlockY;
			double d0 = (double)parBlockZ;
			long i1;

			i1 = (long)(parBlockX * 3129871) ^ (long)parBlockZ * 116129781L ^ (long)parBlockY;
			i1 = i1 * i1 * 42317861L + i1 * 11L;
			d1 += ((double)((float)(i1 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
			d2 += ((double)((float)(i1 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
			d0 += ((double)((float)(i1 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;

			parRenderBlock.drawCrossedSquares(((BlockBerryBush) parBlock).getIcon(-1), d1, d2, d0, 1.2F);

			if (parBlockAccess.getBlockMetadata(parBlockX, parBlockY, parBlockZ) != 0)
			{
				IIcon iicon = ((BlockBerryBush) parBlock).getIcon(parBlockAccess.getBlockMetadata(parBlockX, parBlockY, parBlockZ));
				parRenderBlock.drawCrossedSquares(iicon, d1 + 0.06D, d2, d0, 1.0F);
				parRenderBlock.drawCrossedSquares(iicon, d1 - 0.06D, d2, d0, 1.0F);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int parRenderID)
	{
		return false;
	}

}
