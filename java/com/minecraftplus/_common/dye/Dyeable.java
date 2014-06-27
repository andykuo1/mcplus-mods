package com.minecraftplus._common.dye;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.minecraftplus._common.entity.EntityUtil;

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

	public static class Entity
	{
		public static void entityInit(DataWatcher par1DataWatcher)
		{
			par1DataWatcher.addObject(EntityUtil.DataWatcher.DYE_COLOR, new Byte((byte) BlockColored.func_150031_c(10)));
		}

		public static boolean interact(IDyeable.Entity parIDyeableEntity, EntityPlayer par2EntityPlayer)
		{
			ItemStack itemstack = par2EntityPlayer.inventory.getCurrentItem();

			if (itemstack != null && itemstack.getItem() == Items.dye)
			{
				int i = BlockColored.func_150031_c(itemstack.getItemDamage());
				if (i != parIDyeableEntity.getCollarColor())
				{
					if (!par2EntityPlayer.capabilities.isCreativeMode)
					{
						--itemstack.stackSize;
					}

					if (itemstack.stackSize <= 0)
					{
						par2EntityPlayer.inventory.setInventorySlotContents(par2EntityPlayer.inventory.currentItem, (ItemStack)null);
					}
					
					parIDyeableEntity.setCollarColor(i);
				}

				return true;
			}

			return false;
		}

		public static int getCollarColor(DataWatcher par1DataWatcher)
		{
			return par1DataWatcher.getWatchableObjectByte(EntityUtil.DataWatcher.DYE_COLOR) & 15;
		}

		public static void setCollarColor(DataWatcher par1DataWatcher, int par2)
		{
			par1DataWatcher.updateObject(EntityUtil.DataWatcher.DYE_COLOR, Byte.valueOf((byte) (par2 & 15)));
		}
	}
}
