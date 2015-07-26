package net.minecraftplus.mcp_clippers;

import net.minecraftplus._api.MCS;
import net.minecraftplus._api.base.Proxy;

public class _CommonProxy extends Proxy
{
	@Override
	public void Initialize()
	{
		MCS.entity(EntityChickenNaked.class, "naked_chicken");
		
		super.Initialize();
	}
}