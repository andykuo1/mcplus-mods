package com.minecraftplus.modPigeon;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_Pigeon.MODBASE, name = "MC+ " + MCP_Pigeon.MODBASE, version = "1.0.0")
public class MCP_Pigeon extends MCP
{
	protected static final String MODBASE = "Pigeon";

	@Instance("MCP_" + MCP_Pigeon.MODBASE)
	public static MCP_Pigeon INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.0");

		proxy.register(Registry.RENDER);
		proxy.register(Registry.ENTITY);
		proxy.register(Registry.CUSTOM_ENTITY);
	}

	@EventHandler
	@Override
	public void loadInit(FMLInitializationEvent par1Event)
	{
		MCP.initEvent(par1Event);

		proxy.register(Registry.RECIPE);
	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent par1Event)
	{

	}
}