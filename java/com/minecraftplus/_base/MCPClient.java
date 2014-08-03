package com.minecraftplus._base;

import com.minecraftplus._base.handler.RenderBlockHandler;
import com.minecraftplus._common.render.RenderBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MCPClient
{
	public static RenderBlockHandler renderBlockHandler = new RenderBlockHandler();

	public static boolean registerRenderBlock(RenderBlock par1RenderBlock)
	{
		return renderBlockHandler.add(par1RenderBlock);
	}
}
