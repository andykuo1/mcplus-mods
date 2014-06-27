package com.minecraftplus.modBlowpipe;

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
		par1Registry.addEntity(EntitySeeds.class, "seeds");
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addFuel(MCP_Blowpipe.blowpipe, 100);

		Object[] objs = Item.itemRegistry.getKeys().toArray();
		for(Object obj : objs)
		{
			if (obj instanceof ItemSeeds)
			{
				par1Registry.addFuel((Item) obj, 25);
			}
		}

		par1Registry.addShapedRecipe(new ItemStack(MCP_Blowpipe.blowpipe, 1), new Object[] {
			"#Y", "YX",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), Items.gunpowder,
			Character.valueOf('Y'), Items.paper});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Blowpipe.blowpipe, 1), new Object[] {
			"XY", "Y#",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), Items.gunpowder,
			Character.valueOf('Y'), Items.paper});
	}
}
