package net.minecraftplus.satchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryEnderSatchel extends InventoryBasic
{
	private final EntityPlayer player;

	public InventoryEnderSatchel(ItemStack parItemStack, EntityPlayer parEntityPlayer, int parInventorySize)
	{
		super(parItemStack.getDisplayName(), false, parInventorySize);

		this.player = parEntityPlayer;
	}

	@Override
	public void openInventory()
	{
		NBTTagList nbttaglist;
		if (!this.hasInventoryTag(this.player))
		{
			this.player.getEntityData().setTag("EnderSatchelItems", nbttaglist = this.saveInventoryToNBT());
		}
		else
		{
			nbttaglist = this.player.getEntityData().getTagList("EnderSatchelItems", 10);
		}

		this.loadInventoryFromNBT(nbttaglist);
	}

	@Override
	public void closeInventory()
	{
		this.player.getEntityData().setTag("EnderSatchelItems", this.saveInventoryToNBT());
	}

	@Override
	public void markDirty()
	{
		super.markDirty();
		this.player.getEntityData().setTag("EnderSatchelItems", this.saveInventoryToNBT());
	}

	private void loadInventoryFromNBT(NBTTagList parNBTTagList)
	{
		int i;

		for (i = 0; i < this.getSizeInventory(); ++i)
		{
			this.setInventorySlotContents(i, (ItemStack)null);
		}

		for (i = 0; i < parNBTTagList.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = parNBTTagList.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.getSizeInventory())
			{
				this.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound));
			}
		}
	}

	private NBTTagList saveInventoryToNBT()
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

		return nbttaglist;
	}

	private boolean hasInventoryTag(EntityPlayer parEntityPlayer)
	{
		return parEntityPlayer.getEntityData().hasKey("EnderSatchelItems", 9);
	}
}
