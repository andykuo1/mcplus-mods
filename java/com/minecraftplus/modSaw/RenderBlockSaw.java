package com.minecraftplus.modSaw;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.minecraftplus._client.RenderBlock;

public class RenderBlockSaw extends RenderBlock
{
	@Override
	public boolean renderInWorld(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, Block par5Block)
	{
		this.setIBlockAccess(par1IBlockAccess);

		par5Block.setBlockBoundsBasedOnState(par1IBlockAccess, par2, par3, par4);
		this.setRenderBoundsFromBlock(par5Block);

		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		int k = this.blockAccess.getBlockMetadata(par2, par3, par4) & 7;
		GL11.glRotatef(45, 1F, 0F, 0F);
		this.renderBlockLog(par5Block, par2, par3, par4);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		GL11.glPopMatrix();

		this.resetIBlockAccess();
		return true;
	}

	@Override
	public boolean renderBlockLog(Block p_147742_1_, int p_147742_2_, int p_147742_3_, int p_147742_4_)
	{
		int l = this.blockAccess.getBlockMetadata(p_147742_2_, p_147742_3_, p_147742_4_);
		int i1 = l & 7;

		if (i1 == 0)
		{
			this.uvRotateSouth = 3;
			this.uvRotateEast = 3;
			this.uvRotateWest = 3;
			this.uvRotateNorth = 3;
		}
		else if (i1 == 2)
		{
			this.uvRotateSouth = 1;
			this.uvRotateNorth = 2;
		}
		else if (i1 == 3)
		{
			this.uvRotateSouth = 2;
			this.uvRotateNorth = 1;
			this.uvRotateTop = 3;
			this.uvRotateBottom = 3;
		}
		else if (i1 == 4)
		{
			this.uvRotateEast = 1;
			this.uvRotateWest = 2;
			this.uvRotateTop = 2;
			this.uvRotateBottom = 1;
		}
		else if (i1 == 5)
		{
			this.uvRotateEast = 2;
			this.uvRotateWest = 1;
			this.uvRotateTop = 1;
			this.uvRotateBottom = 2;
		}

		boolean flag = this.renderStandardBlock(p_147742_1_, p_147742_2_, p_147742_3_, p_147742_4_);
		this.uvRotateSouth = 0;
		this.uvRotateEast = 0;
		this.uvRotateWest = 0;
		this.uvRotateNorth = 0;
		this.uvRotateTop = 0;
		this.uvRotateBottom = 0;
		return flag;
	}

	@Override
	public void renderInInventory(Block par1Block, int par2Metadata)
	{
		TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
		texturemanager.bindTexture(texturemanager.getResourceLocation(0));
		this.renderBlockAsItem(par1Block, 1, 1F);
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public void renderBlockAsItem(Block par1Block, int par2Metadata, float par3)
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

		if (this.useInventoryTint)
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
		this.setRenderBoundsFromBlock(par1Block);
		int k;
		
		par1Block.setBlockBoundsForItemRender();
		this.setRenderBoundsFromBlock(par1Block);
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		this.renderFaceYNeg(par1Block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(par1Block, 0, par2Metadata));
		tessellator.draw();

		if (flag && this.useInventoryTint)
		{
			k = par1Block.getRenderColor(par2Metadata);
			f2 = (float)(k >> 16 & 255) / 255.0F;
			f3 = (float)(k >> 8 & 255) / 255.0F;
			float f4 = (float)(k & 255) / 255.0F;
			GL11.glColor4f(f2 * 1F, f3 * 1F, f4 * 1F, 1.0F);
		}

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		this.renderFaceYPos(par1Block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(par1Block, 1, par2Metadata));
		tessellator.draw();

		if (flag && this.useInventoryTint)
		{
			GL11.glColor4f(1F, 1F, 1F, 1.0F);
		}

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		this.renderFaceZNeg(par1Block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(par1Block, 2, par2Metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		this.renderFaceZPos(par1Block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(par1Block, 3, par2Metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		this.renderFaceXNeg(par1Block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(par1Block, 4, par2Metadata));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		this.renderFaceXPos(par1Block, 0.0D, 0.0D, 0.0D, this.getBlockIconFromSideAndMetadata(par1Block, 5, par2Metadata));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}
