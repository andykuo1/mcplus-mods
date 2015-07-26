package net.minecraftplus.mcp_pigeon;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.base._Mod;

@Mod(modid = _Pigeon.MODID, version = _Pigeon.VERSION, dependencies = "required-after:mcp_api")
public class _Pigeon extends _Mod
{
	public static final String MODID = "mcp_pigeon";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Pigeon INSTANCE;

	public _Pigeon() {}

	//TODO: Add registers here for MCP_PIGEON

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.guiHandler(new GuiHandlerPigeon());

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		//TODO: Add Recipes for MCP_PIGEON

		proxy.Initialize();
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		//TODO: Add Communications for MCP_PIGEON

		super.PostInitialize(parEvent);
	}


	@Override
	public void Configure(Configuration parConfiguration)
	{
		//TODO: Add config for MCP_PIGEON

		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		//TODO: Add factory functions for MCP_PIGEON

		super.Munge();
	}
}