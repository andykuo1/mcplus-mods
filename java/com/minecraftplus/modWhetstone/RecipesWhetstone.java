package com.minecraftplus.modWhetstone;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipesWhetstone implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		boolean flag = false;
		boolean flag1 = false;
		int j = 0;

		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);

			if (itemstack1 != null)
			{
				if (j < 3 && itemstack1.getItem() == MCP_Whetstone.whetstone)
				{
					j++;
					flag = true;
					continue;
				}
				else if (!flag1 && itemstack1.getItem() instanceof ItemTool)
				{
					if (!itemstack1.hasTagCompound() || itemstack1.getTagCompound().getInteger("WhetstoneAttr") <= 32)
					{
						flag1 = true;
						continue;
					}
				}

				return false;
			}
		}

		return flag && flag1;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		ItemStack itemstack = null;
		int j = 0;

		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);

			if (itemstack1 != null)
			{
				if (itemstack1.getItem() == MCP_Whetstone.whetstone)
				{
					j++;
				}
				else if (itemstack1.getItem() instanceof ItemTool)
				{
					itemstack = itemstack1.copy();
				}
			}
		}

		NBTTagCompound nbttagcompound = itemstack.hasTagCompound() ? itemstack.getTagCompound() : new NBTTagCompound();
		int k = nbttagcompound.getInteger("WhetstoneAttr");
		nbttagcompound.setInteger("WhetstoneAttr", (15 * j) + k);
		itemstack.setTagCompound(nbttagcompound);

		return itemstack;
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

