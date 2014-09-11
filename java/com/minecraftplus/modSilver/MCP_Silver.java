package com.minecraftplus.modSilver;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

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

@Mod(modid = MCP.D + MCP_Silver.MODBASE, name = MCP.PRE + MCP_Silver.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_Silver implements MCPMod
{
	protected static final String MODBASE = "Silver";

	@Instance(MCP.D + MCP_Silver.MODBASE)
	public static MCP_Silver INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Block silverOre = new BlockSilverOre().setBlockName("silver_ore");
	public static final Block silverBlock = new BlockSilver().setBlockName("block_of_silver");

	public static final Item silverIngot = new ItemSilverIngot().setUnlocalizedName("silver_ingot");
	public static final Item silverNugget = new ItemSilverNugget().setUnlocalizedName("silver_nugget");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(silverOre);
		ItemRegistry.add(silverBlock);
		ItemRegistry.add(silverIngot);
		ItemRegistry.add(silverNugget);

		WorldGenRegistry.add(new WorldGenBlockOreSilver());

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