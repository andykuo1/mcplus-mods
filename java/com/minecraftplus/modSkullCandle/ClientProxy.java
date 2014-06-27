package com.minecraftplus.modSkullCandle;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.client.registry.ClientRegistry;
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

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkullCandle.class, new RenderSkullCandle());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRedstoneSkullCandle.class, new RenderRedstoneSkullCandle());
	}
}
