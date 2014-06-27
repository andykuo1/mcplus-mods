package com.minecraftplus._common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class InventoryItem extends InventoryBasic
{
	protected final EntityPlayer thePlayer;
	protected final ItemStack theItemStack;

	private boolean isReadingNBT;

	public InventoryItem(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack, int par3)
	{
		super(par2ItemStack.getDisplayName(), true, par3);
		this.thePlayer = par1EntityPlayer;
		this.theItemStack = par2ItemStack;

		this.openInventory();
	}

	public abstract ItemStack getCurrentItemStack(EntityPlayer par1EntityPlayer);

	@Override
	public void openInventory()
	{
		if (!hasInventoryTag(this.theItemStack))
		{
			NBTTagCompound nbttag = this.theItemStack.hasTagCompound() ? this.theItemStack.getTagCompound() : new NBTTagCompound();
			this.saveInventoryToNBT(nbttag);
			this.theItemStack.setTagCompound(nbttag);
		}

		this.loadInventoryFromNBT(this.theItemStack.getTagCompound());
	}

	@Override
	public void closeInventory()
	{
		this.saveInventory();
	}

	public void saveInventory()
	{
		this.saveInventoryToNBT(this.theItemStack.getTagCompound());
		if (this.thePlayer.getCurrentEquippedItem() != null)
		{
			setCopyTagCompound(this.getCurrentItemStack(this.thePlayer), this.theItemStack);
		}
	}

	public void loadInventoryFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		if (par1NBTTagCompound != null)
		{
			this.isReadingNBT = true;

			NBTTagList nbttaglist = (NBTTagList) par1NBTTagCompound.getCompoundTag("Inventory").getTag("StackItems");

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

	public NBTTagCompound saveInventoryToNBT(NBTTagCompound par1NBTTagCompound)
	{
		if (par1NBTTagCompound != null)
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
			par1NBTTagCompound.setTag("Inventory", inventory);
		}

		return par1NBTTagCompound;
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		if (!this.isReadingNBT)
		{
			this.saveInventory();
		}
	}

	public static boolean hasInventoryTag(ItemStack par1ItemStack)
	{
		return (par1ItemStack.getTagCompound() != null) && (par1ItemStack.getTagCompound().hasKey("Inventory"));
	}

	public static void setCopyTagCompound(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		par1ItemStack.setTagCompound(par2ItemStack.getTagCompound());
	}
}
