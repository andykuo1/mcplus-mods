package net.minecraftplus.mcp_lumber;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.base._Mod;

@Mod(modid = _Lumber.MODID, version = _Lumber.VERSION, dependencies = "required-after:mcp_api")
public class _Lumber extends _Mod
{
	public static final String MODID = "mcp_lumber";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Lumber INSTANCE;

	public _Lumber() {}
	
	public static final EventHandlerLumber eventHandler = new EventHandlerLumber();

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.eventHandler(eventHandler);
		
		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		proxy.Initialize();
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		super.PostInitialize(parEvent);
	}


	@Override
	public void Configure(Configuration parConfiguration)
	{
		Property propBreakLeaves = parConfiguration.get("GENERAL", "breakLeaves", false);

		EventHandlerLumber.BREAK_LEAVES = propBreakLeaves.getBoolean();

		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		super.Munge();
	}
}