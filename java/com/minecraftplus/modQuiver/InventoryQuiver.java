package com.minecraftplus.modQuiver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.minecraftplus._common.container.InventoryItem;

public class InventoryQuiver extends InventoryItem
{
	protected int inventorySize;
	private boolean onArmor;

	public InventoryQuiver(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack, int par3, boolean par4)
	{
		super(par1EntityPlayer, par2ItemStack, par3);
		this.onArmor = par4;
	}

	public void loadInventoryFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.loadInventoryFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound != null)
		{
			this.setSizeInventory(par1NBTTagCompound.getInteger("Size"));
		}
	}

	public NBTTagCompound saveInventoryToNBT(NBTTagCompound par1NBTTagCompound)
	{
		if (par1NBTTagCompound != null)
		{
			par1NBTTagCompound.setInteger("Size", this.getSizeInventory());
		}

		return super.saveInventoryToNBT(par1NBTTagCompound);
	}

	private void setSizeInventory(int par1)
	{
		this.inventorySize = par1;
	}

	@Override
	public int getSizeInventory()
	{
		return this.inventorySize > 0 ? this.inventorySize : super.getSizeInventory();
	}

	public int getLengthInventory()
	{
		return 9;
	}

	public int getBaseHeight()
	{
		return (-this.getHeight() / this.getLengthInventory()) - this.getHeight() / 6;
	}

	public int getHeight()
	{
		return 18 * this.getRows();
	}

	public int getRows()
	{
		return (int) Math.ceil(this.getSizeInventory() / (double) this.getLengthInventory());
	}

	public int getWidth()
	{
		return 18 * this.getColumns();
	}

	public int getColumns()
	{
		return this.getSizeInventory() - (this.getRows() * this.getLengthInventory()) + this.getLengthInventory();
	}

	public int getCustomAlignment()
	{
		return 81 - this.getWidth() / 2;
	}

	public ItemStack getCurrentItemStack(EntityPlayer par1EntityPlayer)
	{
		return this.onArmor ? par1EntityPlayer.getCurrentArmor(1) : par1EntityPlayer.getCurrentEquippedItem();
	}
}
