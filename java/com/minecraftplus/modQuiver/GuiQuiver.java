package com.minecraftplus.modQuiver;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiQuiver extends GuiContainer
{
	public static final ResourceLocation res_generic = new ResourceLocation("textures/gui/container/generic_54.png");
	public static final ResourceLocation res_genericBlank = new ResourceLocation("minecraftplus:textures/gui/generic_blank.png");
	protected InventoryQuiver upperInventory;
	protected IInventory lowerInventory;

	public static final int LOW_HEIGHT = 82;
	public static final int LOW_TITLE_HEIGHT = 15;
	public static final int LOW_TITLE_CENTER = 3;
	public static final int HIGH_TITLE_HEIGHT = 17;
	public static final int HIGH_TITLE_CENTER = 6;
	public static final int BORDER_WIDTH = 14;

	public GuiQuiver(IInventory par1IInventory, InventoryQuiver par2InventoryQuiver, Container par3Container)
	{
		super(par3Container);
		this.upperInventory = par2InventoryQuiver;
		this.lowerInventory = par1IInventory;
	}

	public GuiQuiver(EntityPlayer par1EntityPlayer, InventoryQuiver par2InventoryQuiver)
	{
		this(par1EntityPlayer.inventory, par2InventoryQuiver, new ContainerQuiver(par1EntityPlayer, par2InventoryQuiver, null, par1EntityPlayer.inventory.currentItem));
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
	}
}
