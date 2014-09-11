package com.minecraftplus.modSweetPotato;

import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addSmelting(MCP_SweetPotato.sweetPotato, new ItemStack(MCP_SweetPotato.bakedSweetPotato), 0.35F);
	}
}
