package com.minecraftplus.modTurtle;

import com.minecraftplus._base.registry.RenderRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@SideOnly(Side.CLIENT)
	@Override
	public void register()
	{
		super.register();
		RenderRegistry.addEntityRender(EntityTurtle.class, new RenderTurtle(new ModelTurtle(), new ModelTurtleCollar(), 0.4F));
	}
}
