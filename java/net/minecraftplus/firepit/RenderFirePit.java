package net.minecraftplus.firepit;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftplus._api.tool.ResTool;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFirePit extends TileEntitySpecialRenderer implements IItemRenderer
{
	private ModelFirePit modelBase;
	private RenderItemStatic itemRender;
	private static final ResourceLocation firePitTexture = ResTool.getResource(ModFirePit.firePit.getUnlocalizedName(), ResTool.BLOCKS, ModFirePit.INSTANCE);
	private static final ResourceLocation firePitLitTexture = ResTool.getResource(ModFirePit.firePitLit.getUnlocalizedName(), ResTool.BLOCKS, ModFirePit.INSTANCE);

	private Random rand = new Random();

	public RenderFirePit()
	{
		this.modelBase = new ModelFirePit();
		this.itemRender = new RenderItemStatic(Items.stick, 0, 16);
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		int rotation = 0;

		if(par1TileEntity.getWorldObj() != null)
		{
			rotation = par1TileEntity.getBlockMetadata();
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

		if(par1TileEntity instanceof TileEntityFirePit)
		{
			TileEntityFirePit tileentityfirepit = (TileEntityFirePit) par1TileEntity;
			BlockFirePit blockfirepit = null;
			ResourceLocation resLocation = firePitTexture;
			if (!tileentityfirepit.isItem())
			{
				Block block = tileentityfirepit.getWorldObj().getBlock(tileentityfirepit.xCoord, tileentityfirepit.yCoord, tileentityfirepit.zCoord);
				if (block instanceof BlockFirePit)
				{
					blockfirepit = (BlockFirePit) block;
				}
				else
				{
					return;
				}

				if (blockfirepit == ModFirePit.firePitLit)
				{
					resLocation = firePitLitTexture;
				}
			}

			Minecraft.getMinecraft().renderEngine.bindTexture(resLocation);
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1F, 0.8F, 0F, 0.8F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glColor3f(1F, 1F, 1F);
			GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 2.24F, (float)par6 + 0.5F);
			GL11.glScalef(1.45F, -1.5F, -1.45F);
			GL11.glRotatef(rotation * 90, 0.0F, 1.0F, 0.0F);
			this.modelBase.doRender();
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_CULL_FACE);

			if (!tileentityfirepit.isItem())
			{
				if (blockfirepit == ModFirePit.firePitLit)
				{
					GL11.glPushMatrix();
					GL11.glTranslatef(0.1F, 1.35F, 0F);
					this.itemRender.doRender(0F, 0F, 0F, 0F, 0F, 0F, 0.5F, false);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					GL11.glTranslatef(0F, 1.35F, 0F);
					GL11.glRotatef(90F, 0F, 0F, 1F);
					GL11.glRotatef(-60F, 1F, 0F, 0F);
					GL11.glTranslatef(0F, 0.2F, 0F);
					this.itemRender.doRender(0F, 0F, 0F, 0F, 0F, 0F, 0.5F, false);
					GL11.glPopMatrix();
					GL11.glPushMatrix();
					GL11.glTranslatef(0.02F, 1.35F, -0.06F);
					GL11.glRotatef(90F, 0F, 0F, 1F);
					GL11.glRotatef(62F, 1F, 0F, 0F);
					GL11.glTranslatef(0F, 0.22F, 0F);
					this.itemRender.doRender(0F, 0F, 0F, 0F, 0F, 0F, 0.5F, false);
					GL11.glPopMatrix();
				}
			}
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	/*****************************ITEM RENDERING**********************************/

	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		GL11.glScalef(1.4F, 1.4F, 1.4F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityFirePit(true), 0D, 0.2D, 0D, 0F);
		GL11.glPopMatrix();
	}
}
