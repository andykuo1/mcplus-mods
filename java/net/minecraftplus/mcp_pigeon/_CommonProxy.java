package net.minecraftplus.mcp_pigeon;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftplus._api.MCS;
import net.minecraftplus._api.base.Proxy;

public class _CommonProxy extends Proxy
{
	@Override
	public void Initialize()
	{
		MCS.entity(EntityPigeon.class, "Pigeon", 0xE8E8E8, 0x8F5EAE);
		EntityRegistry.addSpawn(EntityPigeon.class, 4, 2, 8, EnumCreatureType.CREATURE,
				BiomeGenBase.plains,
				BiomeGenBase.forest,
				BiomeGenBase.birchForest,
				BiomeGenBase.extremeHills);

		super.Initialize();
	}
}