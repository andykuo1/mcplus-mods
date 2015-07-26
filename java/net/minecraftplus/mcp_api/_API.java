package net.minecraftplus.mcp_api;

import java.util.Iterator;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ServerCommandManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftplus._api.MCF;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.minecraft.CommandHandler;
import net.minecraftplus._api.minecraft.FuelHandler;
import net.minecraftplus._api.minecraft.PacketHandler;

@Mod(modid = _API.MODID, version = _API.VERSION)
public class _API extends _Mod
{
	public static final String MODID = "mcp_api";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _API INSTANCE;

	public _API() {}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.eventHandler(this);

		GameRegistry.registerFuelHandler(FuelHandler.INSTANCE);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		PacketHandler.INSTANCE.getChannel().initialize();

		proxy.Initialize();
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		PacketHandler.INSTANCE.getChannel().postInitialize();

		super.PostInitialize(parEvent);
	}

	@Override
	public void Configure(Configuration parConfiguration)
	{
		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		MCF.makeLanguagePack(MCF.langDirectory(MODID));
		super.Munge();
	}

	@EventHandler
	public void onServerStart(FMLServerStartingEvent parEvent)
	{
		ServerCommandManager manager = CommandHandler.getCommandManager();

		Iterator<CommandBase> iter = CommandHandler.INSTANCE.iterator();
		while(iter.hasNext())
		{
			CommandBase command = iter.next();
			manager.registerCommand(command);
		}

		CommandHandler.lock();
	}
}