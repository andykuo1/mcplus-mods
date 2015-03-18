package net.minecraftplus._common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerBase extends Container
{
	protected final IInventory invUp;
	protected final IInventory invDown;

	public ContainerBase(IInventory parMainInventory, IInventory parInventory)
	{
		this.invUp = parInventory;
		this.invDown = parMainInventory;
	}

	protected int findRows(int parSize, int parColumns)
	{
		return (parSize / parColumns) + (parSize % parColumns == 0 ? 0 : 1);
	}

	protected int findColumns(int parSize, int parRows)
	{
		return (parSize / parRows);
	}

	protected void addChestSlotsToContainer(IInventory parIInventory, int parOffsetX, int parOffsetY, int parSlotInit, int parRows, int parColumns)
	{
		int i = parIInventory.getSizeInventory();
		int j;
		int k;

		for (i = parSlotInit, j = 0; j < parRows; ++j)
		{
			for (k = 0; k < parColumns && i < parIInventory.getSizeInventory(); ++k, ++i)
			{
				this.addSlotToContainer(this.getNewSlot(parIInventory, i, parOffsetX + k * 18, parOffsetY + j * 18));
			}
		}
	}

	protected void addPlayerSlotsToContainer(IInventory parIInventory, int parOffsetX, int parOffsetY)
	{
		int j;
		int k;

		for (j = 0; j < 3; ++j)
		{
			for (k = 0; k < 9; ++k)
			{
				this.addSlotToContainer(new Slot(parIInventory, k + j * 9 + 9, parOffsetX + k * 18, parOffsetY + j * 18));
			}
		}

		for (j = 0; j < 9; ++j)
		{
			this.addSlotToContainer(new Slot(parIInventory, j, parOffsetX + j * 18, parOffsetY + 58));
		}
	}

	protected Slot getNewSlot(IInventory parInventory, int parSlotIndex, int parOffsetX, int parOffsetY)
	{
		return new Slot(parInventory, parSlotIndex, parOffsetX, parOffsetY);
	}

	@Override
	public abstract boolean canInteractWith(EntityPlayer parEntityPlayer);

	@Override
	public ItemStack transferStackInSlot(EntityPlayer parEntityPlayer, int parSlot)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(parSlot);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (!slot.isItemValid(itemstack1))
			{
				return null;
			}

			if (parSlot < this.invUp.getSizeInventory())
			{
				if (!this.mergeItemStack(itemstack1, this.invUp.getSizeInventory(), this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, this.invUp.getSizeInventory(), false))
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
	public void onContainerClosed(EntityPlayer parEntityPlayer)
	{
		super.onContainerClosed(parEntityPlayer);
		this.invUp.closeInventory();
	}
}
