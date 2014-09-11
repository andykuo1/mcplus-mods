package com.minecraftplus.modWhetstone;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Whetstone.MODBASE, name = MCP.PRE + MCP_Whetstone.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_Whetstone implements MCPMod
{
	protected static final String MODBASE = "Whetstone";

	@Instance("MCP_" + MCP_Whetstone.MODBASE)
	public static MCP_Whetstone INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item whetstone = new ItemWhetstone().setUnlocalizedName("whetstone");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(whetstone);

		MinecraftForge.EVENT_BUS.register(new EventWhetstoneBuffHandler());

		proxy.register();
	}

	@EventHandler
	@Override
	public void mainInit(FMLInitializationEvent par1Event)
	{

	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent par1Event)
	{

	}
}