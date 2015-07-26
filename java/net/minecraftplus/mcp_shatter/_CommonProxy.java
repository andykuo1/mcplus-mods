package net.minecraftplus.mcp_shatter;

import net.minecraftplus._api.MCS;
import net.minecraftplus._api.base.Proxy;

public class _CommonProxy extends Proxy
{
	@Override
	public void Initialize()
	{
		MCS.entity(EntityArrowShatter.class, "arrow_shatter");
		
		super.Initialize();
	}
}