package com.minecraftplus.modLock;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.minecraftplus._common.render.RenderItemStatic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLock extends RenderItemStatic
{
	public RenderLock(Item par1Item)
	{
		super(par1Item, 1, 16);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		if (par1Entity instanceof EntityLock)
		{
			EntityLock entitylock = (EntityLock) par1Entity;
			if (entitylock.hasCustomName())
			{
				GL11.glPushMatrix();
				this.renderString(entitylock, entitylock.getCustomName(), par2, par4, par6, 0D, 0D, 0D, this.renderManager.playerViewX, -this.renderManager.playerViewY, 1F, true);
				GL11.glPopMatrix();
			}
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		Tessellator tessellator = Tessellator.instance;

		int i = par1Entity.rotationPitch > 0 ? 1 : par1Entity.rotationPitch < 0 ? -1 : 0;
		GL11.glRotatef(par1Entity.rotationYaw, 0F, 1F, 0F);
		GL11.glRotatef(par1Entity.rotationPitch, 1F, 0F, 0F);

		GL11.glTranslatef(0F, 0F, 0.525F);
		if (i == 1 || i == -1)
		{
			GL11.glTranslatef(0F, 0F, 0.1F * -i);
		}
		else
		{
			GL11.glTranslatef(0F, 0.1F, 0F);
		}

		this.drawItem(tessellator, 1F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	/** Draws the text above a living
	 * 
	 * @param par1Entity
	 * @param par2Str
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param offsetX
	 * @param offsetY
	 * @param offsetZ
	 * @param rotationPitch
	 * @param rotationYaw
	 * @param scale
	 * @param shouldRenderPassBlocks
	 */
	protected void renderString(Entity par1Entity, String par2Str, double par3, double par5, double par7, double par8, double par9, double par10, float par11, float par12, float par13, boolean par14)
	{
		if (this.isInRenderDistance(par1Entity))
		{
			FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
			float f = 1.6F * par13;
			float f1 = 0.016666668F * f;

			GL11.glTranslatef((float)par3, (float)par5 + par1Entity.height, (float)par7);

			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(par12, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(par11, 1.0F, 0.0F, 0.0F);

			GL11.glTranslatef((float) par8, (float) par9, (float) par10);

			GL11.glScalef(-f1, -f1, f1);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDepthMask(false);

			if (par14)
			{
				GL11.glDisable(GL11.GL_DEPTH_TEST);
			}

			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Tessellator tessellator = Tessellator.instance;
			byte b0 = 0;

			if (par2Str.equals("deadmau5"))
			{
				b0 = -10;
			}

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			tessellator.startDrawingQuads();
			int j = fontrenderer.getStringWidth(par2Str) / 2;
			tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
			tessellator.addVertex((double)(-j - 1), (double)(-1 + b0), 0.0D);
			tessellator.addVertex((double)(-j - 1), (double)(8 + b0), 0.0D);
			tessellator.addVertex((double)(j + 1), (double)(8 + b0), 0.0D);
			tessellator.addVertex((double)(j + 1), (double)(-1 + b0), 0.0D);
			tessellator.draw();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, b0, 553648127);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, b0, -1);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}