package net.minecraftplus.mcp_quartz;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.item(_Quartz.swordQuartz);
		MCC.item(_Quartz.shovelQuartz);
		MCC.item(_Quartz.axeQuartz);
		MCC.item(_Quartz.hoeQuartz);
		MCC.item(_Quartz.pickaxeQuartz);
		MCC.item(_Quartz.helmetQuartz);
		MCC.item(_Quartz.plateQuartz);
		MCC.item(_Quartz.legsQuartz);
		MCC.item(_Quartz.bootsQuartz);

		super.Initialize();
	}
}