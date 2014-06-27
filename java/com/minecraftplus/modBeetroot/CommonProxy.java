package com.minecraftplus.modBeetroot;

import net.minecraft.init.Items;
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
		par1Registry.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new Object[] {MCP_Beetroot.beetroot});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_Beetroot.beetrootSoup), new Object[] {MCP_Beetroot.beetroot, MCP_Beetroot.beetroot, MCP_Beetroot.beetroot, MCP_Beetroot.beetroot, Items.bowl});
	}
}
