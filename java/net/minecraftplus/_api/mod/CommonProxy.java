package net.minecraftplus._api.mod;

import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.TILES().initialize();

		MCP.FUELS().initialize();
		MCP.SMELTS().initialize();
	}
}
