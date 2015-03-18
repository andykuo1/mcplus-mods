package net.minecraftplus._common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemDyeable extends Item implements IItemDyeable
{
	protected int getWhiteColor()
	{
		return 16777215;
	}

	protected int getDefaultColor()
	{
		return 10511680;
	}

	@Override
	public int getColorFromItemStack(ItemStack parItemStack, int parColor)
	{
		if (parColor > 0)
		{
			return this.getWhiteColor();
		}
		else
		{
			int j = this.getColor(parItemStack);

			if (j < 0)
			{
				j = this.getWhiteColor();
			}

			return j;
		}
	}

	@Override
	public boolean hasColor(ItemStack parItemStack)
	{
		return !parItemStack.hasTagCompound() ? false : (!parItemStack.getTagCompound().hasKey("display", 10) ? false : parItemStack.getTagCompound().getCompoundTag("display").hasKey("color", 3));
	}

	@Override
	public int getColor(ItemStack parItemStack)
	{
		NBTTagCompound nbttagcompound = parItemStack.getTagCompound();

		if (nbttagcompound == null)
		{
			return this.getDefaultColor();
		}
		else
		{
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
			return nbttagcompound1 == null ? this.getDefaultColor() : (nbttagcompound1.hasKey("color", 3) ? nbttagcompound1.getInteger("color") : this.getDefaultColor());
		}
	}

	@Override
	public void removeColor(ItemStack parItemStack)
	{
		NBTTagCompound nbttagcompound = parItemStack.getTagCompound();

		if (nbttagcompound != null)
		{
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1.hasKey("color"))
			{
				nbttagcompound1.removeTag("color");
			}
		}
	}

	@Override
	public void setColor(ItemStack parItemStack, int parColor)
	{
		NBTTagCompound nbttagcompound = parItemStack.getTagCompound();

		if (nbttagcompound == null)
		{
			nbttagcompound = new NBTTagCompound();
			parItemStack.setTagCompound(nbttagcompound);
		}

		NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

		if (!nbttagcompound.hasKey("display", 10))
		{
			nbttagcompound.setTag("display", nbttagcompound1);
		}

		nbttagcompound1.setInteger("color", parColor);
	}
}
