package com.minecraftplus._base.registry;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class FuelRegistry
{
	private static class FuelHandler implements IFuelHandler
	{
		private HashMap<Item, Integer> burnMap = new HashMap<Item, Integer>();

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
	}

	private static FuelHandler handler = new FuelHandler();

	public static void add(Item par1Item, Integer par2BurnTime)
	{
		handler.add(par1Item, par2BurnTime);
	}

	public static void remove(Item par1Item)
	{
		handler.remove(par1Item);
	}

	public static IFuelHandler getFuelHandler()
	{
		return handler;
	}
}
