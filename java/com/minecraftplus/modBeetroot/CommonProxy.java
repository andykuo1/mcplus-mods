package com.minecraftplus.modBeetroot;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), MCP_Beetroot.beetroot);
		ModRegistry.addShapelessRecipe(new ItemStack(MCP_Beetroot.beetrootSoup), MCP_Beetroot.beetroot, MCP_Beetroot.beetroot, MCP_Beetroot.beetroot, MCP_Beetroot.beetroot, Items.bowl);
	}
}
