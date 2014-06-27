package com.minecraftplus.modRegion;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_Region.MODBASE, name = "MC+ " + MCP_Region.MODBASE, version = "1.1.1")
public class MCP_Region extends MCP
{
	protected static final String MODBASE = "Region";

	@Instance("MCP_" + MCP_Region.MODBASE)
	public static MCP_Region INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .
	public static int x, y, scale;

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.0");

		Configuration config = new Configuration(par1Event.getSuggestedConfigurationFile());
		config.load();
		Property prop3 = config.get("Position", "scaleFactor", 55);
		prop3.comment = "Percent Scale (Does not exceed 100%)";
		Property prop2 = config.get("Position", "posX", 0);
		prop2.comment = "1 = Right\n0 = Center\n-1 = Left";
		Property prop1 = config.get("Position", "posY", 1);
		prop1.comment = "1 = Top\n0 = Center\n-1 = Bottom";
		config.save();

		x = prop2.getInt();
		y = prop1.getInt();
		scale = prop3.getInt();

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