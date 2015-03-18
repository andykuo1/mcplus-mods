package net.minecraftplus.turtle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemStack;
import net.minecraftplus.pigeon.EntityPigeon;

public class ContainerTurtle extends Container
{
	private final EntityPlayer player;
	private final EntityTurtle turtle;

	private final IInventory invUp;
	private final IInventory invDown;

	protected final int rows;
	protected final int columns;

	public ContainerTurtle(EntityPlayer parEntityPlayer, EntityTurtle parEntityTurtle)
	{
		this.player = parEntityPlayer;
		this.turtle = parEntityTurtle;

		this.invUp = this.turtle.getAnimalChest();
		this.invDown = this.player.inventory;

		this.invUp.openInventory();

		byte numRows = 3;
		int i = (numRows - 4) * 18;
		int j;
		int k;

		if (this.turtle.isChested())
		{
			this.rows = this.findRows(this.invUp.getSizeInventory(), 5);
			this.columns = 5;
			this.addChestSlotsToContainer(this.invUp, 80, 18, 0, this.rows, this.columns);
		}
		else
		{
			this.rows = 0;
			this.columns = 0;
		}

		this.addPlayerSlotsToContainer(this.invDown, 8, 102 + i);
	}

	protected int findRows(int parSize, int parColumns)
	{
		return (parSize / parColumns) + (parSize % parColumns == 0 ? 0 : 1);
	}

	protected int findColumns(int parSize, int parRows)
	{
		return (parSize / parRows);
	}

	private void addChestSlotsToContainer(IInventory parIInventory, int parOffsetX, int parOffsetY, int parSlotInit, int parRows, int parColumns)
	{
		int i = parIInventory.getSizeInventory();
		int j;
		int k;

		if (parRows <= 0 && parColumns <= 0) {parColumns = 5;}
		if (parRows <= 0) {parRows = this.findRows(i, parColumns);}
		else if (parColumns <= 0) {parColumns = this.findColumns(i, parRows);}

		for (i = parSlotInit, j = 0; j < parRows; ++j)
		{
			for (k = 0; k < parColumns && i < parIInventory.getSizeInventory(); ++k, ++i)
			{
				this.addSlotToContainer(new Slot(parIInventory, i, parOffsetX + k * 18, parOffsetY + j * 18));
			}
		}
	}

	private void addPlayerSlotsToContainer(IInventory parIInventory, int parOffsetX, int parOffsetY)
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

	@Override
	public boolean canInteractWith(EntityPlayer parEntityPlayer)
	{
		return this.invUp.isUseableByPlayer(parEntityPlayer) && this.turtle.isEntityAlive() && this.turtle.getDistanceToEntity(parEntityPlayer) < 8.0F;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer parEntityPlayer, int parSlot)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(parSlot);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

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
	public void onContainerClosed(EntityPlayer p_75134_1_)
	{
		super.onContainerClosed(p_75134_1_);
		this.invUp.closeInventory();
	}
}