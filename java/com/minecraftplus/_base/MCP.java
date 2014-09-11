package com.minecraftplus._base;

import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.handler.EventDyeRemoveHandler;
import com.minecraftplus._base.registry.FuelRegistry;
import com.minecraftplus._base.registry.PacketRegistry;
import com.minecraftplus._base.registry.WorldGenRegistry;
import com.minecraftplus.modBase.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = MCP.D + MCP.MODBASE, name = MCP.PRE + MCP.MODBASE, version = MCP.VERSION)
public class MCP implements MCPMod
{
	protected static final String MODBASE = "API";

	public static final String MODID = "minecraftplus";
	public static final String VERSION = "1.4";

	public static final String D = "MCP_";
	public static final String PRE = "MC+ ";
	public static final String DEPENDENCY = "required-after:" + MCP.D + MCP.MODBASE;

	public static final String PACKAGE = "com.minecraftplus.";
	public static final String A = MCP.PACKAGE + "mod";
	public static final String B = ".ClientProxy";
	public static final String C = ".CommonProxy";

	@Instance(MCP.D + MCP.MODBASE)
	public static MCP INSTANCE;

	//@SidedProxy(clientSide = MCP.A + MCP.MODBASE + MCP.B, serverSide = MCP.A + MCP.MODBASE + MCP.C)
	//public static IProxy proxy;

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		PacketRegistry.getPacketHandler().initialize();
		GameRegistry.registerFuelHandler(FuelRegistry.getFuelHandler());
		GameRegistry.registerWorldGenerator(WorldGenRegistry.getWorldGenHandler(), 3);
		MinecraftForge.EVENT_BUS.register(new EventDyeRemoveHandler());

		//proxy.register();
	}

	@EventHandler
	@Override
	public void mainInit(FMLInitializationEvent par1Event)
	{
		PacketRegistry.getPacketHandler().postInitialize();
	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent par1Event)
	{

	}
}