package com.minecraftplus.modMortar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.minecraftplus._common.container.ContainerWithPlayerInventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerMortar extends ContainerWithPlayerInventory
{
	private TileEntityMortar mortar;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;

	public ContainerMortar(EntityPlayer par1EntityPlayer, TileEntityMortar par2TileEntityDisposer)
	{
		super(par1EntityPlayer, par1EntityPlayer.inventory);
		this.mortar = par2TileEntityDisposer;
		this.addSlotToContainer(new Slot(par2TileEntityDisposer, 0, 34, 17));
		this.addSlotToContainer(new Slot(par2TileEntityDisposer, 1, 56, 17));
		this.addSlotToContainer(new Slot(par2TileEntityDisposer, 2, 74, 17));
		this.addSlotToContainer(new Slot(par2TileEntityDisposer, 3, 56, 53));
		this.addSlotToContainer(new SlotMortar(par1EntityPlayer, par2TileEntityDisposer, 4, 116, 35));
		this.addSlotPlayerMainInventory(0, 32 + 52);
		this.addSlotPlayerHotBar(0, 32 + 52);
	}

	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.mortar.mortarCookTime);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.mortar.mortarBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.mortar.currentItemBurnTime);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);

			if (this.lastCookTime != this.mortar.mortarCookTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.mortar.mortarCookTime);
			}

			if (this.lastBurnTime != this.mortar.mortarBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 1, this.mortar.mortarBurnTime);
			}

			if (this.lastItemBurnTime != this.mortar.currentItemBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 2, this.mortar.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.mortar.mortarCookTime;
		this.lastBurnTime = this.mortar.mortarBurnTime;
		this.lastItemBurnTime = this.mortar.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
		{
			this.mortar.mortarCookTime = par2;
		}

		if (par1 == 1)
		{
			this.mortar.mortarBurnTime = par2;
		}

		if (par1 == 2)
		{
			this.mortar.currentItemBurnTime = par2;
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.mortar.isUseableByPlayer(par1EntityPlayer);
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		return null;
	}
}
