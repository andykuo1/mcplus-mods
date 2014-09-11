package com.minecraftplus.modSurvivor;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		try
		{
			if (Class.forName(MCP.A + "FirePit.MCP_FirePit") == null)
			{
				ModRegistry.addShapelessRecipe(new ItemStack(Items.stick, 2), Blocks.sapling);
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
