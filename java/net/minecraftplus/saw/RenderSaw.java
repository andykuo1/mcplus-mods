package net.minecraftplus.saw;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftplus._api.handler.RenderSimpleBlockHandler;

import org.lwjgl.opengl.GL11;

public class RenderSaw extends RenderSimpleBlockHandler
{
	@Override
	public void renderInventoryBlock(Block parBlock, int parMetadata, int parRenderID, RenderBlocks parRenderBlock)
	{
		TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
		texturemanager.bindTexture(texturemanager.getResourceLocation(0));
		this.renderBlockAsItem(parBlock, 1, 1F, parRenderBlock);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess parBlockAccess, int parBlockX, int parBlockY, int parBlockZ, Block parBlock, int parRenderID, RenderBlocks parRenderBlock)
	{
		parBlock.setBlockBoundsBasedOnState(parBlockAccess, parBlockX, parBlockY, parBlockZ);
		parRenderBlock.setRenderBoundsFromBlock(parBlock);

		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		int k = parBlockAccess.getBlockMetadata(parBlockX, parBlockY, parBlockZ) & 7;
		GL11.glRotatef(45, 1F, 0F, 0F);
		this.renderBlockLog(parBlockAccess, parBlock, parBlockX, parBlockY, parBlockZ, parRenderBlock);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		GL11.glPopMatrix();

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int parRenderID)
	{
		return true;
	}

	private boolean renderBlockLog(IBlockAccess parBlockAccess, Block p_147742_1_, int p_147742_2_, int p_147742_3_, int p_147742_4_, RenderBlocks parRenderBlock)
	{
		int l = parBlockAccess.getBlockMetadata(p_147742_2_, p_147742_3_, p_147742_4_);
		int i1 = l & 7;

		System.out.println(i1);
		
		if (i1 == 0)
		{
			parRenderBlock.uvRotateSouth = 3;
			parRenderBlock.uvRotateEast = 3;
			parRenderBlock.uvRotateWest = 3;
			parRenderBlock.uvRotateNorth = 3;
		}
		else if (i1 == 1)
		{
			
		}
		else if (i1 == 2)
		{
			parRenderBlock.uvRotateSouth = 1;
			parRenderBlock.uvRotateNorth = 2;
		}
		else if (i1 == 3)
		{
			parRenderBlock.uvRotateSouth = 2;
			parRenderBlock.uvRotateNorth = 1;
			parRenderBlock.uvRotateTop = 3;
			parRenderBlock.uvRotateBottom = 3;
		}
		else if (i1 == 4)
		{
			parRenderBlock.uvRotateEast = 1;
			parRenderBlock.uvRotateWest = 2;
			parRenderBlock.uvRotateTop = 2;
			parRenderBlock.uvRotateBottom = 1;
		}
		else if (i1 == 5)
		{
			parRenderBlock.uvRotateEast = 2;
			parRenderBlock.uvRotateWest = 1;
			parRenderBlock.uvRotateTop = 1;
			parRenderBlock.uvRotateBottom = 2;
		}

		boolean flag = parRenderBlock.renderStandardBlock(p_147742_1_, p_147742_2_, p_147742_3_, p_147742_4_);
		parRenderBlock.uvRotateSouth = 0;
		parRenderBlock.uvRotateEast = 0;
		parRenderBlock.uvRotateWest = 0;
		parRenderBlock.uvRotateNorth = 0;
		parRenderBlock.uvRotateTop = 0;
		parRenderBlock.uvRotateBottom = 0;
		return flag;
	}

	private void renderBlockAsItem(Block par1Block, int par2Metadata, float par3, RenderBlocks parRenderBlock)
	{
		Tessellator tessellator = Tessellator.instance;
		boolean flag = par1Block == Blocks.grass;

		if (par1Block == Blocks.dispenser || par1Block == Blocks.dropper || par1Block == Blocks.furnace)
		{
			par2Metadata = 3;
		}

		int j;
		float f1;
		float f2;
		float f3;

		if (parRenderBlock.useInventoryTint)
		{
			j = par1Block.getRenderColor(par2Metadata);

			if (flag)
			{
				j = 16777215;
			}

			f1 = (float)(j >> 16 & 255) / 255.0F;
			f2 = (float)(j >> 8 & 255) / 255.0F;
			f3 = (float)(j & 255) / 255.0F;
			GL11.glColor4f(f1 * 1F, f2 * 1F, f3 * 1F, 1.0F);
		}

		j = par1Block.getRenderType();
		parRenderBlock.setRenderBoundsFromBlock(par1Block);
		int k;

		par1Block.setBlockBoundsForItemRender();
		parRenderBlock.setRenderBoundsFromBlock(par1Block);
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		parRenderBlock.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, parRenderBlock.getBlockIconFromSideAndMetadata(par1Block, 0, par2Metadata));
		tessellator.draw();

		if (flag && parRenderBlock.useInventoryTint)
		{
			k = par1Block.getRenderColor(par2Metadata);
			f2 = (float)(k >> 16 & 255) / 255.0F;
			f3 = (float)(k >> 8 & 255) / 255.0F;
			float f4 = (float)(k & 255) / 255.0F;
			GL11.glColor4f(f2 * 1F, f3 * 1F, f4 * 1F, 1.0F);
		}

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		parRenderBlock.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, parRenderBlock.getBlockIconFromSideAndMetadata(par1Block, 1, par2Metadata));
		tessellator.draw();

		if (flag && parRenderBlock.useInventoryTint)
		{
			GL11.glColor4f(1F, 1F, 1F, 1.0F);
		}

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		parRenderBlock.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, parRenderBlock.getBlockIconFromSideAndMetadata(par1Block, 2, par2Metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		parRenderBlock.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, parRenderBlock.getBlockIconFromSideAndMetadata(par1Block, 3, par2Metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		parRenderBlock.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, parRenderBlock.getBlockIconFromSideAndMetadata(par1Block, 4, par2Metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		parRenderBlock.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, parRenderBlock.getBlockIconFromSideAndMetadata(par1Block, 5, par2Metadata));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}
