package com.minecraftplus.modFoodEssentials;

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
		
		par1Registry.addSmelting(MCP_FoodEssentials.coldBeefStew, new ItemStack(MCP_FoodEssentials.beefStew), 0.5F);

	}
}
