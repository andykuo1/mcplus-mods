package net.minecraftplus.mcp_rotten;

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

@Mod(modid = _Rotten.MODID, version = _Rotten.VERSION, dependencies = "required-after:mcp_api")
public class _Rotten extends _Mod
{
	public static final String MODID = "mcp_rotten";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Rotten INSTANCE;

	public _Rotten() {}

	//TODO: Dependent on doDaylightCycle and WorldTime
	
	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.lang("state.food.new", "Fresh");
		MCP.lang("state.food.old", "Stale");
		MCP.lang("state.food.rotten", "Rotten");
		MCP.lang("state.food.rotting", "Rotting");
		MCP.lang("string.days_left", "Days left");

		MCP.eventHandler(new EventHandlerRotten());

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
		Property propCountdown = parConfiguration.get("GENERAL", "enableCountdownTooltip", false);
		Property propNewDays = parConfiguration.get("GENERAL", "newDays", 2);
		Property propOldDays = parConfiguration.get("GENERAL", "oldDays", 3);

		EventHandlerRotten.NEW_DAYS = propNewDays.getInt();
		EventHandlerRotten.OLD_DAYS = propOldDays.getInt();
		EventHandlerRotten.ENABLE_COUNTDOWN = propCountdown.getBoolean();

		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		super.Munge();
	}
}