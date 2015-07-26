package net.minecraftplus.mcp_clay_tools;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.item(_Clay_Tools.swordClay);
		MCC.item(_Clay_Tools.shovelClay);
		MCC.item(_Clay_Tools.axeClay);
		MCC.item(_Clay_Tools.hoeClay);
		MCC.item(_Clay_Tools.pickaxeClay);
		
		super.Initialize();
	}
}