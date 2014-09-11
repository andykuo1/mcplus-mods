package com.minecraftplus.modRegion;

import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ModRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	public static GuiBiomeName guiBiome;

	@SideOnly(Side.CLIENT)
	@Override
	public void register()
	{
		super.register();
		guiBiome = new GuiBiomeName(MCP_Region.x, MCP_Region.y, MCP_Region.scale);
		MinecraftForge.EVENT_BUS.register(guiBiome);
		MinecraftForge.EVENT_BUS.register(new EventBiomeDisplayHandler());
	}
}
