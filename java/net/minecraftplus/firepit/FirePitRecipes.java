package net.minecraftplus.firepit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FirePitRecipes
{
	private static final FirePitRecipes smeltingBase = new FirePitRecipes();
	/** The list of smelting results. */
	private Map smeltingList = new HashMap();
	private Map experienceList = new HashMap();

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */
	public static FirePitRecipes smelting()
	{
		return smeltingBase;
	}

	public void addSmelting(Block par1Block, ItemStack par2ItemStack, float par3)
	{
		this.addSmelting(Item.getItemFromBlock(par1Block), par2ItemStack, par3);
	}

	public void addSmelting(Item par1Item, ItemStack par2ItemStack, float par3)
	{
		this.addSmelting(new ItemStack(par1Item, 1, 32767), par2ItemStack, par3);
	}

	public void addSmelting(ItemStack par1ItemStack, ItemStack par2ItemStack, float par3)
	{
		this.smeltingList.put(par1ItemStack, par2ItemStack);
		this.experienceList.put(par2ItemStack, Float.valueOf(par3));
	}

	public ItemStack getSmeltingResult(ItemStack par1ItemStack)
	{
		Iterator iterator = this.smeltingList.entrySet().iterator();
		Map.Entry entry;
		do
		{
			if (!iterator.hasNext())
			{
				return null;
			}

			entry = (Map.Entry)iterator.next();
		}
		while (!func_151397_a(par1ItemStack, (ItemStack)entry.getKey()));

		return (ItemStack)entry.getValue();
	}

	private boolean func_151397_a(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return (par2ItemStack.getItem() == par1ItemStack.getItem()) && ((par2ItemStack.getItemDamage() == 32767) || (par2ItemStack.getItemDamage() == par1ItemStack.getItemDamage()));
	}

	public Map getSmeltingList()
	{
		return this.smeltingList;
	}

	public float getExperience(ItemStack par1ItemStack)
	{
		float ret = par1ItemStack.getItem().getSmeltingExperience(par1ItemStack);
		if (ret != -1.0F) return ret;

		Iterator iterator = this.experienceList.entrySet().iterator();
		Map.Entry entry;
		do
		{
			if (!iterator.hasNext())
			{
				return 0.0F;
			}

			entry = (Map.Entry)iterator.next();
		}
		while (!func_151397_a(par1ItemStack, (ItemStack)entry.getKey()));

		return ((Float)entry.getValue()).floatValue();
	}
}
