package com.minecraftplus.modDecay;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.LanguageRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Decay.MODBASE, name = MCP.PRE + MCP_Decay.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_Decay implements MCPMod
{
	protected static final String MODBASE = "Decay";

	@Instance(MCP.D + MCP_Decay.MODBASE)
	public static MCP_Decay INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Block torchLit = new BlockTorchLit().setBlockName("torch_on");
	public static final Block torchUnlit = new BlockTorchUnlit().setBlockName("torch_off");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		LanguageRegistry.add("tile.torch_off.name", "Torch");

		ItemRegistry.addUnLocal(torchLit);
		ItemRegistry.addUnLocal(torchUnlit);

		MinecraftForge.EVENT_BUS.register(new EventDistinguishTorchHandler());

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