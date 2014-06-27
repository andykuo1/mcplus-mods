package com.minecraftplus._base.handler;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler
{
	private HashMap<Item, Integer> burnMap = new HashMap<Item, Integer>();
	private HashMap<Class<? extends Item>, Integer> burnInstanceMap = new HashMap<Class<? extends Item>, Integer>();

	@Override
	public int getBurnTime(ItemStack par1ItemStack)
	{
		for (Map.Entry<Item, Integer> entry : this.burnMap.entrySet())
		{
			if (par1ItemStack.getItem() == entry.getKey())
			{
				return entry.getValue();
			}
		}

		for (Map.Entry<Class<? extends Item>, Integer> entry : this.burnInstanceMap.entrySet())
		{
			if (entry.getKey().isAssignableFrom(par1ItemStack.getItem().getClass()))
			{
				return entry.getValue();
			}
		}

		return 0;
	}

	public void add(Item par1Item, Integer par2BurnTime)
	{
		this.burnMap.put(par1Item, par2BurnTime);
	}

	public void remove(Item par1Item)
	{
		this.burnMap.remove(par1Item);
	}

	public void add(Class<? extends Item> par1Class, Integer par2BurnTime)
	{
		this.burnInstanceMap.put(par1Class, par2BurnTime);
	}

	public void remove(Class<? extends Item> par1Class)
	{
		this.burnInstanceMap.remove(par1Class);
	}
}
