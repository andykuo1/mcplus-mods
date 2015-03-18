package net.minecraftplus.lumber;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftplus._api.IConfigMod;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModLumber.MODID, version = ModLumber.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModLumber extends MCP implements IConfigMod
{
	public static final String MODID = "lumber";
	public static final String VERSION = "1.1.0";

	@Instance(MCP.MCPID + MODID)
	public static ModLumber INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public ModLumber()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		this.addEventHandler(MinecraftForge.EVENT_BUS, new EventTreeBreakHandler());
		
		if (EventReforestationHandler.active)
		{
			this.addEventHandler(MinecraftForge.EVENT_BUS, new EventReforestationHandler());
		}

		proxy.register();
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		super.PostInitialize(parEvent);
	}

	@Override
	public void Configure(Configuration parConfig)
	{
		Property prop = parConfig.get("GENERAL", "breakLeaves", false);
		Property prop1 = parConfig.get("GENERAL", "reforestation", true);

		EventTreeBreakHandler.breakLeaves = prop.getBoolean();
		EventReforestationHandler.active = prop1.getBoolean();
	}
}
