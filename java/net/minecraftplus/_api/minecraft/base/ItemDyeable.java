package net.minecraftplus._api.minecraft.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDyeable extends Item
{
	protected int getDefaultColor()
	{
		return 10511680;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass)
	{
		//Compare to: @ItemArmor
		if (renderPass > 0)
		{
			return 16777215;
		}
		else
		{
			int j = this.getColor(stack);

			if (j < 0)
			{
				j = this.getDefaultColor();
			}

			return j;
		}
	}

	public boolean hasColor(ItemStack parItemStack)
	{
		//Compare to: @ItemArmor
		return !parItemStack.hasTagCompound() ? false : (!parItemStack.getTagCompound().hasKey("display", 10) ? false : parItemStack.getTagCompound().getCompoundTag("display").hasKey("color", 3));
	}

	public int getColor(ItemStack parItemStack)
	{
		//Compare to: @ItemArmor
		NBTTagCompound nbttagcompound = parItemStack.getTagCompound();

		if (nbttagcompound != null)
		{
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3))
			{
				return nbttagcompound1.getInteger("color");
			}
		}

		return this.getDefaultColor();
	}

	public void removeColor(ItemStack stack)
	{
		//Compare to: @ItemArmor
		NBTTagCompound nbttagcompound = stack.getTagCompound();

		if (nbttagcompound != null)
		{
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1.hasKey("color"))
			{
				nbttagcompound1.removeTag("color");
			}
		}
	}

	public void setColor(ItemStack parItemStack, int parColor)
	{
		//Compare to: @ItemArmor
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
