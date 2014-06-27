package com.minecraftplus.modClayWallSlab;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesClayWall implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		for (int i = 0; i < 3; ++i)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);

			if (itemstack1 != null && itemstack1.getItem() == Item.getItemFromBlock(Blocks.stained_hardened_clay))
			{
				itemstack1 = par1InventoryCrafting.getStackInSlot(i += 3);
				if (itemstack1 != null && itemstack1.getItem() == Item.getItemFromBlock(Blocks.stained_hardened_clay))
				{
					itemstack1 = par1InventoryCrafting.getStackInSlot(i += 3);
					if (itemstack1 != null && itemstack1.getItem() == Item.getItemFromBlock(Blocks.stained_hardened_clay))
					{
						return true;
					}
				}

				return false;
			}
		}

		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		ItemStack itemstack = par1InventoryCrafting.getStackInSlot(0);

		for (int i = 1; i < par1InventoryCrafting.getSizeInventory(); ++i)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);

			if (itemstack1 != null && itemstack1.getItem() == Item.getItemFromBlock(Blocks.stained_hardened_clay))
			{
				System.out.println(itemstack1.getItemDamage());
				if (itemstack == null || itemstack1.getItemDamage() > itemstack.getItemDamage())
				{
					itemstack = itemstack1;
				}
			}
		}

		return new ItemStack(BlockClayWallSlab.getBlockWithColor(itemstack.getItemDamage(), true, false), 6, itemstack.getItemDamage());
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
