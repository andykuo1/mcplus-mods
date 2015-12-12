package net.minecraftplus.mcp_glowing_slime;

import net.minecraftplus._api.MCS;
import net.minecraftplus._api.base.Proxy;

public class _CommonProxy extends Proxy
{
	@Override
	public void Initialize()
	{
		MCS.entity(EntityGlowingSlimeball.class, "GlowingSlimeball", 0, _Glowing_Slime.INSTANCE, 80, 10, true);

		super.Initialize();
	}
}