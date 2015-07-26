package net.minecraftplus.mcp_beetroot;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.item(_Beetroot.beetroot);
		MCC.item(_Beetroot.beetrootSeeds);
		MCC.item(_Beetroot.beetrootSoup);

		MCC.block(_Beetroot.beetroots);

		super.Initialize();
	}
}