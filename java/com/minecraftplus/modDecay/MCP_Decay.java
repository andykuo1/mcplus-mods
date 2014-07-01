package com.minecraftplus.modDecay;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.LanguageRegistry;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_Decay.MODBASE, name = "MC+ " + MCP_Decay.MODBASE, version = "1.0.0")
public class MCP_Decay extends MCP
{
	protected static final String MODBASE = "Decay";

	@Instance("MCP_" + MCP_Decay.MODBASE)
	public static MCP_Decay INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .
	public static final Block torchLit = new BlockTorchLit().setBlockName("torch_on");
	public static final Block torchUnlit = new BlockTorchUnlit().setBlockName("torch_off");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.1");

		LanguageRegistry.add("tile.torch_off.name", "Torch");
		
		ItemRegistry.addUnLocal(torchLit);
		ItemRegistry.addUnLocal(torchUnlit);

		MinecraftForge.EVENT_BUS.register(new EventDistinguishTorchHandler());

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