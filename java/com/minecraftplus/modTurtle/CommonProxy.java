package com.minecraftplus.modTurtle;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		int eggColor_main = (41 << 16) + (79 << 8) + 46;
		int eggColor_ext = (125 << 16) + (156 << 8) + 37;
		ModRegistry.addEntity(EntityTurtle.class, "turtle", eggColor_main, eggColor_ext);
		ModRegistry.addEntitySpawn("turtle", 4, 0, 3, EnumCreatureType.creature,
				BiomeGenBase.swampland);
	}
}
