package com.minecraftplus.modSkullCandle;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@SideOnly(Side.CLIENT)
	@Override
	public void register()
	{
		super.register();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkullCandle.class, new RenderSkullCandle());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRedstoneSkullCandle.class, new RenderRedstoneSkullCandle());
	}
}
