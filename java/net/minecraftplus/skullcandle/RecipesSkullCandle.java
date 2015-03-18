package net.minecraftplus.skullcandle;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesSkullCandle implements IRecipe
{
	private Block block;
	private Item skull;

	public RecipesSkullCandle(Block par1Block, Item par2Item)
	{
		this.block = par1Block;
		this.skull = par2Item;
	}

	@Override
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		boolean flag = false;
		int j = -1;

		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i)
		{
			if (i == j) continue;
			ItemStack itemstack2 = par1InventoryCrafting.getStackInSlot(i);
			if (flag && itemstack2 != null) return false;
			if (itemstack2 == null) continue;

			if (itemstack2.getItem() == Item.getItemFromBlock(this.block))
			{
				flag = true;
				ItemStack itemstack = itemstack2;
				if (i + (par1InventoryCrafting.getSizeInventory() == 9 ? 3 : 2) < par1InventoryCrafting.getSizeInventory())
				{
					itemstack2 = par1InventoryCrafting.getStackInSlot(i + (par1InventoryCrafting.getSizeInventory() == 9 ? 3 : 2));
					if (itemstack2 != null && itemstack2.getItem() == Items.skull)
					{
						flag = true;
						j = i + (par1InventoryCrafting.getSizeInventory() == 9 ? 3 : 2);
						continue;
					}
				}

				return false;
			}
			else
			{
				return false;
			}
		}

		return flag;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		ItemStack itemstack = null;

		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);
			if (itemstack1 != null && itemstack1.getItem() == Items.skull)
			{
				itemstack = new ItemStack(this.skull, 1, itemstack1.getItemDamage());
				break;
			}
		}

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
		return new ItemStack(this.skull);
	}
}

