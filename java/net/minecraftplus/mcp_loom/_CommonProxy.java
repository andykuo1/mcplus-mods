package net.minecraftplus.mcp_loom;

import net.minecraftplus._api.MCS;
import net.minecraftplus._api.base.Proxy;

public class _CommonProxy extends Proxy
{
	@Override
	public void Initialize()
	{
		MCS.tileEntity(TileEntityLoom.class, "loom");

		super.Initialize();
	}
}