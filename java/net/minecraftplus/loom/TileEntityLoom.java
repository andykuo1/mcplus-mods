package net.minecraftplus.loom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityLoom extends TileEntity implements ISidedInventory
{
	private static final int[] slots_top = new int[] {0};
	private static final int[] slots_bottom = new int[] {3};
	private static final int[] slots_sides = new int[] {2, 1};

	/**
	 * The ItemStacks that hold the items currently being used in the loom
	 */
	private ItemStack[] loomItemStacks = new ItemStack[4];

	/** The number of ticks that the loom will keep burning */
	public int loomBurnTime;

	private final int loomBurnTimeMax = 400;
	/**
	 * The number of ticks that a fresh copy of the currently-burning item would keep the loom burning for
	 */
	public int currentItemBurnTime;

	/** The number of ticks that the current item has been cooking for */
	public int loomCookTime;
	private String field_94130_e;

	@Override
	public int getSizeInventory()
	{
		return this.loomItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.loomItemStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.loomItemStacks[par1] != null)
		{
			ItemStack itemstack;

			if (this.loomItemStacks[par1].stackSize <= par2)
			{
				itemstack = this.loomItemStacks[par1];
				this.loomItemStacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.loomItemStacks[par1].splitStack(par2);

				if (this.loomItemStacks[par1].stackSize == 0)
				{
					this.loomItemStacks[par1] = null;
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
		if (this.loomItemStacks[par1] != null)
		{
			ItemStack itemstack = this.loomItemStacks[par1];
			this.loomItemStacks[par1] = null;
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
		this.loomItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.field_94130_e : "container.wooden_loom";
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

		//TODO: getTagList
		NBTTagList nbttaglist = (NBTTagList) par1NBTTagCompound.getTag("Items");

		this.loomItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			//TODO: tagAt
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.loomItemStacks.length)
			{
				this.loomItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.loomBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.loomCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getTotalBurnTime();

		if (par1NBTTagCompound.hasKey("CustomName"))
		{
			this.field_94130_e = par1NBTTagCompound.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short)this.loomBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short)this.loomCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.loomItemStacks.length; ++i)
		{
			if (this.loomItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.loomItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
				System.out.println("SAVE");
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
		return this.loomCookTime * par1 / this.loomBurnTimeMax;
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
			this.currentItemBurnTime = this.loomBurnTimeMax;
		}

		return this.loomBurnTime * par1 / this.currentItemBurnTime;
	}

	/**
	 * Returns true if the loom is currently burning
	 */
	public boolean isBurning()
	{
		return this.loomBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		boolean flag = this.loomBurnTime > 0;
		boolean flag1 = false;

		if (this.loomBurnTime > 0)
		{
			--this.loomBurnTime;
		}

		if (!this.worldObj.isRemote)
		{
			if (this.loomBurnTime == 0 && this.canSmelt())
			{
				this.currentItemBurnTime = this.loomBurnTime = getTotalBurnTime();

				if (this.loomBurnTime > 0)
				{
					flag1 = true;

					if (this.loomItemStacks[1] != null && this.loomItemStacks[2] != null)
					{
						--this.loomItemStacks[1].stackSize;
						--this.loomItemStacks[2].stackSize;

						if (this.loomItemStacks[1].stackSize == 0)
						{
							this.loomItemStacks[1] = this.loomItemStacks[1].getItem().getContainerItem(loomItemStacks[1]);
						}

						if (this.loomItemStacks[2].stackSize == 0)
						{
							this.loomItemStacks[2] = this.loomItemStacks[2].getItem().getContainerItem(loomItemStacks[2]);
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt())
			{
				++this.loomCookTime;

				if (this.loomCookTime == this.loomBurnTimeMax)
				{
					this.loomCookTime = 0;
					this.smeltItem();
					flag1 = true;
					this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "mob.sheep.shear", 0.05F, 0.6F);
				}
			}
			else
			{
				this.loomCookTime = 0;
			}

			if (flag != this.loomBurnTime > 0)
			{
				flag1 = true;
				BlockLoom.updateLoomBlockState(this.loomBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (flag1)
		{
			this.markDirty();
		}
	}

	/**
	 * Returns true if the loom can smelt an item, i.e. has a source item, destination stack isn't full, etc.
	 */
	private boolean canSmelt()
	{
		if (this.loomItemStacks[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = LoomRecipes.smelting().getSmeltingResult(this.loomItemStacks[0]);
			if (itemstack == null) return false;
			if (this.loomItemStacks[3] == null) return true;
			if (!this.loomItemStacks[3].isItemEqual(itemstack)) return false;
			int result = loomItemStacks[3].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	/**
	 * Turn one item from the loom source stack into the appropriate smelted item in the loom result stack
	 */
	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack itemstack = LoomRecipes.smelting().getSmeltingResult(this.loomItemStacks[0]);

			if (this.loomItemStacks[3] == null)
			{
				this.loomItemStacks[3] = itemstack.copy();
			}
			else if (this.loomItemStacks[3].isItemEqual(itemstack))
			{
				loomItemStacks[3].stackSize += itemstack.stackSize;
			}

			--this.loomItemStacks[0].stackSize;

			if (this.loomItemStacks[0].stackSize <= 0)
			{
				this.loomItemStacks[0] = null;
			}
		}
	}

	/**
	 * Returns the number of total ticks that all the supplied fuel items will keep the loom burning, or 0 if the item isn't
	 * fuel
	 */
	public int getTotalBurnTime()
	{
		int i = getItemBurnTime(this.loomItemStacks[1]);
		int j = getItemBurnTime(this.loomItemStacks[2]);
		return i == 0 || j == 0 ? 0 : i + j;
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the loom burning, or 0 if the item isn't
	 * fuel
	 */
	public static int getItemBurnTime(ItemStack par0ItemStack)
	{
		if (par0ItemStack == null)
		{
			return 0;
		}
		else
		{
			Item item = par0ItemStack.getItem();
			if (item == Items.stick) return 200;
			if (item == Items.blaze_rod) return 800;
			if (item instanceof ItemShears) return 3200;
			return GameRegistry.getFuelValue(par0ItemStack);
		}
	}

	/**
	 * Return true if item is a fuel source (getItemBurnTime() > 0).
	 */
	public static boolean isItemFuel(ItemStack par0ItemStack)
	{
		return getItemBurnTime(par0ItemStack) > 0;
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
		return par1 == 3 ? false : (par1 == 1 || par1 == 2 ? isItemFuel(par2ItemStack) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
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
