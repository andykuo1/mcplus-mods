package net.minecraftplus.turtle;

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

		MCP.RENDERS().add(EntityTurtle.class, new RenderTurtle(new ModelTurtle(), new ModelTurtleCollar(), 0.4F));
	}
}
