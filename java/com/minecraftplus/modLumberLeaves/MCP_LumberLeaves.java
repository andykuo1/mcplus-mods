package com.minecraftplus.modLumberLeaves;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus.modLumber.EventTreeBreakHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_LumberLeaves.MODBASE, name = "MC+ " + MCP_LumberLeaves.MODBASE, version = "1.0.0", dependencies = "required-after:MCP_Lumber")
public class MCP_LumberLeaves extends MCP
{
	protected static final String MODBASE = "LumberLeaves";

	@Instance("MCP_" + MCP_LumberLeaves.MODBASE)
	public static MCP_LumberLeaves INSTANCE;

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
		EventTreeBreakHandler.breakLeaves = true;
	}
}