package net.minecraftplus.loom;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftplus._api.tool.ResTool;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLoom extends TileEntitySpecialRenderer
{
	private static final ResourceLocation res_Loom = ResTool.getResource(ModLoom.loom.getUnlocalizedName(), ResTool.BLOCKS, ModLoom.INSTANCE);

	private ModelLoom modelBase;
	private ModelLoomWheel modelWheel;

	private float wheelAngle = 0F;

	private Random rand = new Random();

	public RenderLoom()
	{
		this.modelBase = new ModelLoom();
		this.modelWheel = new ModelLoomWheel();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double par2, double par4, double par6, float par8)
	{
		int rotation = 0;

		if(tileentity.getWorldObj() != null)
		{
			rotation = tileentity.getBlockMetadata();
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

		Minecraft.getMinecraft().renderEngine.bindTexture(res_Loom);
		GL11.glPushMatrix();
		GL11.glColor3f(1F, 1F, 1F);
		GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 1.5F, (float)par6 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);
		GL11.glRotatef(rotation * 90, 0.0F, 1.0F, 0.0F);
		this.modelBase.doRender();
		GL11.glEnable(GL11.GL_CULL_FACE);

		float f5 = 0F;
		if(tileentity instanceof TileEntityLoom)
		{
			TileEntityLoom tileentityloom = (TileEntityLoom) tileentity;
			if (tileentityloom.getStackInSlot(0) != null && tileentityloom.getStackInSlot(1) != null && tileentityloom.getStackInSlot(2) != null)
			{
				ItemStack outputStack = tileentityloom.getStackInSlot(3);
				if (outputStack == null || (outputStack != null && outputStack.stackSize < 64))
				{
					this.updateWheelAngle();

					if (rand.nextInt(10) == 0)
					{
						World world = tileentityloom.getWorldObj();

						float f = (float)tileentityloom.xCoord + 0.5F;
						float f1 = (float)tileentityloom.yCoord + 0.0F + this.rand.nextFloat() * 6.0F / 16.0F;
						float f2 = (float)tileentityloom.zCoord + 0.5F;
						float f3 = 0.52F;
						float f4 = this.rand.nextFloat() * 0.6F - 0.3F;
						world.spawnParticle("smoke", (double)f - f4, (double)f1 + 0.5F, (double)f2 - f4 / 2, 0.0D, 0.0D, 0.0D);
					}
				}
			}
			f5 = this.wheelAngle;
		}

		this.modelWheel.render((Entity)null, 0F, 0F, 0F, f5, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	private float updateWheelAngle()
	{
		this.wheelAngle += 0.008F;
		if (this.wheelAngle >= 360F)
		{
			this.wheelAngle = 0F;
		}
		return this.wheelAngle;
	}
}
