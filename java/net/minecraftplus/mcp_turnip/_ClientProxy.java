package net.minecraftplus.mcp_turnip;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.item(_Turnip.turnip);
		MCC.item(_Turnip.turnipSeeds);
		MCC.item(_Turnip.turnipSoup);

		MCC.block(_Turnip.turnips);

		super.Initialize();
	}
}