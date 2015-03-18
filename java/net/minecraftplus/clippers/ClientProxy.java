package net.minecraftplus.clippers;

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

		MCP.RENDERS().add(EntityChickenNaked.class, new RenderChickenNaked(new ModelChickenNaked(), 0.3F));
	}
}
