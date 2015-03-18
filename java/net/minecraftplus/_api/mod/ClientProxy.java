package net.minecraftplus._api.mod;

public class ClientProxy extends CommonProxy
{
	@Override
	public void register()
	{
		super.register();

		MCP.RENDERS().initialize();
		MCP.ICONS().initialize();
	}
}
