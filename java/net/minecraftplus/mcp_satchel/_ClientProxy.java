package net.minecraftplus.mcp_satchel;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.item(_Satchel.satchel);
		MCC.item(_Satchel.enderSatchel);

		super.Initialize();
	}
}