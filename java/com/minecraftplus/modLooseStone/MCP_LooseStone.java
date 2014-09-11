package com.minecraftplus.modLooseStone;

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

@Mod(modid = MCP.D + MCP_LooseStone.MODBASE, name = MCP.PRE + MCP_LooseStone.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_LooseStone implements MCPMod
{
	protected static final String MODBASE = "LooseStone";

	@Instance("MCP_" + MCP_LooseStone.MODBASE)
	public static MCP_LooseStone INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Block looseStone = new BlockLooseStone().setBlockName("loose_stone");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(looseStone);

		WorldGenRegistry.add(new WorldGenBlockLooseStone());

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