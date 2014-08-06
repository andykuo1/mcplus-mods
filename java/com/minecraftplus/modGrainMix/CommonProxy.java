package com.minecraftplus.modGrainMix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

public class CommonProxy implements ICommonProxy
{
	@Override
	public void register(Registry.RenderMode par1Registry)
	{

	}

	@Override
	public void register(Registry.CustomEntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.EntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
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
					par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {seed1, seed2, seed3, Items.bowl});
				}
			}
		}
	}
}
