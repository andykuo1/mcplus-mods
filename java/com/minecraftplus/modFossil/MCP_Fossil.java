package com.minecraftplus.modFossil;

import net.minecraft.block.Block;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.WorldGenRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Fossil.MODBASE, name = MCP.PRE + MCP_Fossil.MODBASE, version = "1.1.2", dependencies = MCP.DEPENDENCY)
public class MCP_Fossil implements MCPMod
{
	protected static final String MODBASE = "Fossil";

	@Instance(MCP.D + MCP_Fossil.MODBASE)
	public static MCP_Fossil INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	//TODO: Redo Fossil Stone texture

	public static final Block fossilStone = new BlockFossil().setBlockName("fossil_stone");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(fossilStone);

		WorldGenRegistry.add(new WorldGenBlockFossil());

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