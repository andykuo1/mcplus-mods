package com.minecraftplus.modTurnip;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus.modBeetroot.MCP_Beetroot;

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
		par1Registry.addShapelessRecipe(new ItemStack(MCP_Turnip.turnipSoup), new Object[] {MCP_Turnip.turnip, MCP_Turnip.turnip, MCP_Turnip.turnip, Items.bowl});
	}
}
