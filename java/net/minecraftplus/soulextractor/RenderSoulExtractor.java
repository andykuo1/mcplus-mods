package net.minecraftplus.soulextractor;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSoulExtractor extends TileEntitySpecialRenderer
{
	private RenderSoulOrb orb = new RenderSoulOrb(Items.ender_pearl, 0, 8);

	public void renderTileEntitySoulExtractorAt(TileEntitySoulExtractor par1TileEntitySoulExtractor, double par2, double par4, double par6, float par8)
	{
		GL11.glPushMatrix();
		orb.render((float)par2 + 0.5F, (float)par4 + 1F, (float)par6 + 0.5F, par1TileEntitySoulExtractor.getOrbYaw(), par1TileEntitySoulExtractor.getOrbPitch());
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderTileEntitySoulExtractorAt((TileEntitySoulExtractor) par1TileEntity, par2, par4, par6, par8);
	}
}
