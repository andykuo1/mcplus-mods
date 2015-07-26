package net.minecraftplus._api.minecraft;

import java.util.Map;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftplus._api.util.collection.SmallMap;
import net.minecraftplus._api.util.collection.SmallSet;

public class FuelHandler implements IFuelHandler
{
	public static final FuelHandler INSTANCE = new FuelHandler();

	private FuelHandler() {}

	private final Set<IFuelHandler> listeners = new SmallSet<IFuelHandler>();
	private final Map<FuelItem, Integer> fuelMap = new SmallMap<FuelItem, Integer>();

	public void put(Item parItem, int parBurnTime)
	{
		this.fuelMap.put(new FuelItem(parItem), parBurnTime);
	}

	public void put(Item parItem, int parMetadata, int parBurnTime)
	{
		this.fuelMap.put(new FuelItem(parItem, parMetadata), parBurnTime);
	}

	public void add(IFuelHandler parFuelHandler)
	{
		this.listeners.add(parFuelHandler);
	}

	@Override
	public int getBurnTime(ItemStack parItemStack)
	{
		int burnTime = this.fuelMap.get(new FuelItem(parItemStack.getItem(), parItemStack.getMetadata()));
		if (burnTime > 0) return burnTime;

		for(IFuelHandler listener : this.listeners)
		{
			burnTime = Math.max(burnTime, listener.getBurnTime(parItemStack));
		}

		return burnTime;
	}

	private final class FuelItem
	{
		public final Item item;
		public final int metadata;

		public FuelItem(Item parItem)
		{
			this(parItem, -1);
		}

		public FuelItem(Item parItem, int parMetadata)
		{
			this.item = parItem;
			this.metadata = parMetadata;
		}

		@Override
		public boolean equals(Object parObject)
		{
			if (parObject instanceof FuelItem)
			{
				return this.item == ((FuelItem) parObject).item &&
						(this.metadata != -1 ? this.metadata == ((FuelItem) parObject).metadata : true); 
			}
			else if (parObject instanceof Item)
			{
				return this.item == ((Item) parObject) && this.metadata == -1;
			}
			else if (parObject instanceof ItemStack)
			{
				return this.item == ((ItemStack) parObject).getItem() &&
						(this.metadata != -1 ? this.metadata == ((ItemStack) parObject).getMetadata() : true);
			}

			return super.equals(parObject);
		}
	}
}
