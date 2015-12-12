package net.minecraftplus.mcp_ice_box;

import net.minecraftplus._api.MCS;
import net.minecraftplus._api.base.Proxy;

public class _CommonProxy extends Proxy
{
	@Override
	public void Initialize()
	{
		MCS.tileEntity(TileEntityIceBox.class, "ice_box");

		super.Initialize();
	}
}