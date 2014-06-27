package com.minecraftplus.modTurtle;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

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
		int eggColor_main = (41 << 16) + (79 << 8) + 46;
		int eggColor_ext = (125 << 16) + (156 << 8) + 37;
		par1Registry.addEntity(EntityTurtle.class, "turtle", eggColor_main, eggColor_ext);
		par1Registry.addEntitySpawn("turtle", 4, 0, 3, EnumCreatureType.creature,
				BiomeGenBase.swampland);
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		
	}
}
