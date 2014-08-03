package com.minecraftplus.modFoodEssentials;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPClient;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.render.RenderBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	public static final RenderBlock renderCabbagePlant = new RenderBlockCabbagePlant();

	@SideOnly(Side.CLIENT)
	@Override
	public void register(Registry.RenderMode par1Registry)
	{
		MCP.initClient();
		this.register(Registry.CUSTOM_ENTITY);

		MCPClient.registerRenderBlock(renderCabbagePlant);
	}
}
