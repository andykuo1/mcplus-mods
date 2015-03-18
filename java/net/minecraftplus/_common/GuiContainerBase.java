package net.minecraftplus._common;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public abstract class GuiContainerBase extends GuiContainer
{
	protected final IInventory invUp;
	protected final IInventory invDown;

	public GuiContainerBase(Container parContainer, IInventory parMainInventory, IInventory parInventory)
	{
		super(parContainer);

		this.invUp = parInventory;
		this.invDown = parMainInventory;

		this.allowUserInput = false;
	}

	@Override
	protected abstract void drawGuiContainerForegroundLayer(int par1, int par2);

	@Override
	protected abstract void drawGuiContainerBackgroundLayer(float par1, int par2, int par3);

	protected void drawInventoryName(IInventory parIInventory, int parOffsetX, int parOffsetY)
	{
		this.fontRendererObj.drawString(parIInventory.hasCustomInventoryName() ? parIInventory.getInventoryName() : I18n.format(parIInventory.getInventoryName(), new Object[0]), parOffsetX, parOffsetY, 4210752);
	}

	protected Container getContainer()
	{
		return this.inventorySlots;
	}
}
