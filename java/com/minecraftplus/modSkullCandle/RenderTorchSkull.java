package com.minecraftplus.modSkullCandle;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTorchSkull extends TileEntitySpecialRenderer
{
	private static final ResourceLocation field_147537_c = new ResourceLocation("textures/entity/skeleton/skeleton.png");
	private static final ResourceLocation field_147534_d = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
	private static final ResourceLocation field_147535_e = new ResourceLocation("textures/entity/zombie/zombie.png");
	private static final ResourceLocation field_147532_f = new ResourceLocation("textures/entity/creeper/creeper.png");
	public static RenderTorchSkull field_147536_b;
	private ModelSkeletonHead field_147533_g = new ModelSkeletonHead(0, 0, 64, 32);
	private ModelSkeletonHead field_147538_h = new ModelSkeletonHead(0, 0, 64, 64);

	public void renderTileEntityAt(TileEntitySkullCandle par1TileEntity, double par2, double par3, double par4, float par5)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);

		RenderHelper.disableStandardItemLighting();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);

		if (Minecraft.isAmbientOcclusionEnabled())
		{
			GL11.glShadeModel(GL11.GL_SMOOTH);
		}
		else
		{
			GL11.glShadeModel(GL11.GL_FLAT);
		}

		GL11.glPushMatrix();
		GL11.glTranslated(par2, par3, par4);

		GL11.glTranslated(0.5D, 0, 0.5D);
		GL11.glRotatef((float)(-par1TileEntity.func_145906_b() * 360) / 16.0F, 0, 1F, 0);
		GL11.glTranslated(-0.5D, 0, -0.5D);

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		RenderBlocks renderBlocks = new RenderBlocks(par1TileEntity.getWorldObj());

		tessellator.setTranslation(-par1TileEntity.xCoord, -par1TileEntity.yCoord + 0.2F, -par1TileEntity.zCoord);

		Blocks.torch.setBlockBoundsBasedOnState(renderBlocks.blockAccess, par1TileEntity.xCoord, par1TileEntity.yCoord, par1TileEntity.zCoord);
		renderBlocks.setRenderBoundsFromBlock(Blocks.torch);
		this.renderBlockTorch(renderBlocks, Blocks.torch, par1TileEntity.xCoord, par1TileEntity.yCoord, par1TileEntity.zCoord);

		tessellator.draw();
		tessellator.setTranslation(0, 0, 0);
		GL11.glPopMatrix();

		RenderHelper.enableStandardItemLighting();

		this.func_152674_a((float)par2, (float)par3, (float)par4, par1TileEntity.getBlockMetadata() & 7, (float)(par1TileEntity.func_145906_b() * 360) / 16.0F, par1TileEntity.func_145904_a(), par1TileEntity.func_152108_a());
	}

	@Override
	public void func_147497_a(TileEntityRendererDispatcher p_147497_1_)
	{
		super.func_147497_a(p_147497_1_);
		field_147536_b = this;
	}

	public void func_152674_a(float p_152674_1_, float p_152674_2_, float p_152674_3_, int p_152674_4_, float p_152674_5_, int p_152674_6_, GameProfile p_152674_7_)
	{
		ModelSkeletonHead modelskeletonhead = this.field_147533_g;

		switch (p_152674_6_)
		{
			case 0:
			default:
				this.bindTexture(field_147537_c);
				break;
			case 1:
				this.bindTexture(field_147534_d);
				break;
			case 2:
				this.bindTexture(field_147535_e);
				modelskeletonhead = this.field_147538_h;
				break;
			case 3:
				ResourceLocation resourcelocation = AbstractClientPlayer.locationStevePng;

				if (p_152674_7_ != null)
				{
					Minecraft minecraft = Minecraft.getMinecraft();
					Map map = minecraft.func_152342_ad().func_152788_a(p_152674_7_);

					if (map.containsKey(Type.SKIN))
					{
						resourcelocation = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
					}
				}

				this.bindTexture(resourcelocation);
				break;
			case 4:
				this.bindTexture(field_147532_f);
		}

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);

		if (p_152674_4_ != 1)
		{
			switch (p_152674_4_)
			{
				case 2:
					GL11.glTranslatef(p_152674_1_ + 0.5F, p_152674_2_ + 0.25F, p_152674_3_ + 0.74F);
					break;
				case 3:
					GL11.glTranslatef(p_152674_1_ + 0.5F, p_152674_2_ + 0.25F, p_152674_3_ + 0.26F);
					p_152674_5_ = 180.0F;
					break;
				case 4:
					GL11.glTranslatef(p_152674_1_ + 0.74F, p_152674_2_ + 0.25F, p_152674_3_ + 0.5F);
					p_152674_5_ = 270.0F;
					break;
				case 5:
				default:
					GL11.glTranslatef(p_152674_1_ + 0.26F, p_152674_2_ + 0.25F, p_152674_3_ + 0.5F);
					p_152674_5_ = 90.0F;
			}
		}
		else
		{
			GL11.glTranslatef(p_152674_1_ + 0.5F, p_152674_2_, p_152674_3_ + 0.5F);
		}

		float f4 = 0.0625F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		modelskeletonhead.render((Entity)null, 0.0F, 0.0F, 0.0F, p_152674_5_, 0.0F, f4);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par3, double par4, float par5)
	{
		this.renderTileEntityAt((TileEntitySkullCandle)par1TileEntity, par2, par3, par4, par5);
	}

	public boolean renderBlockTorch(RenderBlocks par1RenderBlocks, Block p_147791_1_, int p_147791_2_, int p_147791_3_, int p_147791_4_)
	{
		int l = par1RenderBlocks.blockAccess.getBlockMetadata(p_147791_2_, p_147791_3_, p_147791_4_);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(p_147791_1_.getMixedBrightnessForBlock(par1RenderBlocks.blockAccess, p_147791_2_, p_147791_3_, p_147791_4_));
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		double d0 = 0.4000000059604645D;
		double d1 = 0.5D - d0;
		double d2 = 0.20000000298023224D;

		if (l == 2)
		{
			par1RenderBlocks.renderTorchAtAngle(p_147791_1_, (double)p_147791_2_, (double)p_147791_3_ + d2, (double)p_147791_4_ + d1, 0.0D, d0, 0);
			return true;
		}

		if (l == 3)
		{
			par1RenderBlocks.renderTorchAtAngle(p_147791_1_, (double)p_147791_2_, (double)p_147791_3_ + d2, (double)p_147791_4_ - d1, 0.0D, -d0, 0);
			return true;
		}

		if (l == 4)
		{
			par1RenderBlocks.renderTorchAtAngle(p_147791_1_, (double)p_147791_2_ + d1, (double)p_147791_3_ + d2, (double)p_147791_4_, d0, 0.0D, 0);
			return true;
		}

		if (l == 5)
		{
			par1RenderBlocks.renderTorchAtAngle(p_147791_1_, (double)p_147791_2_ - d1, (double)p_147791_3_ + d2, (double)p_147791_4_, -d0, 0.0D, 0);
			return true;
		}

		par1RenderBlocks.renderTorchAtAngle(p_147791_1_, (double)p_147791_2_, (double)p_147791_3_, (double)p_147791_4_, 0.0D, 0.0D, 0);

		return true;
	}
}