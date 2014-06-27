package com.minecraftplus.modTurtle;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTurtle extends GuiContainer
{
	private static final ResourceLocation res_entityExtra = new ResourceLocation("minecraftplus:textures/gui/generic_entity.png");
	private EntityTurtle turtle;
	private float field_110416_x;
	private float field_110415_y;

	public static final ResourceLocation res_generic = new ResourceLocation("textures/gui/container/generic_54.png");
	public static final ResourceLocation res_genericBlank = new ResourceLocation("minecraftplus:textures/gui/generic_blank.png");
	protected InventoryTurtle upperInventory;
	protected IInventory lowerInventory;

	public static final int LOW_HEIGHT = 82;
	public static final int LOW_TITLE_HEIGHT = 15;
	public static final int LOW_TITLE_CENTER = 3;
	public static final int HIGH_TITLE_HEIGHT = 17;
	public static final int HIGH_TITLE_CENTER = 6;
	public static final int BORDER_WIDTH = 14;

	public GuiTurtle(IInventory par1IInventory, InventoryTurtle par2InventoryTurtle, Container par3Container)
	{
		super(par3Container);
		this.upperInventory = par2InventoryTurtle;
		this.lowerInventory = par1IInventory;
	}

	public GuiTurtle(EntityPlayer par1EntityPlayer, InventoryTurtle par2InventoryTurtle, EntityTurtle par3EntityTurtle)
	{
		this(par1EntityPlayer.inventory, par2InventoryTurtle, new ContainerTurtle(par1EntityPlayer, par2InventoryTurtle, par3EntityTurtle));
		this.turtle = par3EntityTurtle;
		this.allowUserInput = false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString(this.upperInventory.hasCustomInventoryName() ? this.upperInventory.getInventoryName() : I18n.format(this.upperInventory.getInventoryName()), 8, this.HIGH_TITLE_CENTER + this.upperInventory.getBaseHeight(), 4210752);
		this.fontRendererObj.drawString(this.lowerInventory.hasCustomInventoryName() ? this.lowerInventory.getInventoryName() : I18n.format(this.lowerInventory.getInventoryName()), 8, this.LOW_TITLE_CENTER + this.HIGH_TITLE_HEIGHT + this.upperInventory.getHeight() + this.upperInventory.getBaseHeight(), 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(res_genericBlank);

		int var1 = (this.width - this.xSize) / 2;
		int var2 = (this.height - this.ySize) / 2 + this.upperInventory.getBaseHeight();
		drawTexturedModalRect(var1, var2 + 17, 0, 17, this.xSize, 74 + this.upperInventory.getHeight() - 17);

		this.mc.renderEngine.bindTexture(res_generic);
		drawTexturedModalRect(var1, var2, 0, 0, this.xSize, 17);
		drawTexturedModalRect(var1, var2 + 18 + this.upperInventory.getHeight(), 0, 126, this.xSize, 96);

		int i = this.upperInventory.getRows();
		int k = this.upperInventory.getColumns();

		for (int j = 1; j < i; j++)
		{
			drawTexturedModalRect(var1 + 7 + this.upperInventory.getCustomAlignment(), var2 + j * 18 - 1, 7, 17, (this.upperInventory.getLengthInventory() * 18), 18);
		}

		drawTexturedModalRect(var1 + 7 + this.upperInventory.getCustomAlignment(), var2 + i * 18 - 1, 7, 17, k * 18, 18);

		//TURTLE GUI
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(res_entityExtra);

		int i1 = (this.width - this.xSize) / 2;
		int j1 = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(i1 + 35, j1 + 16, 0, 0, 44, 54);

		if (this.turtle.isDead == false && this.turtle.getHealth() > 0)
		{
			//Background Hearts
			this.drawTexturedModalRect(i1 + 7, j1 + 17, 157, 2, 10, 50);
			this.drawTexturedModalRect(i1 + 20, j1 + 17, 157, 2, 10, 50);

			if (this.turtle.getHealth() / this.turtle.getMaxHealth() > 0.5D)
			{
				int k1 = (int) (this.turtle.getMaxHealth() - this.turtle.getHealth()) * 5;
				//Not yet half-dead; Draw all
				this.drawTexturedModalRect(i1 + 7, j1 + 17 + k1, 146, 2 + k1, 10, -k1 + 50);
				this.drawTexturedModalRect(i1 + 20, j1 + 17, 146, 2, 10, 50);
			}
			else
			{
				int k1 = (int) ((this.turtle.getMaxHealth()/2) - this.turtle.getHealth()) * 5;
				//Half-dead; Don't draw other half
				this.drawTexturedModalRect(i1 + 20, j1 + 17 + k1, 146, 2 + k1, 10, -k1 + 50);
			}
		}

		//TODO: fontRenderer
		//this.fontRendererObj.drawString(this.turtle.getMaxHealth() + "/" + this.turtle.getHealth(), 8, this.HIGH_TITLE_CENTER + this.upperInventory.getBaseHeight(), 4210752);

		GuiInventory.func_147046_a(i1 + 57, j1 + 58, 30, (float)(i1 + 51) - this.field_110416_x, (float)(j1 + 75 - 50) - this.field_110415_y, this.turtle);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.field_110416_x = (float)par1;
		this.field_110415_y = (float)par2;
		super.drawScreen(par1, par2, par3);
	}
}