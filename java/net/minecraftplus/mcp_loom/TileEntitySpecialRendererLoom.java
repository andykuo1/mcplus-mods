package net.minecraftplus.mcp_loom;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Assets;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntitySpecialRendererLoom extends TileEntitySpecialRenderer
{
	private ModelLoom modelBase;
	private ModelLoomWheel modelWheel;
	private static final ResourceLocation loomTexture = new ResourceLocation(Assets.resource(_Loom.MODID, "textures/blocks/loom.png"));

	private Random rand = new Random();

	public TileEntitySpecialRendererLoom()
	{
		this.modelBase = new ModelLoom();
		this.modelWheel = new ModelLoomWheel();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double relativeX, double relativeY, double relativeZ, float partialTicks, int blockDamageProgress)
	{
		int rotation = 0;

		if(tileEntity.getWorld() != null)
		{
			rotation = tileEntity.getBlockMetadata();
		}

		switch(rotation)
		{
		case 5:
			rotation = 3;
			break;
		case 3:
			rotation = 4;
			break;
		case 4:
			rotation = 1;
			break;
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(loomTexture);
		GL11.glPushMatrix();
		GL11.glColor3f(1F, 1F, 1F);
		GL11.glTranslatef((float)relativeX + 0.5F, (float)relativeY + 1.5F, (float)relativeZ + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glRotatef(rotation * 90, 0.0F, 1.0F, 0.0F);
		this.modelBase.doRender();
		GL11.glEnable(GL11.GL_CULL_FACE);

		float f5 = 0F;
		if(tileEntity instanceof TileEntityLoom)
		{
			TileEntityLoom tileentityloom = (TileEntityLoom) tileEntity;
			if (tileentityloom.getBlockType() == _Loom.workingLoom)
			{
				f5 = ((float) Minecraft.getSystemTime() / 200000F) * 360F;
			}
		}

		this.modelWheel.render((Entity)null, 0F, 0F, 0F, f5, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}
