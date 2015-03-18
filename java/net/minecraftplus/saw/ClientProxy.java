package net.minecraftplus.saw;

import net.minecraftplus._api.handler.RenderSimpleBlockHandler;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	public static final RenderSimpleBlockHandler renderBlockSaw = new RenderSaw();

	@SideOnly(Side.CLIENT)
	@Override
	public void register()
	{
		super.register();

		MCP.RENDERS().add(renderBlockSaw);
	}
}
