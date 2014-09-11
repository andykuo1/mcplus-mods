package com.minecraftplus.modSoulExtractor;

import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ModRegistry;

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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulExtractor.class, new RenderSoulExtractor());
	}
}
