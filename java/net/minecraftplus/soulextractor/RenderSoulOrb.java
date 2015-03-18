package net.minecraftplus.soulextractor;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSoulOrb extends RenderItemStatic
{
	public RenderSoulOrb(Item par1Item, int par2, int par3)
	{
		super(par1Item, par2, par3);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {}

	public void render(float par1, float par2, float par3, float par4, float par5)
	{
		GL11.glTranslatef(par1, par2, par3);
		GL11.glRotatef(par4, 0F, 1F, 0F);
		GL11.glRotatef(par5, 1F, 0F, 0F);

		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		Tessellator tessellator = Tessellator.instance;

		GL11.glScalef(0.6F, 0.6F, 0.6F);
		this.drawItem(tessellator, 1F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}
}
