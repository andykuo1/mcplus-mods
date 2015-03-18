package net.minecraftplus.turtle;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		int eggColor_main = (41 << 16) + (79 << 8) + 46;
		int eggColor_ext = (125 << 16) + (156 << 8) + 37;
		MCP.ENTITIES().add(EntityTurtle.class, "Turtle").setColor(eggColor_main, eggColor_ext).addSpawn(4, 0, 3, EnumCreatureType.creature, BiomeGenBase.swampland);
	}
}
