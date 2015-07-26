package net.minecraftplus._api.dictionary;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftplus._api.dictionary.entry.RecipeDyeable;
import net.minecraftplus._api.dictionary.entry.RecipeToolDyeable;
import net.minecraftplus._api.minecraft.base.ItemDyeable;
import net.minecraftplus._api.minecraft.base.ItemToolDyeable;
import net.minecraftplus._api.util.ArrayUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class Recipes
{
	private Recipes() {}

	public static final IRecipe SWORD(ItemStack parResult, Object parItem, Object parStick)
	{
		return getShapedRecipe(parResult,
				"#",
				"#",
				"X",
				Character.valueOf('#'), parItem,
				Character.valueOf('X'), parStick);
	}

	public static final IRecipe SHOVEL(ItemStack parResult, Object parItem, Object parStick)
	{
		return getShapedRecipe(parResult,
				"#",
				"X",
				"X",
				Character.valueOf('#'), parItem,
				Character.valueOf('X'), parStick);
	}

	public static final IRecipe AXE(ItemStack parResult, Object parItem, Object parStick)
	{
		return getShapedRecipe(parResult,
				"##",
				"#X",
				" X",
				Character.valueOf('#'), parItem,
				Character.valueOf('X'), parStick);
	}

	public static final IRecipe HOE(ItemStack parResult, Object parItem, Object parStick)
	{
		return getShapedRecipe(parResult,
				"##",
				" X",
				" X",
				Character.valueOf('#'), parItem,
				Character.valueOf('X'), parStick);
	}

	public static final IRecipe PICKAXE(ItemStack parResult, Object parItem, Object parStick)
	{
		return getShapedRecipe(parResult,
				"###",
				" X ",
				" X ",
				Character.valueOf('#'), parItem,
				Character.valueOf('X'), parStick);
	}

	public static final IRecipe HELMET(ItemStack parResult, Object parItem)
	{
		return getShapedRecipe(parResult,
				"###",
				"# #",
				Character.valueOf('#'), parItem);
	}

	public static final IRecipe CHESTPLATE(ItemStack parResult, Object parItem)
	{
		return getShapedRecipe(parResult,
				"# #",
				"###",
				"###",
				Character.valueOf('#'), parItem);
	}

	public static final IRecipe LEGGINGS(ItemStack parResult, Object parItem)
	{
		return getShapedRecipe(parResult,
				"###",
				"# #",
				"# #",
				Character.valueOf('#'), parItem);
	}

	public static final IRecipe BOOTS(ItemStack parResult, Object parItem)
	{
		return getShapedRecipe(parResult,
				"# #",
				"# #",
				Character.valueOf('#'), parItem);
	}

	public static final IRecipe DYEABLE(Item parItem)
	{
		assert(parItem instanceof ItemDyeable);

		return new RecipeDyeable(parItem);
	}

	public static final IRecipe DYEABLE(ItemTool parItem)
	{
		assert(parItem instanceof ItemToolDyeable);

		return new RecipeToolDyeable(parItem);
	}

	public static final IRecipe CONVERT(ItemStack parResult, Object parItem)
	{
		assert(parItem instanceof Item || parItem instanceof Block || parItem instanceof ItemStack);

		return getShapedRecipe(parResult,
				"#",
				Character.valueOf('#'), parItem);
	}

	public static final IRecipe COMPRESSED(ItemStack parResult, Object parItem)
	{
		assert(parItem instanceof Item || parItem instanceof Block || parItem instanceof ItemStack);

		return getShapedRecipe(parResult,
				"###",
				"###",
				"###",
				Character.valueOf('#'), parItem);
	}

	public static final IRecipe SHAPELESS(ItemStack parResult, Object... parItems)
	{
		assert(ArrayUtil.containsInstanceOf(parItems, Item.class) || ArrayUtil.containsInstanceOf(parItems, Block.class) || ArrayUtil.containsInstanceOf(parItems, ItemStack.class));

		return getShapelessRecipe(parResult,
				parItems);
	}

	protected static final ShapedRecipes getShapedRecipe(ItemStack stack, Object ... recipeComponents)
	{
		//Compare to: @CraftingManager
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;

		if (recipeComponents[i] instanceof String[])
		{
			String[] astring = (String[])((String[])recipeComponents[i++]);

			for (int l = 0; l < astring.length; ++l)
			{
				String s1 = astring[l];
				++k;
				j = s1.length();
				s = s + s1;
			}
		}
		else
		{
			while (recipeComponents[i] instanceof String)
			{
				String s2 = (String)recipeComponents[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}

		HashMap hashmap;

		for (hashmap = Maps.newHashMap(); i < recipeComponents.length; i += 2)
		{
			Character character = (Character)recipeComponents[i];
			ItemStack itemstack1 = null;

			if (recipeComponents[i + 1] instanceof Item)
			{
				itemstack1 = new ItemStack((Item)recipeComponents[i + 1]);
			}
			else if (recipeComponents[i + 1] instanceof Block)
			{
				itemstack1 = new ItemStack((Block)recipeComponents[i + 1], 1, 32767);
			}
			else if (recipeComponents[i + 1] instanceof ItemStack)
			{
				itemstack1 = (ItemStack)recipeComponents[i + 1];
			}

			hashmap.put(character, itemstack1);
		}

		ItemStack[] aitemstack = new ItemStack[j * k];

		for (int i1 = 0; i1 < j * k; ++i1)
		{
			char c0 = s.charAt(i1);

			if (hashmap.containsKey(Character.valueOf(c0)))
			{
				aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).copy();
			}
			else
			{
				aitemstack[i1] = null;
			}
		}

		ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, stack);
		return shapedrecipes;
	}

	protected static final IRecipe getShapelessRecipe(ItemStack stack, Object ... recipeComponents)
	{
		//Compare to: @CraftingManager
		ArrayList arraylist = Lists.newArrayList();
		Object[] aobject = recipeComponents;
		int i = recipeComponents.length;

		for (int j = 0; j < i; ++j)
		{
			Object object1 = aobject[j];

			if (object1 instanceof ItemStack)
			{
				arraylist.add(((ItemStack)object1).copy());
			}
			else if (object1 instanceof Item)
			{
				arraylist.add(new ItemStack((Item)object1));
			}
			else
			{
				if (!(object1 instanceof Block))
				{
					throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object1.getClass().getName() + "!");
				}

				arraylist.add(new ItemStack((Block)object1));
			}
		}

		return new ShapelessRecipes(stack, arraylist);
	}
}
