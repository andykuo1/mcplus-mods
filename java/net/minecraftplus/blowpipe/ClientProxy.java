package net.minecraftplus.blowpipe;

import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@SideOnly(Side.CLIENT)
	@Override
	public void register()
	{
		super.register();

		MCP.RENDERS().add(EntitySeeds.class, new RenderSeeds());
	}
}
