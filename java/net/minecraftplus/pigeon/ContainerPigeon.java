package net.minecraftplus.pigeon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemStack;

public class ContainerPigeon extends Container
{
	private final EntityPlayer player;
	private final EntityPigeon pigeon;

	private final IInventory invUp;
	private final IInventory invDown;

	protected final int rows;
	protected final int columns;

	public ContainerPigeon(EntityPlayer parEntityPlayer, EntityPigeon parEntityPigeon)
	{
		this.player = parEntityPlayer;
		this.pigeon = parEntityPigeon;

		this.invUp = this.pigeon.getAnimalChest();
		this.invDown = this.player.inventory;

		this.invUp.openInventory();

		this.addSlotToContainer(new Slot(this.invUp, 0, 8, 18)
		{
			@Override
			public boolean isItemValid(ItemStack p_75214_1_)
			{
				return super.isItemValid(p_75214_1_) && p_75214_1_.getItem() == Items.leather_helmet && !this.getHasStack();
			}
		});

		this.addSlotToContainer(new Slot(this.invUp, 1, 8, 36)
		{
			@Override
			public boolean isItemValid(ItemStack parItemStack)
			{
				return super.isItemValid(parItemStack) && parItemStack.getItem() instanceof ItemNameTag && !this.getHasStack();
			}
		});

		byte numRows = 3;
		int i = (numRows - 4) * 18;
		int j;
		int k;

		if (this.pigeon.isChested())
		{
			this.rows = this.findRows(this.invUp.getSizeInventory() - 2, 5);
			this.columns = 5;
			this.addChestSlotsToContainer(this.invUp, 80, 18, 2, this.rows, this.columns);
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
		return this.invUp.isUseableByPlayer(parEntityPlayer) && this.pigeon.isEntityAlive() && this.pigeon.isChested() && this.pigeon.getOwner() != parEntityPlayer ? (parEntityPlayer.getCurrentEquippedItem() != null && (parEntityPlayer.getCurrentEquippedItem().getItem() == Items.melon_seeds || parEntityPlayer.getCurrentEquippedItem().getItem() == Items.melon)) : true;
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
			else if (this.getSlot(1).isItemValid(itemstack1))
			{
				if (!this.mergeItemStack(itemstack1, 1, 2, false))
				{
					return null;
				}
			}
			else if (this.getSlot(0).isItemValid(itemstack1))
			{
				if (!this.mergeItemStack(itemstack1, 0, 1, false))
				{
					return null;
				}
			}
			else if (this.invUp.getSizeInventory() <= 2 || !this.mergeItemStack(itemstack1, 2, this.invUp.getSizeInventory(), false))
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
