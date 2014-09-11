package com.minecraftplus.modTurnip;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapelessRecipe(new ItemStack(MCP_Turnip.turnipSoup), new Object[] {MCP_Turnip.turnip, MCP_Turnip.turnip, MCP_Turnip.turnip, Items.bowl});
	}
}
