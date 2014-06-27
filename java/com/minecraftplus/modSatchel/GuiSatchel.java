package com.minecraftplus.modSatchel;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSatchel extends GuiContainer
{
	private EntityPlayer player;
	private float field_110416_x;
	private float field_110415_y;

	private static final ResourceLocation resSatchelInventory = new ResourceLocation("minecraftplus:textures/gui/container_satchel.png");
	private static final ResourceLocation resPlayerInventory = new ResourceLocation("textures/gui/container/generic_54.png");
	private IInventory upperChestInventory;
	private IInventory lowerChestInventory;
	/**
	 * window height is calculated with these values; the more rows, the heigher
	 */
	private int inventoryRows;

	public GuiSatchel(EntityPlayer par1EntityPlayer, IInventory par2IInventory, ItemStack par3ItemStack)
	{
		super(new ContainerSatchel(par1EntityPlayer, (InventorySatchel) par2IInventory, par3ItemStack));
		this.player = par1EntityPlayer;
		this.upperChestInventory = par1EntityPlayer.inventory;
		this.lowerChestInventory = par2IInventory;
		this.allowUserInput = false;
		short short1 = 222;
		int i = short1 - 108;
		this.inventoryRows = par2IInventory.getSizeInventory() / 6;
		this.ySize = i + this.inventoryRows * 18;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString(this.lowerChestInventory.hasCustomInventoryName() ? this.lowerChestInventory.getInventoryName() : I18n.format(this.lowerChestInventory.getInventoryName(), new Object[0]), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.upperChestInventory.hasCustomInventoryName() ? this.upperChestInventory.getInventoryName() : I18n.format(this.upperChestInventory.getInventoryName(), new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(resSatchelInventory);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.mc.getTextureManager().bindTexture(resPlayerInventory);
		this.drawTexturedModalRect(k, l + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);

		GuiInventory.func_147046_a(k + 34, l + 66, 24, (float)(k + 34) - this.field_110416_x, (float)(l + 95 - 66) - this.field_110415_y, this.player);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.field_110416_x = (float)par1;
		this.field_110415_y = (float)par2;
		super.drawScreen(par1, par2, par3);
	}
}
