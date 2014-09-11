package com.minecraftplus._base.registry;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import com.minecraftplus._client.RenderBlock;
import com.minecraftplus._client.RenderBlockHandler;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRegistry
{
	public static RenderBlockHandler renderBlockHandler = new RenderBlockHandler();

	public static boolean addBlockRender(RenderBlock par1RenderBlock)
	{
		return renderBlockHandler.add(par1RenderBlock);
	}

	public static void addEntityRender(Class<? extends Entity> par1Class, Render par2Render)
	{
		RenderingRegistry.registerEntityRenderingHandler(par1Class, par2Render);
	}
}
