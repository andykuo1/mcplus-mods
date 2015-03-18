package net.minecraftplus.firepit;

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

public class TileEntityFirePit extends TileEntity implements ISidedInventory
{
	private static final int[] slotsTop = new int[] {0};
	private static final int[] slotsBottom = new int[] {2, 1};
	private static final int[] slotsSides = new int[] {1};

	/**
	 * The ItemStacks that hold the items currently being used in the fire pit
	 */
	private ItemStack[] firePitItemStacks = new ItemStack[3];
	/** The number of ticks that the fire pit will keep burning */
	public int firePitBurnTime;

	/**
	 * The number of ticks that a fresh copy of the currently-burning item would keep the fire pit burning for
	 */
	public int currentItemBurnTime;
	/** The number of ticks that the current item has been cooking for */
	public int firePitCookTime;
	public final int firePitMaxCookTime = 400;
	private String field_145958_o;

	private boolean isItem;

	public TileEntityFirePit(boolean par1)
	{
		this.isItem = par1;
	}

	public TileEntityFirePit()
	{
		this(false);
	}

	public boolean isItem()
	{
		return this.isItem;
	}

	@Override
	public int getSizeInventory()
	{
		return this.firePitItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.firePitItemStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.firePitItemStacks[par1] != null)
		{
			ItemStack itemstack;

			if (this.firePitItemStacks[par1].stackSize <= par2)
			{
				itemstack = this.firePitItemStacks[par1];
				this.firePitItemStacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.firePitItemStacks[par1].splitStack(par2);

				if (this.firePitItemStacks[par1].stackSize == 0)
				{
					this.firePitItemStacks[par1] = null;
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
		if (this.firePitItemStacks[par1] != null)
		{
			ItemStack itemstack = this.firePitItemStacks[par1];
			this.firePitItemStacks[par1] = null;
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
		this.firePitItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.field_145958_o : "container.fire_pit";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return this.field_145958_o != null && this.field_145958_o.length() > 0;
	}

	public void func_145951_a(String par1String)
	{
		this.field_145958_o = par1String;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		this.firePitItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.firePitItemStacks.length)
			{
				this.firePitItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.firePitBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.firePitCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.firePitItemStacks[1]);

		if (par1NBTTagCompound.hasKey("CustomName", 8))
		{
			this.field_145958_o = par1NBTTagCompound.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short)this.firePitBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short)this.firePitCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.firePitItemStacks.length; ++i)
		{
			if (this.firePitItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.firePitItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName())
		{
			par1NBTTagCompound.setString("CustomName", this.field_145958_o);
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	/**
	 * Returns an integer between 0 and the passed value representing how close the current item is to being completely
	 * cooked
	 */
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1)
	{
		return this.firePitCookTime * par1 / this.firePitMaxCookTime;
	}

	/**
	 * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
	 * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
	 */
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1)
	{
		if (this.currentItemBurnTime == 0)
		{
			this.currentItemBurnTime = this.firePitMaxCookTime;
		}

		return this.firePitBurnTime * par1 / this.currentItemBurnTime;
	}

	/**
	 * FirePit isBurning
	 */
	public boolean isBurning()
	{
		return this.firePitBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		boolean flag = this.firePitBurnTime > 0;
		boolean flag1 = false;

		if (this.firePitBurnTime > 0)
		{
			--this.firePitBurnTime;
		}

		if (!this.worldObj.isRemote)
		{
			if (this.firePitBurnTime == 0 && this.canBurn())
			{
				this.currentItemBurnTime = this.firePitBurnTime = getItemBurnTime(this.firePitItemStacks[1]);

				if (this.firePitBurnTime > 0)
				{
					flag1 = true;

					if (this.firePitItemStacks[1] != null)
					{
						--this.firePitItemStacks[1].stackSize;

						if (this.firePitItemStacks[1].stackSize == 0)
						{
							this.firePitItemStacks[1] = firePitItemStacks[1].getItem().getContainerItem(firePitItemStacks[1]);
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt())
			{
				++this.firePitCookTime;

				if (this.firePitCookTime == this.firePitMaxCookTime)
				{
					this.firePitCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			else
			{
				this.firePitCookTime = 0;
			}

			if (flag != this.firePitBurnTime > 0)
			{
				flag1 = true;
				BlockFirePit.updateFirePitBlockState(this.firePitBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1)
		{
			this.markDirty();
		}
	}

	private boolean canBurn()
	{
		if (this.firePitItemStacks[0] == null && ((BlockFirePit) this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord)).isActive())
		{
			return true;
		}

		return this.canSmelt();
	}

	/**
	 * Returns true if the fire pit can smelt an item, i.e. has a source item, destination stack isn't full, etc.
	 */
	private boolean canSmelt()
	{
		if (this.firePitItemStacks[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = FirePitRecipes.smelting().getSmeltingResult(this.firePitItemStacks[0]);
			if (itemstack == null) return false;
			if (this.firePitItemStacks[2] == null) return true;
			if (!this.firePitItemStacks[2].isItemEqual(itemstack)) return false;
			int result = firePitItemStacks[2].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.firePitItemStacks[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
		}
	}

	/**
	 * Turn one item from the fire pit source stack into the appropriate smelted item in the fire pit result stack
	 */
	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack itemstack = FirePitRecipes.smelting().getSmeltingResult(this.firePitItemStacks[0]);

			if (this.firePitItemStacks[2] == null)
			{
				this.firePitItemStacks[2] = itemstack.copy();
			}
			else if (this.firePitItemStacks[2].getItem() == itemstack.getItem())
			{
				this.firePitItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
			}

			--this.firePitItemStacks[0].stackSize;

			if (this.firePitItemStacks[0].stackSize <= 0)
			{
				this.firePitItemStacks[0] = null;
			}
		}
	}

	public static int getItemBurnTime(ItemStack par1ItemStack)
	{
		return TileEntityFurnace.getItemBurnTime(par1ItemStack) * 2;
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the fire pit burning, or 0 if the item isn't
	 * fuel
	 */
	public static boolean isItemFuel(ItemStack par1ItemStack)
	{
		return getItemBurnTime(par1ItemStack) > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return par3 != 0 || par1 != 1 || par2ItemStack.getItem() == Items.bucket;
	}
}
