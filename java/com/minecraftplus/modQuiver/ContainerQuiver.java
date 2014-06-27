package com.minecraftplus.modQuiver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.minecraftplus._common.container.ContainerItem;

public class ContainerQuiver extends ContainerItem
{
	protected int numRows = 0;

	public ContainerQuiver(EntityPlayer par1EntityPlayer, InventoryQuiver par2InventoryQuiver, ItemStack par3ItemStack, int par4)
	{
		super(par1EntityPlayer, par2InventoryQuiver, par3ItemStack, par4);

		this.numRows = getRowInventory(par2InventoryQuiver);

		int i = par2InventoryQuiver.getHeight() + par2InventoryQuiver.getBaseHeight();
		this.addChestSlots(i, par2InventoryQuiver);
		this.addSlotPlayerMainInventory(0, 32 + i);
		this.addSlotPlayerHotBar(0, 32 + i);
	}

	public void addChestSlots(int par1, InventoryQuiver par2InventoryQuiver)
	{
		int i1 = (par2InventoryQuiver.getSizeInventory() / par2InventoryQuiver.getLengthInventory()) * par2InventoryQuiver.getLengthInventory();

		for (int i = 0; i < par2InventoryQuiver.getSizeInventory(); i++)
		{
			//Current Row
			int j = i / par2InventoryQuiver.getLengthInventory();
			//Current Column
			int k = i % par2InventoryQuiver.getLengthInventory();

			this.addSlotToContainer(new SlotQuiver(par2InventoryQuiver, k + (j * par2InventoryQuiver.getLengthInventory()), 8 + (k * 18) + (i >= i1 ? par2InventoryQuiver.getCustomAlignment() : 0), 18 + par2InventoryQuiver.getBaseHeight() + (j * 18)));
		}
	}

	public boolean canItemStackBeShifted(ItemStack par1ItemStack)
	{
		return par1ItemStack.getItem() == Items.arrow;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		this.chestInventory.closeInventory();
	}

	public int getCustmAlignment()
	{
		return ((InventoryQuiver) this.chestInventory).getCustomAlignment();
	}

	protected static int getRowInventory(IInventory par1IInventory)
	{
		return (int) Math.ceil(par1IInventory.getSizeInventory() / (double) ((InventoryQuiver) par1IInventory).getLengthInventory());
	}
}