package com.minecraftplus._common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public abstract class ContainerWithPlayerInventory extends ContainerBasic
{
	public InventoryPlayer playerInventory;

	public ContainerWithPlayerInventory(EntityPlayer par1EntityPlayer, InventoryPlayer par2InventoryPlayer)
	{
		super(par1EntityPlayer);
		this.playerInventory = par2InventoryPlayer;
	}

	/** Add player's main inventory slots. */
	protected void addSlotPlayerMainInventory(int par1, int par2)
	{
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(this.playerInventory, j + (i + 1) * 9, par1 + 8 + j * 18, par2 + i * 18));
			}
		}
	}

	/** Add player's hotbar slots. */
	protected void addSlotPlayerHotBar(int par1, int par2)
	{
		for (int i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(this.playerInventory, i, par1 + 8 + i * 18, par2 + 58));
		}
	}
}
