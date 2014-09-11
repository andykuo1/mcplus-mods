package com.minecraftplus.modLumberLeaves;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus.modLumber.EventTreeBreakHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_LumberLeaves.MODBASE, name = "MC+ " + MCP_LumberLeaves.MODBASE, version = "1.0.1", dependencies = "required-after:MCP_Lumber")
public class MCP_LumberLeaves implements MCPMod
{
	protected static final String MODBASE = "LumberLeaves";

	@Instance("MCP_" + MCP_LumberLeaves.MODBASE)
	public static MCP_LumberLeaves INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
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
		EventTreeBreakHandler.breakLeaves = true;
	}
}