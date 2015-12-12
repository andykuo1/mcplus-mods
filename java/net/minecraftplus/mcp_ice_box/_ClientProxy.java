package net.minecraftplus.mcp_ice_box;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.block(_Ice_Box.iceBox);

		super.Initialize();
	}
}