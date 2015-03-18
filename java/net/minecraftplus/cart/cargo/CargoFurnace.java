package net.minecraftplus.cart.cargo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftplus.cart.EntityCart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CargoFurnace extends Cargo implements ISidedInventory
{
	private boolean isActive = false;

	private static final int[] slots_top = new int[] {0};
	private static final int[] slots_bottom = new int[] {2, 1};
	private static final int[] slots_sides = new int[] {1};

	/**
	 * The ItemStacks that hold the items currently being used in the furnace
	 */
	private ItemStack[] furnaceItemStacks = new ItemStack[3];

	/** The number of ticks that the furnace will keep burning */
	public int furnaceBurnTime;

	/**
	 * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
	 */
	public int currentItemBurnTime;

	/** The number of ticks that the current item has been cooking for */
	public int furnaceCookTime;
	private String field_94130_e;

	public CargoFurnace(EntityCart par1EntityCart)
	{
		super(par1EntityCart);
	}

	public int getCargoID()
	{
		return 5;
	}

	public void onUpdate()
	{
		boolean flag = this.furnaceBurnTime > 0;
		boolean flag1 = false;

		if (this.furnaceBurnTime > 0)
		{
			--this.furnaceBurnTime;
		}

		if (!this.getWorldObj().isRemote)
		{
			if (this.furnaceBurnTime == 0 && this.canSmelt())
			{
				this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

				if (this.furnaceBurnTime > 0)
				{
					flag1 = true;

					if (this.furnaceItemStacks[1] != null)
					{
						--this.furnaceItemStacks[1].stackSize;

						if (this.furnaceItemStacks[1].stackSize == 0)
						{
							this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt())
			{
				++this.furnaceCookTime;

				if (this.furnaceCookTime == 200)
				{
					this.furnaceCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			else
			{
				this.furnaceCookTime = 0;
			}

			if (flag != this.furnaceBurnTime > 0)
			{
				flag1 = true;
			}
		}

		if (flag1)
		{
			this.markDirty();
		}

		this.setActive(this.furnaceBurnTime > 0);
	}

	public void interact(EntityPlayer par1EntityPlayer)
	{
		this.openGui(par1EntityPlayer);
	}

	public void readCargoFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.furnaceItemStacks.length)
			{
				this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

		//TODO; func_150297_b
		if (par1NBTTagCompound.hasKey("CustomName", 8))
		{
			this.field_94130_e = par1NBTTagCompound.getString("CustomName");
		}
	}

	public void writeCargoToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.furnaceItemStacks.length; ++i)
		{
			if (this.furnaceItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (this.hasCustomInventoryName())
		{
			par1NBTTagCompound.setString("CustomName", this.field_94130_e);
		}
	}

	public void dropDeadItems()
	{
		if (this.getWorldObj().isRemote)
		{
			return;
		}

		for (int j1 = 0; j1 < this.getSizeInventory(); ++j1)
		{
			ItemStack itemstack = this.getStackInSlot(j1);

			if (itemstack != null)
			{
				this.theCart.entityDropItem(itemstack.copy(), 0.0F);
			}
		}

		this.theCart.entityDropItem(new ItemStack(Blocks.furnace), 0.0F);
	}

	@Override
	public int getSizeInventory()
	{
		return this.furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.furnaceItemStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.furnaceItemStacks[par1] != null)
		{
			ItemStack itemstack;

			if (this.furnaceItemStacks[par1].stackSize <= par2)
			{
				itemstack = this.furnaceItemStacks[par1];
				this.furnaceItemStacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.furnaceItemStacks[par1].splitStack(par2);

				if (this.furnaceItemStacks[par1].stackSize == 0)
				{
					this.furnaceItemStacks[par1] = null;
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
		if (this.furnaceItemStacks[par1] != null)
		{
			ItemStack itemstack = this.furnaceItemStacks[par1];
			this.furnaceItemStacks[par1] = null;
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
		this.furnaceItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return this.hasCustomInventoryName() ? this.field_94130_e : "container.furnace";
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
		return this.furnaceCookTime * par1 / 200;
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
			this.currentItemBurnTime = 200;
		}

		return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
	}

	/**
	 * Returns true if the furnace is currently burning
	 */
	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
	 */
	private boolean canSmelt()
	{
		if (this.furnaceItemStacks[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
			if (itemstack == null) return false;
			if (this.furnaceItemStacks[2] == null) return true;
			if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) return false;
			int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	/**
	 * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
	 */
	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

			if (this.furnaceItemStacks[2] == null)
			{
				this.furnaceItemStacks[2] = itemstack.copy();
			}
			else if (this.furnaceItemStacks[2].isItemEqual(itemstack))
			{
				furnaceItemStacks[2].stackSize += itemstack.stackSize;
			}

			--this.furnaceItemStacks[0].stackSize;

			if (this.furnaceItemStacks[0].stackSize <= 0)
			{
				this.furnaceItemStacks[0] = null;
			}
		}
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
	 * fuel
	 */
	public static int getItemBurnTime(ItemStack par1ItemStack)
	{
		return TileEntityFurnace.getItemBurnTime(par1ItemStack);
	}

	/**
	 * Return true if item is a fuel source (getItemBurnTime() > 0).
	 */
	public static boolean isItemFuel(ItemStack par0ItemStack)
	{
		return getItemBurnTime(par0ItemStack) > 0;
	}

	private void setActive(boolean par1)
	{
		this.isActive = par1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return true;
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

	@Override
	public void markDirty() {}
}
