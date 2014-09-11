package com.minecraftplus.modMortar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

import com.minecraftplus.modSulfur.MCP_Sulfur;

public class MortarRecipes
{
	private static final MortarRecipes smeltingBase = new MortarRecipes();

	/** The list of smelting results. */
	private Map smeltingList = new HashMap<List<Item>, ItemStack>();
	private Map experienceList = new HashMap<Item, Float>();

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */
	public static final MortarRecipes smelting()
	{
		return smeltingBase;
	}

	private MortarRecipes()
	{
		/**FOOD RECIPES**/
		ArrayList<ItemFood> foodlist = new ArrayList<ItemFood>();
		Iterator<Item> iter = Item.itemRegistry.iterator();
		while(iter.hasNext())
		{
			Item item = iter.next();
			if (item instanceof ItemFood)
			{
				foodlist.add((ItemFood) item);
			}
		}

		for(ItemFood food1 : foodlist)
		{
			for(ItemFood food2 : foodlist)
			{
				int hunger = (int) Math.ceil((food1.func_150905_g(new ItemStack(food1)) + food2.func_150905_g(new ItemStack(food2))) / 2F);
				float sat = (food1.func_150906_h(new ItemStack(food1)) + food2.func_150906_h(new ItemStack(food2)));
				this.addSmelting(Arrays.asList(MCP_Sulfur.sulfur, food1, food2),  new ItemStack(MCP_Mortar.rottenCompost, hunger), sat);
			}
		}

		/**INGREDIANT RECIPES**/


		/**MATERIAL RECIPES**/
		this.addSmelting(Item.getItemFromBlock(Blocks.gravel), new ItemStack(Items.flint), 0.2F);
		this.addSmelting(Items.bone, new ItemStack(Items.dye, 9, 15),  0.2F);
		this.addSmelting(Arrays.asList(MCP_Sulfur.sulfur, Items.coal, Items.coal), new ItemStack(Items.gunpowder, 4), 0.2F);
	}

	/**
	 * Adds a smelting recipe.
	 */
	public void addSmelting(List<Item> par1, ItemStack par2ItemStack, float par3)
	{
		this.smeltingList.put(par1, par2ItemStack);
		this.experienceList.put(par2ItemStack.getItem(), Float.valueOf(par3));
	}

	/**
	 * Adds a smelting recipe of same type.
	 */
	public void addSmelting(Item par1Item, ItemStack par2ItemStack, float par3)
	{
		this.smeltingList.put(Arrays.asList(par1Item, par1Item, par1Item), par2ItemStack);
		this.experienceList.put(par2ItemStack.getItem(), Float.valueOf(par3));
	}

	/**
	 * Used to get the resulting ItemStack form a source ItemStack
	 * @param item The Source ItemStack
	 * @return The result ItemStack
	 */
	public ItemStack getSmeltingResult(ItemStack[] par1ItemStacks) 
	{
		if (par1ItemStacks == null)
		{
			return null;
		}

		List<Item> list = null;
		for(int k = 0; k < par1ItemStacks.length; k++)
		{
			if (par1ItemStacks[k] == null)
			{
				return null;
			}
		}

		list = Arrays.asList(par1ItemStacks[0].getItem(), par1ItemStacks[1].getItem(), par1ItemStacks[2].getItem());
		return (ItemStack) smeltingList.get(list);
	}

	/**
	 * Grabs the amount of base experience for this item to give when pulled from the furnace slot.
	 */
	public float getExperience(ItemStack item)
	{
		if (item == null || item.getItem() == null)
		{
			return 0;
		}

		float ret = item.getItem().getSmeltingExperience(item);
		if (ret < 0 && experienceList.containsKey(item))
		{
			ret = ((Float)experienceList.get(item)).floatValue();
		}

		return (ret < 0 ? 0 : ret);
	}

	public Map getSmeltingList()
	{
		return this.smeltingList;
	}
}