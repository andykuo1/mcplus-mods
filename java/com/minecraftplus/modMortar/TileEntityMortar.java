package com.minecraftplus.modMortar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMortar extends TileEntity implements ISidedInventory
{
	public static final int[] slots_input = new int[] {0, 1, 2};
	public static final int[] slots_burnable = new int[] {3};
	public static final int[] slots_output = new int[] {4};

	/**
	 * The ItemStacks that hold the items currently being used in the tile entity
	 */
	private ItemStack[] mortarItemStacks = new ItemStack[slots_input.length + slots_output.length + slots_burnable.length];

	/** The number of ticks that the tile entity will keep burning */
	public int mortarBurnTime;

	private final int mortarBurnTimeMax = 100;
	/**
	 * The number of ticks that a fresh copy of the currently-burning item would keep the tile entity burning for
	 */
	public int currentItemBurnTime;

	/** The number of ticks that the current item has been cooking for */
	public int mortarCookTime;
	private String field_94130_e = "Mortar";

	@Override
	public int getSizeInventory()
	{
		return this.mortarItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.mortarItemStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.mortarItemStacks[par1] != null)
		{
			ItemStack itemstack;

			if (this.mortarItemStacks[par1].stackSize <= par2)
			{
				itemstack = this.mortarItemStacks[par1];
				this.mortarItemStacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.mortarItemStacks[par1].splitStack(par2);

				if (this.mortarItemStacks[par1].stackSize == 0)
				{
					this.mortarItemStacks[par1] = null;
				}

				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.mortarItemStacks[par1] != null)
		{
			ItemStack itemstack = this.mortarItemStacks[par1];
			this.mortarItemStacks[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.mortarItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.field_94130_e : "container.mortar";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return this.field_94130_e != null && this.field_94130_e.length() > 0;
	}

	/**
	 * Sets the custom display name to use when opening a GUI linked to this tile entity.
	 */
	public void setGuiDisplayName(String par1Str)
	{
		this.field_94130_e = par1Str;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = (NBTTagList) par1NBTTagCompound.getTag("Items");
		this.mortarItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.mortarItemStacks.length)
			{
				this.mortarItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.mortarBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.mortarCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.mortarItemStacks[slots_burnable[0]]);

		if (par1NBTTagCompound.hasKey("CustomName"))
		{
			this.field_94130_e = par1NBTTagCompound.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short)this.mortarBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short)this.mortarCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.mortarItemStacks.length; ++i)
		{
			if (this.mortarItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.mortarItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName())
		{
			par1NBTTagCompound.setString("CustomName", this.field_94130_e);
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns an integer between 0 and the passed value representing how close the current item is to being completely
	 * cooked
	 */
	public int getCookProgressScaled(int par1)
	{
		return this.mortarCookTime * par1 / this.mortarBurnTimeMax;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
	 * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
	 */
	public int getBurnTimeRemainingScaled(int par1)
	{
		if (this.currentItemBurnTime == 0)
		{
			this.currentItemBurnTime = this.mortarBurnTimeMax;
		}

		return this.mortarBurnTime * par1 / this.currentItemBurnTime;
	}

	/**
	 * Returns true if the tile entity is currently burning
	 */
	public boolean isBurning()
	{
		return this.mortarBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		boolean flag = this.mortarBurnTime > 0;
		boolean flag1 = false;

		if (this.mortarBurnTime > 0)
		{
			--this.mortarBurnTime;
		}

		if (!this.worldObj.isRemote)
		{
			if (this.mortarBurnTime == 0 && this.canSmelt())
			{
				this.currentItemBurnTime = this.mortarBurnTime = getItemBurnTime(this.mortarItemStacks[slots_burnable[0]]);

				if (this.mortarBurnTime > 0)
				{
					flag1 = true;
					if (this.mortarItemStacks[slots_burnable[0]] != null)
					{
						--this.mortarItemStacks[slots_burnable[0]].stackSize;
						if (this.mortarItemStacks[slots_burnable[0]].stackSize == 0)
						{
							this.mortarItemStacks[slots_burnable[0]] = this.mortarItemStacks[slots_burnable[0]].getItem().getContainerItem(mortarItemStacks[slots_burnable[0]]);
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt())
			{
				++this.mortarCookTime;

				if (this.mortarCookTime == this.mortarBurnTimeMax)
				{
					this.mortarCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			else
			{
				this.mortarCookTime = 0;
			}

			if (flag != this.mortarBurnTime > 0)
			{
				flag1 = true;
				BlockMortar.updateDisposerBlockState(this.mortarBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1)
		{
			this.markDirty();
		}
	}

	/**
	 * Returns true if the tile entity can smelt an item, i.e. has a source item, destination stack isn't full, etc.
	 */
	private boolean canSmelt()
	{
		ItemStack[] itemstacks = new ItemStack[slots_input.length];
		for(int i = 0; i < itemstacks.length; i++)
		{
			itemstacks[i] = this.mortarItemStacks[slots_input[i]];
		}

		ItemStack result = MortarRecipes.smelting().getSmeltingResult(itemstacks);
		if (result == null) return false;

		boolean flag = true;
		int j = 0;
		if (this.mortarItemStacks[slots_output[0]] != null)
		{
			if (this.mortarItemStacks[slots_output[0]].isItemEqual(result))
			{
				int resultSize = this.mortarItemStacks[slots_output[0]].stackSize + result.stackSize;
				if (!(resultSize <= getInventoryStackLimit() && resultSize <= result.getMaxStackSize()))
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Turn one item from the tile entity source stack into the appropriate smelted item in the tile entity result stack
	 */
	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack[] itemstacks = new ItemStack[slots_input.length];
			for(int i = 0; i < itemstacks.length; i++)
			{
				itemstacks[i] = this.mortarItemStacks[slots_input[i]];
			}

			ItemStack result = MortarRecipes.smelting().getSmeltingResult(itemstacks);

			int j = slots_output[0];
			if (this.mortarItemStacks[j] == null)
			{
				this.mortarItemStacks[j] = result.copy();
			}
			else if (this.mortarItemStacks[j].isItemEqual(result))
			{
				this.mortarItemStacks[j].stackSize += result.stackSize;
			}

			for(int k : slots_input)
			{
				--this.mortarItemStacks[k].stackSize;

				if (this.mortarItemStacks[k].stackSize <= 0)
				{
					this.mortarItemStacks[k] = null;
				}
			}
		}
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the tile entity burning, or 0 if the item isn't
	 * fuel
	 */
	public static int getItemBurnTime(ItemStack par1ItemStack)
	{
		if (par1ItemStack != null && par1ItemStack.getItem() == Items.bowl)
		{
			return 400;
		}
		
		return 0;
	}

	/**
	 * Return true if item is a fuel source (getItemBurnTime() > 0).
	 */
	public static boolean isItemFuel(ItemStack par1ItemStack)
	{
		return getItemBurnTime(par1ItemStack) > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 == slots_output[0] ? false : par1 == slots_burnable[0] ? isItemFuel(par2ItemStack) : true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slots_output : par1 == 1 ? slots_input : slots_burnable;
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return par3 != 0 || par1 != slots_burnable[0] || par2ItemStack.getItem() == Items.bucket;
	}
}