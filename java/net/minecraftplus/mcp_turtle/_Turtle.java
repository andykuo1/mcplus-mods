package net.minecraftplus.mcp_turtle;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.MCP;

@Mod(modid = _Turtle.MODID, version = _Turtle.VERSION, dependencies = "required-after:mcp_api")
public class _Turtle extends _Mod
{
	public static final String MODID = "mcp_turtle";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Turtle INSTANCE;

	public _Turtle() {}

	//TODO: Add registers here for MCP_TURTLE

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		//TODO: Add Items, Blocks, EventHandlers, Localizations for MCP_TURTLE
		
		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		//TODO: Add Recipes for MCP_TURTLE
		
		proxy.Initialize();
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		//TODO: Add Communications for MCP_TURTLE
		
		super.PostInitialize(parEvent);
	}


	@Override
	public void Configure(Configuration parConfiguration)
	{
		//TODO: Add config for MCP_TURTLE
		
		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		//TODO: Add factory functions for MCP_TURTLE
		
		super.Munge();
	}
}