package com.minecraftplus.modSkullCandle;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.mojang.authlib.GameProfile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRedstoneGlowingSkull extends TileEntitySpecialRenderer
{
	private static final ResourceLocation field_147537_c = new ResourceLocation("minecraftplus:textures/blocks/tile.glowing_redstone_skull.skeleton.png");
	private static final ResourceLocation field_147534_d = new ResourceLocation("minecraftplus:textures/blocks/tile.glowing_redstone_skull.wither_skeleton.png");
	private static final ResourceLocation field_147535_e = new ResourceLocation("minecraftplus:textures/blocks/tile.glowing_redstone_skull.zombie.png");
	private static final ResourceLocation field_147532_f = new ResourceLocation("minecraftplus:textures/blocks/tile.glowing_redstone_skull.creeper.png");
	private static final ResourceLocation field_147539_g = new ResourceLocation("minecraftplus:textures/blocks/tile.glowing_redstone_skull.steve.png");
	public static RenderRedstoneGlowingSkull field_147536_b;
	private ModelSkeletonHead field_147533_g = new ModelSkeletonHead(0, 0, 64, 32);
	private ModelSkeletonHead field_147538_h = new ModelSkeletonHead(0, 0, 64, 64);

	public void renderTileEntityAt(TileEntityRedstoneSkullCandle par1TileEntity, double par2, double par3, double par4, float par5)
	{
		this.func_152674_a((float)par2, (float)par3, (float)par4, par1TileEntity.getBlockMetadata() & 7, (float)(par1TileEntity.func_145906_b() * 360) / 16.0F, par1TileEntity.func_145904_a(), par1TileEntity.func_152108_a());
	}

	@Override
	public void func_147497_a(TileEntityRendererDispatcher p_147497_1_)
	{
		super.func_147497_a(p_147497_1_);
		field_147536_b = this;
	}

	public void func_152674_a(float p_147530_1_, float p_147530_2_, float p_147530_3_, int p_147530_4_, float p_147530_5_, int p_147530_6_, GameProfile p_152674_7_)
	{
		ModelSkeletonHead modelskeletonhead = this.field_147533_g;

		switch (p_147530_6_)
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
				this.bindTexture(field_147539_g);
				break;
			case 4:
				this.bindTexture(field_147532_f);
		}

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);

		if (p_147530_4_ != 1)
		{
			switch (p_147530_4_)
			{
				case 2:
					GL11.glTranslatef(p_147530_1_ + 0.5F, p_147530_2_ + 0.25F, p_147530_3_ + 0.74F);
					break;
				case 3:
					GL11.glTranslatef(p_147530_1_ + 0.5F, p_147530_2_ + 0.25F, p_147530_3_ + 0.26F);
					p_147530_5_ = 180.0F;
					break;
				case 4:
					GL11.glTranslatef(p_147530_1_ + 0.74F, p_147530_2_ + 0.25F, p_147530_3_ + 0.5F);
					p_147530_5_ = 270.0F;
					break;
				case 5:
				default:
					GL11.glTranslatef(p_147530_1_ + 0.26F, p_147530_2_ + 0.25F, p_147530_3_ + 0.5F);
					p_147530_5_ = 90.0F;
			}
		}
		else
		{
			GL11.glTranslatef(p_147530_1_ + 0.5F, p_147530_2_, p_147530_3_ + 0.5F);
		}

		float f4 = 0.0625F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		modelskeletonhead.render((Entity)null, 0.0F, 0.0F, 0.0F, p_147530_5_, 0.0F, f4);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par3, double par4, float par5)
	{
		this.renderTileEntityAt((TileEntityRedstoneSkullCandle) par1TileEntity, par2, par3, par4, par5);
	}
}