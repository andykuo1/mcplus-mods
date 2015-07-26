package net.minecraftplus.mcp_battle_hearts;

import net.minecraftplus._api.MCS;
import net.minecraftplus._api.base.Proxy;

public class _CommonProxy extends Proxy
{
	@Override
	public void Initialize()
	{
		MCS.command(new CommandMaxHealth());

		super.Initialize();
	}
}