package com.minecraftplus.modReforestation;

import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Reforestation.MODBASE, name = MCP.PRE + MCP_Reforestation.MODBASE, version = "1.0.2", dependencies = MCP.DEPENDENCY)
public class MCP_Reforestation implements MCPMod
{
	protected static final String MODBASE = "Reforestation";

	@Instance(MCP.D + MCP_Reforestation.MODBASE)
	public static MCP_Reforestation INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MinecraftForge.EVENT_BUS.register(new EventReforestationHandler());

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