package net.minecraftplus.loom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerLoom extends Container
{
	private final EntityPlayer player;
	private final InventoryPlayer playerInventory;

	private TileEntityLoom loom;
	private int lastCookTime;
	private int lastBurnTime;
	private int lastItemBurnTime;

	public ContainerLoom(EntityPlayer parEntityPlayer, TileEntityLoom par2TileEntityLoom)
	{
		this.player = parEntityPlayer;
		this.playerInventory = this.player.inventory;

		this.loom = par2TileEntityLoom;
		this.addSlotToContainer(new Slot(par2TileEntityLoom, 0, 56, 17));
		this.addSlotToContainer(new Slot(par2TileEntityLoom, 1, 47, 53));
		this.addSlotToContainer(new Slot(par2TileEntityLoom, 2, 65, 53));
		this.addSlotToContainer(new SlotLoom(parEntityPlayer, par2TileEntityLoom, 3, 116, 35));
		this.addSlotPlayerMainInventory(0, 32 + 52);
		this.addSlotPlayerHotBar(0, 32 + 52);
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

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.loom.loomCookTime);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.loom.loomBurnTime);
		par1ICrafting.sendProgressBarUpdate(this, 2, this.loom.currentItemBurnTime);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);

			if (this.lastCookTime != this.loom.loomCookTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.loom.loomCookTime);
			}

			if (this.lastBurnTime != this.loom.loomBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 1, this.loom.loomBurnTime);
			}

			if (this.lastItemBurnTime != this.loom.currentItemBurnTime)
			{
				icrafting.sendProgressBarUpdate(this, 2, this.loom.currentItemBurnTime);
			}
		}

		this.lastCookTime = this.loom.loomCookTime;
		this.lastBurnTime = this.loom.loomBurnTime;
		this.lastItemBurnTime = this.loom.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
		{
			this.loom.loomCookTime = par2;
		}

		if (par1 == 1)
		{
			this.loom.loomBurnTime = par2;
		}

		if (par1 == 2)
		{
			this.loom.currentItemBurnTime = par2;
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.loom.isUseableByPlayer(par1EntityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 == 3)
			{
				if (!this.mergeItemStack(itemstack1, 4, 40, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (par2 != 2 && par2 != 1 && par2 != 0)
			{
				if (LoomRecipes.smelting().getSmeltingResult(itemstack1) != null)
				{
					if (!this.mergeItemStack(itemstack1, 0, 1, false))
					{
						return null;
					}
				}
				else if (TileEntityLoom.isItemFuel(itemstack1))
				{
					if (!this.mergeItemStack(itemstack1, 1, 3, false))
					{
						return null;
					}
				}
				else if (par2 >= 4 && par2 < 31)
				{
					if (!this.mergeItemStack(itemstack1, 31, 40, false))
					{
						return null;
					}
				}
				else if (par2 >= 31 && par2 < 40 && !this.mergeItemStack(itemstack1, 4, 31, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 4, 40, false))
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

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
}
