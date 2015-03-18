package net.minecraftplus.cabbage;

import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._api.registry.RenderRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	public static final RenderCabbage renderCabbage = new RenderCabbage();

	@SideOnly(Side.CLIENT)
	@Override
	public void register()
	{
		super.register();
		
		MCP.RENDERS().add(renderCabbage);
	}
}
