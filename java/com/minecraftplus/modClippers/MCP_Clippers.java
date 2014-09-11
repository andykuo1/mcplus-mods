package com.minecraftplus.modClippers;

import net.minecraft.item.Item;

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

@Mod(modid = MCP.D + MCP_Clippers.MODBASE, name = MCP.PRE + MCP_Clippers.MODBASE, version = "1.1.2", dependencies = MCP.DEPENDENCY)
public class MCP_Clippers implements MCPMod
{
	protected static final String MODBASE = "Clippers";

	@Instance(MCP.D + MCP_Clippers.MODBASE)
	public static MCP_Clippers INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item clippers = new ItemClippers().setUnlocalizedName("clippers");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(clippers);

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