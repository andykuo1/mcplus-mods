package com.minecraftplus.modRabbit;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.registry.EntityRegistry;

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
		int eggColor_main = 0xF7E0FF;
		int eggColor_ext = 0xFFF785;
		par1Registry.addEntity(EntityRabbit.class, "rabbit", eggColor_main, eggColor_ext);
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		
	}
}
