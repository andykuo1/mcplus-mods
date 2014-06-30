package com.minecraftplus.modSoulExtractor;

import net.minecraft.block.Block;
import net.minecraft.init.Items;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_SoulExtractor.MODBASE, name = "MC+ " + MCP_SoulExtractor.MODBASE, version = "1.0.2")
public class MCP_SoulExtractor extends MCP
{
	protected static final String MODBASE = "SoulExtractor";

	@Instance("MCP_" + MCP_SoulExtractor.MODBASE)
	public static MCP_SoulExtractor INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Block soulExtractor = new BlockSoulExtractor().setBlockName("soul_extractor");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.0");

		ItemRegistry.add(soulExtractor);
		Items.experience_bottle.setMaxStackSize(1);

		Registry.addPacket(PacketAbsorbSoul.class);
		Registry.addGuiHandler(this, new GuiHandler());

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