package net.minecraftplus._api.minecraft.base;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class RecipesShaped implements IRecipe
{
	//Compare To: @ShapedRecipes
	/** How many horizontal slots this recipe is wide. */
	public final int recipeWidth;
	/** How many vertical slots this recipe uses. */
	public final int recipeHeight;
	/** Is a array of chars that composes the recipe. */
	public final char[] recipeItems;
	/** Is the ItemStack that you get when craft the recipe. */
	private final ItemStack recipeOutput;
	/** Is there a mirror recipe?*/
	private final boolean mirror;

	public RecipesShaped(int width, int height, boolean mirror, ItemStack output, char... inputs)
	{
		//Compare To: ShapedRecipes
		this.recipeWidth = width;
		this.recipeHeight = height;
		this.mirror = mirror;
		this.recipeItems = inputs;
		this.recipeOutput = output;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		//Compare To: ShapedRecipes
		return this.recipeOutput;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting p_179532_1_)
	{
		//Compare To: ShapedRecipes
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
		//Compare To: ShapedRecipes
		for (int i = 0; i <= 3 - this.recipeWidth; ++i)
		{
			for (int j = 0; j <= 3 - this.recipeHeight; ++j)
			{
				if (this.mirror && this.checkMatch(p_77569_1_, i, j, true))
				{
					return true;
				}

				if (this.checkMatch(p_77569_1_, i, j, false))
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the region of a crafting inventory is match for the recipe.
	 */
	private boolean checkMatch(InventoryCrafting p_77573_1_, int p_77573_2_, int p_77573_3_, boolean p_77573_4_)
	{
		//Compare To: @ShapedRecipes
		for (int k = 0; k < 3; ++k)
		{
			for (int l = 0; l < 3; ++l)
			{
				int i1 = k - p_77573_2_;
				int j1 = l - p_77573_3_;
				char itemtype = ' ';

				if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight)
				{
					if (p_77573_4_)
					{
						itemtype = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
					}
					else
					{
						itemtype = this.recipeItems[i1 + j1 * this.recipeWidth];
					}
				}

				ItemStack itemstack1 = p_77573_1_.getStackInRowAndColumn(k, l);

				if (!this.checkItems(itemtype, itemstack1))
				{
					return false;
				}
			}
		}

		return true;
	}

	public abstract boolean checkItems(char itemtype, ItemStack itemstack1);

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		ItemStack itemstack = this.getRecipeOutput().copy();

		for (int i = 0; i < p_77572_1_.getSizeInventory(); ++i)
		{
			ItemStack itemstack1 = p_77572_1_.getStackInSlot(i);

			if (itemstack1 != null && itemstack1.hasTagCompound())
			{
				itemstack.setTagCompound((NBTTagCompound)itemstack1.getTagCompound().copy());
			}
		}

		return itemstack;
	}

	@Override
	public int getRecipeSize()
	{
		return this.recipeWidth * this.recipeHeight;
	}
}
