package com.minecraftplus.modRabbit;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@SideOnly(Side.CLIENT)
	@Override
	public void register(Registry.RenderMode par1Registry)
	{
		MCP.initClient();
		this.register(Registry.CUSTOM_ENTITY);

		par1Registry.addEntityRender(EntityRabbit.class, new RenderRabbit (new ModelRabbit(), 0.4F));
	}
}
