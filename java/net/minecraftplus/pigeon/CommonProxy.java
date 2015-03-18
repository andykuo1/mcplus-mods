package net.minecraftplus.pigeon;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.ENTITIES().add(EntityPigeon.class, "Pigeon").setColor(0xE8E8E8, 0x8F5EAE).addSpawn(4, 2, 8, EnumCreatureType.creature,
				BiomeGenBase.plains,
				BiomeGenBase.forest,
				BiomeGenBase.birchForest,
				BiomeGenBase.extremeHills);
	}
}
