package net.minecraftplus.firepit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderItemStatic extends Render
{
	protected Item item;
	protected int itemMetadata;

	private int renderDistance;

	public RenderItemStatic(Item par1Item, int par2, int par3)
	{
		this.item = par1Item;
		this.itemMetadata = par2;
		this.renderDistance = par3;
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.doRender(par2, par4, par6, par1Entity.rotationYaw, par1Entity.rotationPitch, 0F);
	}

	public void doRender(double par1, double par2, double par3, float par4, float par5, float par6)
	{
		this.doRender(par1, par2, par3, par4, par5, par6, 1F, false);
	}

	public void doRender(double par1, double par2, double par3, float par4, float par5, float par6, float par7, boolean par8)
	{
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		GL11.glRotatef(par4, 0F, 1F, 0F);
		GL11.glRotatef(par5, 1F, 0F, 0F);

		Tessellator tessellator = Tessellator.instance;

		this.drawItem(tessellator, par7, par8);
	}

	protected void drawItem(Tessellator par1Tessellator, float par2)
	{
		this.drawItem(par1Tessellator, par2, false);
	}

	protected void drawItem(Tessellator par1Tessellator, float par2, boolean par3)
	{
		if (Minecraft.getMinecraft().isFancyGraphicsEnabled())
		{
			this.drawItem3D(par1Tessellator, par2);
		}
		else
		{
			this.drawItem2D(par1Tessellator, par2, par3);
		}
	}

	protected void drawItem2D(Tessellator par1Tessellator, float par2, boolean par3)
	{
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
		IIcon icon = this.getIcon(this.item, this.itemMetadata);

		float f = icon.getMinU();
		float f1 = icon.getMaxU();
		float f2 = icon.getMinV();
		float f3 = icon.getMaxV();
		float f4 = par2;

		this.tessellateIcon2D(par1Tessellator, f, f1, f2, f3, f4, 2F, 2F);
		GL11.glRotatef(90, 0F, 0F, !par3 ? 1F : 0F);
		GL11.glScalef(-1F, 1F, 1F);
		this.tessellateIcon2D(par1Tessellator, f, f1, f2, f3, f4, 2F, 2F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	protected void drawItem3D(Tessellator par1Tessellator, float par2)
	{
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
		IIcon icon = this.getIcon(this.item, this.itemMetadata);

		float f = icon.getMinU();
		float f1 = icon.getMaxU();
		float f2 = icon.getMinV();
		float f3 = icon.getMaxV();
		float f4 = par2;

		GL11.glRotatef(90, 0F, 0F, 1F);
		GL11.glScalef(par2, par2, par2);
		tessellateIcon3D(par1Tessellator, f, f1, f2, f3, icon.getIconWidth(), icon.getIconHeight());
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

	private IIcon getIcon(Item par1Item, int par2)
	{
		IIcon icon = par1Item.getIconFromDamage(par2);

		if (icon == null)
		{
			TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
			ResourceLocation resourcelocation = texturemanager.getResourceLocation(0);
			icon = ((TextureMap)texturemanager.getTexture(resourcelocation)).getAtlasSprite("missingno");
		}

		return icon;
	}

	private void tessellateIcon2D(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7, float par8)
	{
		float f = par6 / par7;
		float f1 = par6 / par8;
		par1Tessellator.startDrawingQuads();
		par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
		par1Tessellator.addVertexWithUV((double)(0.0F - f), (double)(0.0F - f1), 0.0D, (double)par2, (double)par5);
		par1Tessellator.addVertexWithUV((double)(par6 - f), (double)(0.0F - f1), 0.0D, (double)par3, (double)par5);
		par1Tessellator.addVertexWithUV((double)(par6 - f), (double)(par6 - f1), 0.0D, (double)par3, (double)par4);
		par1Tessellator.addVertexWithUV((double)(0.0F - f), (double)(par6 - f1), 0.0D, (double)par2, (double)par4);
		par1Tessellator.draw();
	}

	private void tessellateIcon3D(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, int par6, int par7)
	{
		GL11.glTranslatef(-0.5F, -0.5F, 0.03125F);
		ItemRenderer.renderItemIn2D(par1Tessellator, par3, par4, par2, par5, par6, par7, 0.0625F);
	}

	protected boolean isInRenderDistance(Entity par1Entity)
	{
		return (par1Entity.getDistanceSqToEntity(this.renderManager.livingPlayer) <= (double)(this.renderDistance * this.renderDistance));
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity)
	{
		return TextureMap.locationItemsTexture;
	}
}
