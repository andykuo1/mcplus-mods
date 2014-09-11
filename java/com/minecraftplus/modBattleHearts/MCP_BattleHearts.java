package com.minecraftplus.modBattleHearts;

import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ModRegistry;
import com.minecraftplus._base.registry.PacketRegistry;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_BattleHearts.MODBASE, name = MCP.PRE + MCP_BattleHearts.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_BattleHearts implements MCPMod
{
	protected static final String MODBASE = "BattleHearts";

	@Instance(MCP.D + MCP_BattleHearts.MODBASE)
	public static MCP_BattleHearts INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.B + MODBASE + MCP.C)
	public static CommonProxy proxy;

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		PacketRegistry.add(PacketMaxHealth.class);

		Object obj = new EventMaxHealthHandler();
		ModRegistry.addEventHandler(MinecraftForge.EVENT_BUS, obj);
		ModRegistry.addEventHandler(FMLCommonHandler.instance().bus(), obj);

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