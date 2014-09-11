package com.minecraftplus.modTrappedLadder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate.Sensitivity;

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

@Mod(modid = MCP.D + MCP_TrappedLadder.MODBASE, name = MCP.PRE + MCP_TrappedLadder.MODBASE, version = "1.1.2", dependencies = MCP.DEPENDENCY)
public class MCP_TrappedLadder implements MCPMod
{
	protected static final String MODBASE = "TrappedLadder";

	@Instance(MCP.D + MCP_TrappedLadder.MODBASE)
	public static MCP_TrappedLadder INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Block ladderTrapped = new BlockLadderTrapped(Sensitivity.players).setBlockName("trapped_ladder");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(ladderTrapped);

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