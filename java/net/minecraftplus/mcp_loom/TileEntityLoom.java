package net.minecraftplus.mcp_loom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Assets;
import net.minecraftplus._api.dictionary.Sounds;

//@TileEntityFurnace
public class TileEntityLoom extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
	private static final int[] slotsTop = new int[] {0};
	private static final int[] slotsBottom = new int[] {3};
	private static final int[] slotsSides = new int[] {2, 1};
	/** The ItemStacks that hold the items currently being used in the furnace */
	private ItemStack[] loomItemStacks = new ItemStack[4];
	/** The number of ticks that the furnace will keep burning */
	private int loomBurnTime;
	/** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
	private int currentItemBurnTime;
	private int cookTime;
	private int totalCookTime;
	private String loomCustomName;
	private static final String __OBFID = "CL_00000357";

	@Override
	public int getSizeInventory()
	{
		return this.loomItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.loomItemStacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (this.loomItemStacks[index] != null)
		{
			ItemStack itemstack;

			if (this.loomItemStacks[index].stackSize <= count)
			{
				itemstack = this.loomItemStacks[index];
				this.loomItemStacks[index] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.loomItemStacks[index].splitStack(count);

				if (this.loomItemStacks[index].stackSize == 0)
				{
					this.loomItemStacks[index] = null;
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
	public ItemStack getStackInSlotOnClosing(int index)
	{
		if (this.loomItemStacks[index] != null)
		{
			ItemStack itemstack = this.loomItemStacks[index];
			this.loomItemStacks[index] = null;
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
		boolean flag = stack != null && stack.isItemEqual(this.loomItemStacks[index]) && ItemStack.areItemStackTagsEqual(stack, this.loomItemStacks[index]);
		this.loomItemStacks[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}

		if (index == 0 && !flag)
		{
			this.totalCookTime = this.func_174904_a(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.loomCustomName : "container.loom";
	}

	@Override
	public boolean hasCustomName()
	{
		return this.loomCustomName != null && this.loomCustomName.length() > 0;
	}

	public void setCustomInventoryName(String p_145951_1_)
	{
		this.loomCustomName = p_145951_1_;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.loomItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.loomItemStacks.length)
			{
				this.loomItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.loomBurnTime = compound.getShort("BurnTime");
		this.cookTime = compound.getShort("CookTime");
		this.totalCookTime = compound.getShort("CookTimeTotal");
		this.currentItemBurnTime = getItemBurnTime(this.loomItemStacks[1]);

		if (compound.hasKey("CustomName", 8))
		{
			this.loomCustomName = compound.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setShort("BurnTime", (short)this.loomBurnTime);
		compound.setShort("CookTime", (short)this.cookTime);
		compound.setShort("CookTimeTotal", (short)this.totalCookTime);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.loomItemStacks.length; ++i)
		{
			if (this.loomItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.loomItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		compound.setTag("Items", nbttaglist);

		if (this.hasCustomName())
		{
			compound.setString("CustomName", this.loomCustomName);
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	/**
	 * Furnace isBurning
	 */
	public boolean isBurning()
	{
		return this.loomBurnTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory p_174903_0_)
	{
		return p_174903_0_.getField(0) > 0;
	}

	@Override
	public void update()
	{
		boolean flag = this.isBurning();
		boolean flag1 = false;

		if (this.isBurning())
		{
			--this.loomBurnTime;
		}

		if (!this.worldObj.isRemote)
		{
			if (!this.isBurning() && (this.loomItemStacks[2] == null || this.loomItemStacks[1] == null || this.loomItemStacks[0] == null))
			{
				if (!this.isBurning() && this.cookTime > 0)
				{
					this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
				}
			}
			else
			{
				if (!this.isBurning() && this.canSmelt())
				{
					this.currentItemBurnTime = this.loomBurnTime = getItemBurnTime(this.loomItemStacks[1], this.loomItemStacks[2]);

					if (this.isBurning())
					{
						flag1 = true;

						if (this.loomItemStacks[1] != null && this.loomItemStacks[2] != null)
						{
							--this.loomItemStacks[1].stackSize;
							--this.loomItemStacks[2].stackSize;

							if (this.loomItemStacks[1].stackSize == 0)
							{
								this.loomItemStacks[1] = loomItemStacks[1].getItem().getContainerItem(loomItemStacks[2]);
							}

							if (this.loomItemStacks[2].stackSize == 0)
							{
								this.loomItemStacks[2] = loomItemStacks[2].getItem().getContainerItem(loomItemStacks[2]);
							}
						}
					}
				}

				if (this.isBurning() && this.canSmelt())
				{
					++this.cookTime;

					if (this.cookTime == this.totalCookTime)
					{
						this.cookTime = 0;
						this.totalCookTime = this.func_174904_a(this.loomItemStacks[0]);
						this.smeltItem();
						flag1 = true;
						this.worldObj.playSoundEffect((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D, Sounds.MOB_SHEEP_SHEAR, 0.05F, 0.6F);
					}
				}
				else
				{
					this.cookTime = 0;
				}
			}

			if (flag != this.isBurning())
			{
				flag1 = true;
				BlockLoom.setState(this.isBurning(), this.worldObj, this.pos);
			}
		}

		if (flag1)
		{
			this.markDirty();
		}
	}

	public int func_174904_a(ItemStack p_174904_1_)
	{
		return 800;
	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
	 */
	private boolean canSmelt()
	{
		if (this.loomItemStacks[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = LoomRecipes.instance().getSmeltingResult(this.loomItemStacks[0]);
			if (itemstack == null) return false;
			if (this.loomItemStacks[3] == null) return true;
			if (!this.loomItemStacks[3].isItemEqual(itemstack)) return false;
			int result = loomItemStacks[3].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.loomItemStacks[3].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
		}
	}

	/**
	 * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
	 */
	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack itemstack = LoomRecipes.instance().getSmeltingResult(this.loomItemStacks[0]);

			if (this.loomItemStacks[3] == null)
			{
				this.loomItemStacks[3] = itemstack.copy();
			}
			else if (this.loomItemStacks[3].getItem() == itemstack.getItem())
			{
				this.loomItemStacks[3].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
			}

			--this.loomItemStacks[0].stackSize;

			if (this.loomItemStacks[0].stackSize <= 0)
			{
				this.loomItemStacks[0] = null;
			}
		}
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
	 * fuel
	 */
	public static int getItemBurnTime(ItemStack p_145952_0_, ItemStack itemstack)
	{
		int i = getItemBurnTime(p_145952_0_);
		int j = getItemBurnTime(itemstack);
		return i == 0 || j == 0 ? 0 : i + j;
	}

	private static int getItemBurnTime(ItemStack itemstack)
	{
		if (itemstack == null)
		{
			return 0;
		}
		else
		{
			Item item = itemstack.getItem();
			if (item == Items.stick) return 200;
			if (item == Items.blaze_rod) return 800;
			if (item instanceof ItemShears) return 3200;
			return 0;
		}
	}

	public static boolean isItemFuel(ItemStack p_145954_0_)
	{
		/**
		 * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
		 * fuel
		 */
		return getItemBurnTime(p_145954_0_) > 0;
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
		return index == 3 ? false : (index != 1 && index != 2 ? true : isItemFuel(stack));
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
	{
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
		if (direction == EnumFacing.DOWN && (index == 1 || index == 2))
		{
			Item item = stack.getItem();

			if (item != Items.water_bucket && item != Items.bucket)
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public String getGuiID()
	{
		return Assets.resource(_Loom.MODID, "loom");
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerFurnace(playerInventory, this);
	}

	@Override
	public int getField(int id)
	{
		switch (id)
		{
		case 0:
			return this.loomBurnTime;
		case 1:
			return this.currentItemBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value)
	{
		switch (id)
		{
		case 0:
			this.loomBurnTime = value;
			break;
		case 1:
			this.currentItemBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
		}
	}

	@Override
	public int getFieldCount()
	{
		return 4;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < this.loomItemStacks.length; ++i)
		{
			this.loomItemStacks[i] = null;
		}
	}
}