package net.minecraftplus.grainmix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		List<ItemSeeds> seedList = new ArrayList<ItemSeeds>();
		Iterator iter = Item.itemRegistry.getKeys().iterator();
		while(iter.hasNext())
		{
			Object obj = Item.itemRegistry.getObject(iter.next());
			if (obj instanceof ItemSeeds)
			{
				seedList.add((ItemSeeds) obj);
			}
		}

		for(ItemSeeds seed1 : seedList)
		{
			for(ItemSeeds seed2 : seedList)
			{
				for(ItemSeeds seed3 : seedList)
				{
					this.addShapelessRecipe(new ItemStack(ModGrainMix.grainMix), new Object[] {seed1, seed2, seed3, Items.bowl});
				}
			}
		}
	}
}
