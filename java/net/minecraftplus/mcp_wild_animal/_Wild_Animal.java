package net.minecraftplus.mcp_wild_animal;

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

@Mod(modid = _Wild_Animal.MODID, version = _Wild_Animal.VERSION, dependencies = "required-after:mcp_api")
public class _Wild_Animal extends _Mod
{
	public static final String MODID = "mcp_wild_animal";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Wild_Animal INSTANCE;

	public _Wild_Animal() {}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{

		MCP.eventHandler(new EventHandlerWildAnimal());

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
		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		super.Munge();
	}
}