package net.minecraftplus.mcp_turtle;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftplus._api.MCS;
import net.minecraftplus._api.base.Proxy;
import net.minecraftplus._api.util.ColorUtil;

public class _CommonProxy extends Proxy
{
	@Override
	public void Initialize()
	{
		int eggColor_main = ColorUtil.rgbToHex(41, 79, 46);
		int eggColor_ext = ColorUtil.rgbToHex(125, 156, 37);
		MCS.entity(EntityTurtle.class, "Turtle", 0, _Turtle.INSTANCE, 80, 3, true, eggColor_main, eggColor_ext);
		EntityRegistry.addSpawn(EntityTurtle.class, 4, 0, 3, EnumCreatureType.CREATURE,
				BiomeGenBase.swampland);

		super.Initialize();
	}
}