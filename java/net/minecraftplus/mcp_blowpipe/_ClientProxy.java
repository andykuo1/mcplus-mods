package net.minecraftplus.mcp_blowpipe;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.MCC;

@SideOnly(Side.CLIENT)
public class _ClientProxy extends _CommonProxy
{
	@Override
	public void Initialize()
	{
		MCC.item(_Blowpipe.blowpipe);
		MCC.item(_Blowpipe.grainMix);

		MCC.entity(EntitySeeds.class, new RenderSeeds(ItemBlowpipe.PROJECTILES[0]));

		super.Initialize();
	}
}