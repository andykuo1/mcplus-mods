package com.minecraftplus.modTurtle;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ModRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Turtle.MODBASE, name = MCP.PRE + MCP_Turtle.MODBASE, version = "1.2.3", dependencies = MCP.DEPENDENCY)
public class MCP_Turtle implements MCPMod
{
	protected static final String MODBASE = "Turtle";

	@Instance(MCP.D + MCP_Turtle.MODBASE)
	public static MCP_Turtle INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ModRegistry.addGuiHandler(this, new GuiHandler());

		proxy.register();
	}

	@EventHandler
	@Override
	public void mainInit(FMLInitializationEvent par1Event)
	{

	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent par1Event)
	{

	}
}