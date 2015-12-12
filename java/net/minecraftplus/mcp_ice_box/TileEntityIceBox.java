package net.minecraftplus.mcp_ice_box;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftplus.mcp_rotten.EventHandlerRotten;

public class TileEntityIceBox extends TileEntity implements IInventory
{
	private ItemStack[] stacks = new ItemStack[9];
	protected String customName;

	public static void freezeItemStack(World world, ItemStack itemstack)
	{
		if (itemstack != null && EventHandlerRotten.isRotItem(itemstack.getItem()) && itemstack.hasTagCompound())
		{
			int time = EventHandlerRotten.getTime(world);
			NBTTagCompound tagcompound = itemstack.getTagCompound();

			if (!tagcompound.hasKey("rottime"))
			{
				tagcompound.setInteger("rottime", time);
			}
			else
			{
				int rottime = time - EventHandlerRotten.getSavedRotTime(world, itemstack);
				tagcompound.setInteger("rottime", rottime);
			}

			tagcompound.setInteger("worldtime", time);
		}
	}

	public static void freezeInventory(World world, IInventory inventory)
	{
		for(int i = 0; i < inventory.getSizeInventory(); ++i)
		{
			ItemStack itemstack = inventory.getStackInSlot(i);
			freezeItemStack(world, itemstack);
		}
	}

	public static boolean containsIceInInventory(IInventory inventory)
	{
		for(int i = 0; i < inventory.getSizeInventory(); ++i)
		{
			ItemStack itemstack = inventory.getStackInSlot(i);
			if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock(Blocks.ice))
			{
				return true;
			}
		}

		return false;
	}

	public static void consumeIceInInventory(World world, IInventory inventory)
	{
		boolean flag = false;
		for(int i = 0; i < inventory.getSizeInventory(); ++i)
		{
			ItemStack itemstack = inventory.getStackInSlot(i);
			if (itemstack != null && itemstack.getItem() == Item.getItemFromBlock(Blocks.ice))
			{
				itemstack.stackSize--;
				flag = true;
				break;
			}
		}

		if (flag)
		{
			freezeInventory(world, inventory);
		}
	}

	@Override
	public int getSizeInventory()
	{
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.stacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (this.stacks[index] != null)
		{
			ItemStack itemstack;

			if (this.stacks[index].stackSize <= count)
			{
				itemstack = this.stacks[index];
				this.stacks[index] = null;
				this.markDirty();
				return itemstack;
			}
			else
			{
				itemstack = this.stacks[index].splitStack(count);

				if (this.stacks[index].stackSize == 0)
				{
					this.stacks[index] = null;
				}

				this.markDirty();
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		if (this.stacks[index] != null)
		{
			ItemStack itemstack = this.stacks[index];
			this.stacks[index] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		this.stacks[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	/**
	 * Add the given ItemStack to this Dispenser. Return the Slot the Item was placed in or -1 if no free slot is
	 * available.
	 */
	public int addItemStack(ItemStack stack)
	{
		for (int i = 0; i < this.stacks.length; ++i)
		{
			if (this.stacks[i] == null || this.stacks[i].getItem() == null)
			{
				this.setInventorySlotContents(i, stack);
				return i;
			}
		}

		return -1;
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.customName : "container.ice_box";
	}

	public void setCustomName(String customName)
	{
		this.customName = customName;
	}

	/**
	 * Returns true if this thing is named
	 */
	public boolean hasCustomName()
	{
		return this.customName != null;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.stacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.stacks.length)
			{
				this.stacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		if (compound.hasKey("CustomName", 8))
		{
			this.customName = compound.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.stacks.length; ++i)
		{
			if (this.stacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.stacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		compound.setTag("Items", nbttaglist);

		if (this.hasCustomName())
		{
			compound.setString("CustomName", this.customName);
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return true;
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < this.stacks.length; ++i)
		{
			this.stacks[i] = null;
		}
	}

	@Override
	public IChatComponent getDisplayName()
	{
		//@TileEntityLockable
		return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
	}
}
