package com.minecraftplus.modPigeon;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

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
		par1Registry.addEntity(EntityPigeon.class, "pigeon", 0xE8E8E8, 0x8F5EAE);
		par1Registry.addEntitySpawn("pigeon", 6, 0, 8, EnumCreatureType.creature,
				BiomeGenBase.plains,
				BiomeGenBase.forest,
				BiomeGenBase.birchForest,
				BiomeGenBase.extremeHills);
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{

	}
}
