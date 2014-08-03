package com.minecraftplus.modHandDigging;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.item.ItemBase;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_HandDigging.MODBASE, name = "MC+ " + MCP_HandDigging.MODBASE, version = "1.0.0")
public class MCP_HandDigging extends MCP
{
	protected static final String MODBASE = "HandDigging";

	@Instance("MCP_" + MCP_HandDigging.MODBASE)
	public static MCP_HandDigging INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Item rock = new ItemBase(CreativeTabs.tabMaterials).setUnlocalizedName("rock");
	public static final Item dirtBall = new ItemBase(CreativeTabs.tabMaterials).setUnlocalizedName("dirt_ball");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.2");

		ItemRegistry.add(rock);
		ItemRegistry.add(dirtBall);

		try
		{
			if (Class.forName("com.minecraftplus.modFirePit.MCP_FirePit") != null)
			{
				CommonProxy.FIRE_PIT_RECIPE = true;
				com.minecraftplus.modFirePit.CommonProxy.CUSTOM_RECIPE = true;
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		Registry.addEventHandler(MinecraftForge.EVENT_BUS, new EventHandDiggingHandler());

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