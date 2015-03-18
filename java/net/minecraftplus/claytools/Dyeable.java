package net.minecraftplus.claytools;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Dyeable
{
	public static class Item
	{
		@SideOnly(Side.CLIENT)
		public static int getColorFromItemStack(ItemStack par1ItemStack, int par2, int par3)
		{
			if (par2 == 1)
			{
				return par3;
			}
			else
			{
				int i = ((IDyeable.Item) par1ItemStack.getItem()).getColor(par1ItemStack);
				return i < 0 ? par3 : i;
			}
		}

		public static boolean hasColor(ItemStack par1ItemStack)
		{
			return (!par1ItemStack.hasTagCompound() ? false : (!par1ItemStack.getTagCompound().hasKey("display") ? false : par1ItemStack.getTagCompound().getCompoundTag("display").hasKey("color")));
		}

		public static int getColor(ItemStack par1ItemStack, int par2)
		{
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			if (nbttagcompound == null)
			{
				return par2;
			}
			else
			{
				NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
				return nbttagcompound1 == null ? par2 : (nbttagcompound1.hasKey("color") ? nbttagcompound1.getInteger("color") : par2);
			}
		}

		public static void setColor(ItemStack par1ItemStack, int par2)
		{
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			if (nbttagcompound == null)
			{
				nbttagcompound = new NBTTagCompound();
				par1ItemStack.setTagCompound(nbttagcompound);
			}

			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
			if (!nbttagcompound.hasKey("display"))
			{
				nbttagcompound.setTag("display", nbttagcompound1);
			}

			nbttagcompound1.setInteger("color", par2);
		}

		public static void removeColor(ItemStack par1ItemStack)
		{
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			if (nbttagcompound != null)
			{
				NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
				if (nbttagcompound1.hasKey("color"))
				{
					nbttagcompound1.removeTag("color");
				}
			}
		}
	}
}
