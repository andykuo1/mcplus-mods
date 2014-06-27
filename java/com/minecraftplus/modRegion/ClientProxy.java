package com.minecraftplus.modRegion;

import net.minecraftforge.common.MinecraftForge;

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
		
		GuiBiomeName guiBiome = new GuiBiomeName(MCP_Region.x, MCP_Region.y, MCP_Region.scale);
		MinecraftForge.EVENT_BUS.register(guiBiome);
		MinecraftForge.EVENT_BUS.register(new EventBiomeDisplayHandler(guiBiome));
	}
}
