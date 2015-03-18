package net.minecraftplus.shatter;

import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.ENTITIES().add(EntityArrowShatter.class, "ArrowShatter");
	}
}
