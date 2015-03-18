package net.minecraftplus.cart.cargo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftplus.cart.EntityCart;

public class CargoChest extends Cargo implements IInvBasic
{
	private AnimalChest animalChest;

	public CargoChest(EntityCart par1EntityCart)
	{
		super(par1EntityCart);
		this.initCargo();
	}

	public void initCargo()
	{
		AnimalChest animalchest = this.animalChest;
		this.animalChest = this.getAnimalChestNew();
		this.animalChest.func_110133_a(this.theCart.getCommandSenderName());

		if (animalchest != null)
		{
			animalchest.func_110132_b((IInvBasic) this);
			int i = Math.min(animalchest.getSizeInventory(), this.animalChest.getSizeInventory());

			for (int j = 0; j < i; ++j)
			{
				ItemStack itemstack = animalchest.getStackInSlot(j);

				if (itemstack != null)
					this.animalChest.setInventorySlotContents(j, itemstack.copy());
			}

			animalchest = null;
		}

		this.animalChest.func_110134_a(this);
	}

	public void interact(EntityPlayer par1EntityPlayer)
	{
		this.animalChest.func_110133_a("Chest");
		this.openGui(par1EntityPlayer);
	}

	public IInventory getInventory()
	{
		if (this.animalChest == null)
		{
			this.animalChest = getAnimalChestNew();
		}
		return this.animalChest;
	}

	public void readCargoFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		NBTTagList nbttaglist = (NBTTagList) par1NBTTagCompound.getTag("Items");
		this.initCargo();

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j < this.animalChest.getSizeInventory())
			{
				this.animalChest.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
			}
		}
	}

	public void writeCargoToNBT(NBTTagCompound par1NBTTagCompound)
	{
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.animalChest.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.animalChest.getStackInSlot(i);

			if (itemstack != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				itemstack.writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);
	}

	public void dropDeadItems()
	{
		if (this.getWorldObj().isRemote)
		{
			return;
		}

		if (this.animalChest != null)
		{
			for (int i = 0; i < this.animalChest.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.animalChest.getStackInSlot(i);

				if (itemstack != null)
				{
					this.theCart.entityDropItem(itemstack, 0.0F);
				}
			}
		}

		this.theCart.entityDropItem(new ItemStack(Blocks.chest), 0.0F);
	}

	public int getCargoID()
	{
		return 1;
	}

	protected AnimalChest getAnimalChestNew()
	{
		return new AnimalChest("AnimalChest", this.getInventorySize());
	}

	protected int getInventorySize()
	{
		return 27;
	}

	protected static boolean areItemStacksEqualItem(ItemStack par0ItemStack, ItemStack par1ItemStack)
	{
		return par0ItemStack.getItem() != par1ItemStack.getItem() ? false : (par0ItemStack.getItemDamage() != par1ItemStack.getItemDamage() ? false : (par0ItemStack.stackSize > par0ItemStack.getMaxStackSize() ? false : ItemStack.areItemStackTagsEqual(par0ItemStack, par1ItemStack)));
	}

	@Override
	public void onInventoryChanged(InventoryBasic inventorybasic) {}
}
