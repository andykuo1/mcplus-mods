package com.minecraftplus.modPigeon;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addEntity(EntityPigeon.class, "pigeon", 0xE8E8E8, 0x8F5EAE);
		ModRegistry.addEntitySpawn("pigeon", 6, 0, 8, EnumCreatureType.creature,
				BiomeGenBase.plains,
				BiomeGenBase.forest,
				BiomeGenBase.birchForest,
				BiomeGenBase.extremeHills);
	}
}
