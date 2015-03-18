package net.minecraftplus.woodenbucket;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesWoodenBucketCake implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i)
		{
			ItemStack itemstack = par1InventoryCrafting.getStackInSlot(i);

			if (itemstack != null)
			{
				if (i < 3)
				{
					if (!(itemstack.getItem() instanceof ItemWoodenBucketMilk))
					{
						return false;
					}
				}
				else if (i == 4)
				{
					if (itemstack.getItem() != Items.egg)
					{
						return false;
					}
				}
				else if (i == 3 || i == 5)
				{
					if (itemstack.getItem() != Items.sugar)
					{
						return false;
					}
				}
				else if (itemstack.getItem() != Items.wheat)
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		return new ItemStack(Items.cake);
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