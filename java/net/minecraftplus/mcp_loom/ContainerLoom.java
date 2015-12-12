package net.minecraftplus.mcp_loom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//@ContainerFurnace
public class ContainerLoom extends Container
{
	private final IInventory tileLoom;
	private int field_178152_f;
	private int field_178153_g;
	private int field_178154_h;
	private int field_178155_i;
	
	public ContainerLoom(InventoryPlayer p_i45794_1_, IInventory furnaceInventory)
	{
		this.tileLoom = furnaceInventory;
		this.addSlotToContainer(new Slot(furnaceInventory, 0, 56, 17));
		this.addSlotToContainer(new Slot(furnaceInventory, 1, 47, 53));
		this.addSlotToContainer(new Slot(furnaceInventory, 2, 65, 53));
		this.addSlotToContainer(new SlotLoom(p_i45794_1_.player, furnaceInventory, 3, 116, 35));
		
		int i;

		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(p_i45794_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(p_i45794_1_, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting listener)
	{
		super.addCraftingToCrafters(listener);
		listener.func_175173_a(this, this.tileLoom);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);

			if (this.field_178152_f != this.tileLoom.getField(2))
			{
				icrafting.sendProgressBarUpdate(this, 2, this.tileLoom.getField(2));
			}

			if (this.field_178154_h != this.tileLoom.getField(0))
			{
				icrafting.sendProgressBarUpdate(this, 0, this.tileLoom.getField(0));
			}

			if (this.field_178155_i != this.tileLoom.getField(1))
			{
				icrafting.sendProgressBarUpdate(this, 1, this.tileLoom.getField(1));
			}

			if (this.field_178153_g != this.tileLoom.getField(3))
			{
				icrafting.sendProgressBarUpdate(this, 3, this.tileLoom.getField(3));
			}
		}

		this.field_178152_f = this.tileLoom.getField(2);
		this.field_178154_h = this.tileLoom.getField(0);
		this.field_178155_i = this.tileLoom.getField(1);
		this.field_178153_g = this.tileLoom.getField(3);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data)
	{
		this.tileLoom.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.tileLoom.isUseableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 3)
			{
				if (!this.mergeItemStack(itemstack1, 4, 39, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (index != 2 && index != 1 && index != 0)
			{
				if (LoomRecipes.instance().getSmeltingResult(itemstack1) != null)
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
				else if (index >= 4 && index < 31)
				{
					if (!this.mergeItemStack(itemstack1, 31, 40, false))
					{
						return null;
					}
				}
				else if (index >= 31 && index < 40 && !this.mergeItemStack(itemstack1, 4, 31, false))
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

			slot.onPickupFromSlot(playerIn, itemstack1);
		}

		return itemstack;
	}
}