package net.minecraftplus.mcp_sweet_potato;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.item(_Sweet_Potato.sweetPotato);
		MCC.item(_Sweet_Potato.bakedSweetPotato);

		MCC.block(_Sweet_Potato.sweetPotatoes);

		super.Initialize();
	}
}