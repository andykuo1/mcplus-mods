package net.minecraftplus._api.minecraft.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.google.common.collect.Lists;

public abstract class RecipesShapeless implements IRecipe
{
	//Compare To: @ShapelessRecipe
	/** Is the ItemStack that you get when craft the recipe. */
	private final ItemStack recipeOutput;
	/** Is a List of chars that composes the recipe. */
	public final List recipeItems;

	public RecipesShapeless(ItemStack output, int size)
	{
		//Compare To: @ShapelessRecipe
		this.recipeOutput = output;
		this.recipeItems = new ArrayList<Character>();
		char c = 'A';
		for(; size > 0; --size)
		{
			this.recipeItems.add(c++);
		}
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		//Compare To: @ShapelessRecipe
		return this.recipeOutput;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting p_179532_1_)
	{
		//Compare To: @ShapelessRecipe
		ItemStack[] aitemstack = new ItemStack[p_179532_1_.getSizeInventory()];

		for (int i = 0; i < aitemstack.length; ++i)
		{
			ItemStack itemstack = p_179532_1_.getStackInSlot(i);
			aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
		}

		return aitemstack;
	}

	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World worldIn)
	{
		//Compare To: @ShapelessRecipe
		ArrayList arraylist = Lists.newArrayList(this.recipeItems);

		for (int i = 0; i < p_77569_1_.getHeight(); ++i)
		{
			for (int j = 0; j < p_77569_1_.getWidth(); ++j)
			{
				ItemStack itemstack = p_77569_1_.getStackInRowAndColumn(j, i);

				if (itemstack != null)
				{
					boolean flag = false;
					Iterator iterator = arraylist.iterator();

					while (iterator.hasNext())
					{
						char itemtype = (Character) iterator.next();

						if (this.checkItems(itemtype, itemstack))
						{
							flag = true;
							arraylist.remove((Character) itemtype);
							break;
						}
					}

					if (!flag)
					{
						return false;
					}
				}
			}
		}

		return arraylist.isEmpty();
	}

	public abstract boolean checkItems(char itemtype, ItemStack itemstack);

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		//Compare To: @ShapelessRecipe
		return this.recipeOutput.copy();
	}

	@Override
	public int getRecipeSize()
	{
		//Compare To: @ShapelessRecipe
		return this.recipeItems.size();
	}
}