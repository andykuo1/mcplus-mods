package com.minecraftplus._common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerItem extends ContainerWithPlayerInventory
{
	protected IInventory chestInventory;
	protected ItemStack chestStack;
	protected int chestIndex;

	public ContainerItem(EntityPlayer par1EntityPlayer, IInventory par2IInventory, ItemStack par3ItemStack, int par4)
	{
		super(par1EntityPlayer, par1EntityPlayer.inventory);

		this.chestInventory = par2IInventory;
		this.chestStack = par3ItemStack;
		if (par4 >= 0 && par4 < par1EntityPlayer.inventory.mainInventory.length || par4 < 0 && par4 >= -par1EntityPlayer.inventory.armorInventory.length)
		{
			this.chestIndex = par4;
		}
		else
		{
			this.chestIndex = par1EntityPlayer.inventory.currentItem;
		}

		this.chestInventory.openInventory();
	}

	public abstract boolean canItemStackBeShifted(ItemStack par1ItemStack);

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.chestInventory.isUseableByPlayer(par1EntityPlayer) && this.canAccessChestStack(par1EntityPlayer);
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
			if (par2 < this.chestInventory.getSizeInventory())
			{
				if (!this.mergeItemStack(itemstack1, this.chestInventory.getSizeInventory(), this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, this.chestInventory.getSizeInventory(), false))
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

	private boolean canAccessChestStack(EntityPlayer par1EntityPlayer)
	{
		return this.chestIndex >= 0 ? par1EntityPlayer.inventory.mainInventory[this.chestIndex] != null : par1EntityPlayer.inventory.armorInventory[-this.chestIndex - 1] != null;
	}

	public IInventory getChestInventory()
	{
		return this.chestInventory;
	}

	public ItemStack getChestStack()
	{
		return this.chestStack;
	}
}

