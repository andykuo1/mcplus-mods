package net.minecraftplus._common;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryItem extends InventoryBasic
{
	private boolean isReadingNBT;
	private final ItemStack itemstack;

	public InventoryItem(ItemStack parItemStack, int parInventorySize)
	{
		super(parItemStack.getDisplayName(), false, parInventorySize);

		this.itemstack = parItemStack;
	}

	@Override
	public void openInventory()
	{
		if (!this.hasInventoryTag(this.itemstack))
		{
			NBTTagCompound tag = this.itemstack.hasTagCompound() ? this.itemstack.getTagCompound() : new NBTTagCompound();
			this.saveInventoryToNBT(tag);
			this.itemstack.setTagCompound(tag);
		}

		this.loadInventoryFromNBT(this.itemstack.getTagCompound());
	}

	@Override
	public void closeInventory()
	{
		this.saveInventoryToNBT(this.itemstack.getTagCompound());
	}
	
	@Override
	public void markDirty()
	{
		super.markDirty();
		if (!this.isReadingNBT)
		{
			this.saveInventoryToNBT(this.itemstack.getTagCompound());
		}
	}

	private void loadInventoryFromNBT(NBTTagCompound parNBTTagCompound)
	{
		if (parNBTTagCompound != null)
		{
			this.isReadingNBT = true;

			NBTTagList nbttaglist = (NBTTagList) parNBTTagCompound.getCompoundTag("Inventory").getTag("StackItems");

			int i;
			for (i = 0; i < this.getSizeInventory(); ++i)
			{
				this.setInventorySlotContents(i, (ItemStack)null);
			}

			for (i = 0; i < nbttaglist.tagCount(); ++i)
			{
				NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound.getByte("Slot") & 255;

				if (j >= 0 && j < this.getSizeInventory())
				{
					this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound));
				}
			}

			this.isReadingNBT = false;
		}
	}

	private void saveInventoryToNBT(NBTTagCompound parNBTTagCompound)
	{
		if (parNBTTagCompound != null)
		{
			NBTTagList nbttaglist = new NBTTagList();

			for (int i = 0; i < this.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.getStackInSlot(i);

				if (itemstack != null)
				{
					NBTTagCompound nbttagcompound = new NBTTagCompound();
					nbttagcompound.setByte("Slot", (byte)i);
					itemstack.writeToNBT(nbttagcompound);
					nbttaglist.appendTag(nbttagcompound);
				}
			}

			NBTTagCompound inventory = new NBTTagCompound();
			inventory.setTag("StackItems", nbttaglist);
			parNBTTagCompound.setTag("Inventory", inventory);
		}
	}

	private boolean hasInventoryTag(ItemStack parItemStack)
	{
		NBTTagCompound tag = parItemStack.getTagCompound();
		return tag != null && tag.hasKey("Inventory");
	}
}
