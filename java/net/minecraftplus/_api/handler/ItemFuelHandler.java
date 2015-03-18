package net.minecraftplus._api.handler;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class ItemFuelHandler implements IFuelHandler
{
	private HashMap<Item, Integer> fuelMap = new HashMap<Item, Integer>();

	@Override
	public int getBurnTime(ItemStack parItemStack)
	{
		for (Entry<Item, Integer> entry : this.fuelMap.entrySet())
		{
			if (parItemStack.getItem() == entry.getKey())
			{
				return entry.getValue();
			}
		}

		return 0;
	}

	public void add(Item parItem, Integer parBurnTime)
	{
		this.fuelMap.put(parItem, parBurnTime);
	}

	public void remove(Item parItem)
	{
		this.fuelMap.remove(parItem);
	}
}