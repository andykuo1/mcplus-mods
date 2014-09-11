package com.minecraftplus.modBlowpipe;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addEntity(EntitySeeds.class, "seeds");
		ModRegistry.addFuel(MCP_Blowpipe.blowpipe, 100);

		Object[] objs = Item.itemRegistry.getKeys().toArray();
		for(Object obj : objs)
		{
			if (obj instanceof ItemSeeds)
			{
				ModRegistry.addFuel((Item) obj, 25);
			}
		}

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Blowpipe.blowpipe, 1),
				"#Y", "YX",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.gunpowder,
				Character.valueOf('Y'), Items.paper);

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Blowpipe.blowpipe, 1),
				"XY", "Y#",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.gunpowder,
				Character.valueOf('Y'), Items.paper);
	}
}
