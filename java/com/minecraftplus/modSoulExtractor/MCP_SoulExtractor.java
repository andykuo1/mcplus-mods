package com.minecraftplus.modSoulExtractor;

import net.minecraft.block.Block;
import net.minecraft.init.Items;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.ModRegistry;
import com.minecraftplus._base.registry.PacketRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_SoulExtractor.MODBASE, name = MCP.PRE + MCP_SoulExtractor.MODBASE, version = "1.0.3", dependencies = MCP.DEPENDENCY)
public class MCP_SoulExtractor implements MCPMod
{
	protected static final String MODBASE = "SoulExtractor";

	@Instance(MCP.D + MCP_SoulExtractor.MODBASE)
	public static MCP_SoulExtractor INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Block soulExtractor = new BlockSoulExtractor().setBlockName("soul_extractor");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(soulExtractor);
		Items.experience_bottle.setMaxStackSize(1);

		PacketRegistry.add(PacketAbsorbSoul.class);
		ModRegistry.addGuiHandler(this, new GuiHandler());

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