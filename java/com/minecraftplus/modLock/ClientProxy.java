package com.minecraftplus.modLock;

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
		RenderRegistry.addEntityRender(EntityLock.class, new RenderLock(MCP_Lock.ironLock));
	}
}
