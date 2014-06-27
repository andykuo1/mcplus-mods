package com.minecraftplus.modTurtle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.minecraftplus._common.container.ContainerWithPlayerInventory;

public class ContainerTurtle extends ContainerWithPlayerInventory
{
	protected int numRows;
	protected InventoryTurtle chestInventory;

	private Entity chestEntity;

	public ContainerTurtle(EntityPlayer par1EntityPlayer, InventoryTurtle par2InventoryTurtle, EntityTurtle par3EntityTurtle)
	{
		super(par1EntityPlayer, par1EntityPlayer.inventory);

		this.numRows = getRowInventory(par2InventoryTurtle);
		this.chestInventory = par2InventoryTurtle;

		par2InventoryTurtle.openInventory();

		int i = par2InventoryTurtle.getHeight() + par2InventoryTurtle.getBaseHeight();
		this.addChestSlots(i, par2InventoryTurtle);
		this.addSlotPlayerMainInventory(0, 32 + i);
		this.addSlotPlayerHotBar(0, 32 + i);

		this.chestEntity = par3EntityTurtle;
	}

	public void addChestSlots(int par1, InventoryTurtle par2InventoryTurtle)
	{
		int i1 = (par2InventoryTurtle.getSizeInventory() / par1) * par2InventoryTurtle.getLengthInventory();

		for (int i = 0; i < par2InventoryTurtle.getSizeInventory(); i++)
		{
			//Current Row
			int j = i / par2InventoryTurtle.getLengthInventory();
			//Current Column
			int k = i % par2InventoryTurtle.getLengthInventory();

			this.addSlotToContainer(new Slot(par2InventoryTurtle, k + (j * par2InventoryTurtle.getLengthInventory()), 8 + (k * 18) + (i >= i1 ? par2InventoryTurtle.getCustomAlignment() : 0), 18 + par2InventoryTurtle.getBaseHeight() + (j * 18)));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.chestInventory.isUseableByPlayer(par1EntityPlayer) && this.chestEntity.isEntityAlive() && this.chestEntity.getDistanceToEntity(par1EntityPlayer) < 8.0F;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();

			if (!canItemStackBeShifted(itemstack1))
			{
				return null;
			}

			itemstack = itemstack1.copy();
			if (par2 < this.numRows * this.chestInventory.getLengthInventory())
			{
				if (!this.mergeItemStack(itemstack1, this.numRows * this.chestInventory.getLengthInventory(), this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, this.numRows * this.chestInventory.getLengthInventory(), false))
			{
				return null;
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		this.chestInventory.closeInventory();
	}

	public IInventory getChestInventory()
	{
		return this.chestInventory;
	}

	public boolean canItemStackBeShifted(ItemStack par1ItemStack)
	{
		return true;
	}

	protected static int getRowInventory(IInventory par1IInventory)
	{
		return (int) Math.ceil(par1IInventory.getSizeInventory() / (double) ((InventoryTurtle) par1IInventory).getLengthInventory());
	}
}