package com.minecraftplus.modSatchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.minecraftplus._common.container.ContainerItem;

public class ContainerSatchel extends ContainerItem
{
	protected int numRows = 0;

	public ContainerSatchel(EntityPlayer par1EntityPlayer, InventorySatchel par2InventorySatchel, ItemStack par3ItemStack)
	{
		super(par1EntityPlayer, par2InventorySatchel, par3ItemStack, par1EntityPlayer.inventory.currentItem);

		this.numRows = par2InventorySatchel.getSizeInventory() / 6;

		this.addChestSlots(0, 54, par2InventorySatchel);
		this.addSlotPlayerMainInventory(0, this.numRows * 18 + 31);
		this.addSlotPlayerHotBar(0, this.numRows * 18 + 31);
	}

	public void addChestSlots(int par1, int par2, InventorySatchel par2InventorySatchel)
	{
		for (int j = 0; j < this.numRows; ++j)
		{
			for (int k = 0; k < 6; ++k)
			{
				this.addSlotToContainer(new SlotSatchel(par2InventorySatchel, k + j * 6, 8 + k * 18 + par2, par1 + 18 + j * 18));
			}
		}
	}

	@Override
	public boolean canItemStackBeShifted(ItemStack par1ItemStack)
	{
		return par1ItemStack.getItem() != MCP_Satchel.satchel;
	}
}