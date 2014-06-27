package com.minecraftplus.modGiftBox;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class RecipesGiftBox implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		ItemStack itemstack = null;

		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);

			if (itemstack1 != null)
			{
				if (i == 4)
				{
					if (itemstack != null)
					{
						return false;
					}

					itemstack = itemstack1;
				}
				else
				{
					if (itemstack1.getItem() != Items.paper)
					{
						return false;
					}
				}
			}
			else
			{
				return false;
			}
		}

		return itemstack != null;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		ItemStack itemstack = par1InventoryCrafting.getStackInSlot(4);
		ItemStack giftstack = new ItemStack(MCP_GiftBox.giftBox);

		NBTTagCompound nbttagcompound = giftstack.hasTagCompound() ? giftstack.getTagCompound() : new NBTTagCompound();
		NBTTagList nbttaglist = new NBTTagList();

		if (itemstack != null)
		{
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setByte("Slot", (byte) 0);
			itemstack.writeToNBT(nbttagcompound1);
			nbttaglist.appendTag(nbttagcompound1);
		}

		NBTTagCompound nbttagcompound2 = new NBTTagCompound();
		nbttagcompound2.setTag("StackItems", nbttaglist);

		nbttagcompound.setTag("Inventory", nbttagcompound2);
		giftstack.setTagCompound(nbttagcompound);

		return giftstack;
	}

	@Override
	public int getRecipeSize()
	{
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return null;
	}
}
