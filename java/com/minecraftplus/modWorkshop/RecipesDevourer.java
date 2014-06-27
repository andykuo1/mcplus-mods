package com.minecraftplus.modWorkshop;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesDevourer implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting par1, World par2World)
	{
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		return null;
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
