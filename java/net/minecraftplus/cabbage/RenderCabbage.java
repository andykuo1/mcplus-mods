package net.minecraftplus.cabbage;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftplus._api.handler.RenderSimpleBlockHandler;

public class RenderCabbage extends RenderSimpleBlockHandler
{
	@Override
	public void renderInventoryBlock(Block parBlock, int parMetadata, int parRenderID, RenderBlocks parRenderBlock) {}

	@Override
	public boolean renderWorldBlock(IBlockAccess parBlockAccess, int parBlockX, int parBlockY, int parBlockZ, Block parBlock, int parRenderID, RenderBlocks parRenderBlock)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(parBlock.getMixedBrightnessForBlock(parBlockAccess, parBlockX, parBlockY, parBlockZ));
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

		int j = parBlockAccess.getBlockMetadata(parBlockX, parBlockY, parBlockZ);
		double d0 = (double)parBlockX;
		double d1 = (double)((float)parBlockY - 0.0625F);
		double d2 = (double)parBlockZ;
		IIcon iicon = parRenderBlock.getBlockIconFromSideAndMetadata(parBlock, 0, j);

		if (parRenderBlock.hasOverrideBlockTexture())
		{
			iicon = parRenderBlock.overrideBlockTexture;
		}

		double d3 = (double)iicon.getMinU();
		double d4 = (double)iicon.getMinV();
		double d5 = (double)iicon.getMaxU();
		double d6 = (double)iicon.getMaxV();
		double d7 = d0 + 0.5D - 0.25D;
		double d8 = d0 + 0.5D + 0.25D;
		double d9 = d2 + 0.5D - 0.5D;
		double d10 = d2 + 0.5D + 0.5D;

		tessellator.setBrightness((int) (parBlock.getMixedBrightnessForBlock(parBlockAccess, parBlockX, parBlockY, parBlockZ) * 0.9D));
		for(int i = 0; i < 2; i++)
		{
			d9 = d2 + 0.5D + (i % 2 == 0 ? -0.5D: 0.5D);
			d10 = d2 + 0.5D + (i % 2 == 0 ? 0.5D: -0.5D);
			tessellator.addVertexWithUV(d7 - 0.5D, d1 + 1.0D, d9, d3, d4);
			tessellator.addVertexWithUV(d7, d1 + 0.0D, d9, d3, d6);
			tessellator.addVertexWithUV(d7, d1 + 0.0D, d10, d5, d6);
			tessellator.addVertexWithUV(d7 - 0.5D, d1 + 1.0D, d10, d5, d4);

			tessellator.addVertexWithUV(d8 + 0.5D, d1 + 1.0D, d10, d3, d4);
			tessellator.addVertexWithUV(d8, d1 + 0.0D, d10, d3, d6);
			tessellator.addVertexWithUV(d8, d1 + 0.0D, d9, d5, d6);
			tessellator.addVertexWithUV(d8 + 0.5D, d1 + 1.0D, d9, d5, d4);
		}

		tessellator.setBrightness(parBlock.getMixedBrightnessForBlock(parBlockAccess, parBlockX, parBlockY, parBlockZ));
		for(int i = 0; i < 2; i++)
		{
			d9 = d2 + 0.5D + (i % 2 == 0 ? -0.5D: 0.5D);
			d10 = d2 + 0.5D + (i % 2 == 0 ? 0.5D: -0.5D);
			double d11 = i % 2 == 0 ? -0.2D : 0.2D;
			tessellator.addVertexWithUV(d7 - 0.1D, d1 + 1.0D, d9, d3, d4);
			tessellator.addVertexWithUV(d7 + 0.2D, d1 + 0.0D, d9 - d11, d3, d6);
			tessellator.addVertexWithUV(d7 + 0.2D, d1 + 0.0D, d10 + d11, d5, d6);
			tessellator.addVertexWithUV(d7 - 0.1D, d1 + 1.0D, d10, d5, d4);

			tessellator.addVertexWithUV(d8 + 0.1D, d1 + 1.0D, d10, d3, d4);
			tessellator.addVertexWithUV(d8 - 0.2D, d1 + 0.0D, d10 + d11, d3, d6);
			tessellator.addVertexWithUV(d8 - 0.2D, d1 + 0.0D, d9 - d11, d5, d6);
			tessellator.addVertexWithUV(d8 + 0.1D, d1 + 1.0D, d9, d5, d4);
		}

		d7 = d0 + 0.5D - 0.5D;
		d8 = d0 + 0.5D + 0.5D;
		d9 = d2 + 0.5D - 0.25D;
		d10 = d2 + 0.5D + 0.25D;

		tessellator.setBrightness((int) (parBlock.getMixedBrightnessForBlock(parBlockAccess, parBlockX, parBlockY, parBlockZ) * 0.9D));
		for(int i = 0; i < 2; i++)
		{
			d7 = d0 + 0.5D + (i % 2 == 0 ? -0.5D: 0.5D);
			d8 = d0 + 0.5D + (i % 2 == 0 ? 0.5D: -0.5D);
			tessellator.addVertexWithUV(d7, d1 + 1.0D, d9 - 0.5D, d3, d4);
			tessellator.addVertexWithUV(d7, d1 + 0.0D, d9, d3, d6);
			tessellator.addVertexWithUV(d8, d1 + 0.0D, d9, d5, d6);
			tessellator.addVertexWithUV(d8, d1 + 1.0D, d9 - 0.5D, d5, d4);

			tessellator.addVertexWithUV(d8, d1 + 1.0D, d10 + 0.5D, d3, d4);
			tessellator.addVertexWithUV(d8, d1 + 0.0D, d10, d3, d6);
			tessellator.addVertexWithUV(d7, d1 + 0.0D, d10, d5, d6);
			tessellator.addVertexWithUV(d7, d1 + 1.0D, d10 + 0.5D, d5, d4);
		}

		tessellator.setBrightness(parBlock.getMixedBrightnessForBlock(parBlockAccess, parBlockX, parBlockY, parBlockZ));
		for(int i = 0; i < 2; i++)
		{
			d7 = d0 + 0.5D + (i % 2 == 0 ? -0.5D: 0.5D);
			d8 = d0 + 0.5D + (i % 2 == 0 ? 0.5D: -0.5D);
			double d11 = i % 2 == 0 ? -0.2D : 0.2D;
			tessellator.addVertexWithUV(d7, d1 + 1.0D, d9 - 0.1D, d3, d4);
			tessellator.addVertexWithUV(d7 - d11, d1 + 0.0D, d9 + 0.2D, d3, d6);
			tessellator.addVertexWithUV(d8 + d11, d1 + 0.0D, d9 + 0.2D, d5, d6);
			tessellator.addVertexWithUV(d8, d1 + 1.0D, d9 - 0.1D, d5, d4);

			tessellator.addVertexWithUV(d8, d1 + 1.0D, d10 + 0.1D, d3, d4);
			tessellator.addVertexWithUV(d8 + d11, d1 + 0.0D, d10 - 0.2D, d3, d6);
			tessellator.addVertexWithUV(d7 - d11, d1 + 0.0D, d10 - 0.2D, d5, d6);
			tessellator.addVertexWithUV(d7, d1 + 1.0D, d10 + 0.1D, d5, d4);
		}

		if (j > 2)
		{
			iicon = parRenderBlock.getBlockIconFromSideAndMetadata(parBlock, 0, -j);
			d3 = (double)iicon.getMinU();
			d4 = (double)iicon.getMinV();
			d5 = (double)iicon.getMaxU();
			d6 = (double)iicon.getMaxV();

			double d11 = (j / 7D) * 0.4D;
			double d12 = 0.25D;
			tessellator.addVertexWithUV(d7, d1 + d11, d9 + 1.0D - d12, d3, d4);
			tessellator.addVertexWithUV(d7, d1 + d11, d9 - d12, d3, d6);
			tessellator.addVertexWithUV(d8, d1 + d11, d9 - d12, d5, d6);
			tessellator.addVertexWithUV(d8, d1 + d11, d9 + 1.0D - d12, d5, d4);
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int parRenderID)
	{
		return false;
	}
}
