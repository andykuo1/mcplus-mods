package com.minecraftplus.modClayWallSlab;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

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

@Mod(modid = "MCP_" + MCP_ClayWallSlab.MODBASE, name = "MC+ " + MCP_ClayWallSlab.MODBASE, version = "1.0.2")
public class MCP_ClayWallSlab extends MCP
{
	protected static final String MODBASE = "ClayWallSlab";

	@Instance("MCP_" + MCP_ClayWallSlab.MODBASE)
	public static MCP_ClayWallSlab INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Block clayWallHalfSlabNS = new BlockClayWallSlabNS(false, Material.rock).setBlockName("claywall_slab_NS");
	public static final Block clayWallDoubleSlabNS = new BlockClayWallSlabNS(true, Material.rock).setBlockName("claywall_slab_NS");
	public static final Block clayWallHalfSlabNS1 = new BlockClayWallSlabNS1(false, Material.rock).setBlockName("claywall_slab_NS1");
	public static final Block clayWallDoubleSlabNS1 = new BlockClayWallSlabNS1(true, Material.rock).setBlockName("claywall_slab_NS1");
	public static final Block clayWallHalfSlabNS2 = new BlockClayWallSlabNS2(false, Material.rock).setBlockName("claywall_slab_NS2");
	public static final Block clayWallDoubleSlabNS2 = new BlockClayWallSlabNS2(true, Material.rock).setBlockName("claywall_slab_NS2");
	public static final Block clayWallHalfSlabWE = new BlockClayWallSlabWE(false, Material.rock).setBlockName("claywall_slab_WE");
	public static final Block clayWallDoubleSlabWE = new BlockClayWallSlabWE(true, Material.rock).setBlockName("claywall_slab_WE");
	public static final Block clayWallHalfSlabWE1 = new BlockClayWallSlabWE1(false, Material.rock).setBlockName("claywall_slab_WE1");
	public static final Block clayWallDoubleSlabWE1 = new BlockClayWallSlabWE1(true, Material.rock).setBlockName("claywall_slab_WE1");
	public static final Block clayWallHalfSlabWE2 = new BlockClayWallSlabWE2(false, Material.rock).setBlockName("claywall_slab_WE2");
	public static final Block clayWallDoubleSlabWE2 = new BlockClayWallSlabWE2(true, Material.rock).setBlockName("claywall_slab_WE2");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.2");
		
		LanguageRegistry.add("tile.claywall_slab_NS.name", "Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS1.silver.name", "Light Gray Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS1.cyan.name", "Cyan Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS1.purple.name", "Purple Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS1.blue.name", "Blue Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS1.brown.name", "Brown Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS1.green.name", "Green Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS1.red.name", "Red Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS1.black.name", "Black Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS2.white.name", "White Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS2.orange.name", "Orange Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS2.magenta.name", "Magenta Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS2.lightBlue.name", "Light Blue Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS2.yellow.name", "Yellow Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS2.lime.name", "Lime Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS2.pink.name", "Pink Clay Wall Slab");
		LanguageRegistry.add("tile.claywall_slab_NS2.gray.name", "Gray Clay Wall Slab");
		
		ItemRegistry.addUnLocal(clayWallHalfSlabNS, ItemClayWallSlab.class, "claywall_slab_NS");
		ItemRegistry.addUnLocal(clayWallDoubleSlabNS, ItemClayWallSlab.class, "claywall_slab_double_NS");
		ItemRegistry.addUnLocal(clayWallHalfSlabNS1, ItemClayWallSlab.class, "claywall_slab_NS1");
		ItemRegistry.addUnLocal(clayWallDoubleSlabNS1, ItemClayWallSlab.class, "claywall_slab_double_NS1");
		ItemRegistry.addUnLocal(clayWallHalfSlabNS2, ItemClayWallSlab.class, "claywall_slab_NS2");
		ItemRegistry.addUnLocal(clayWallDoubleSlabNS2, ItemClayWallSlab.class, "claywall_slab_double_NS2");
		ItemRegistry.addUnLocal(clayWallHalfSlabWE, ItemClayWallSlab.class, "claywall_slab_WE");
		ItemRegistry.addUnLocal(clayWallDoubleSlabWE, ItemClayWallSlab.class, "claywall_slab_double_WE");
		ItemRegistry.addUnLocal(clayWallHalfSlabWE1, ItemClayWallSlab.class, "claywall_slab_WE1");
		ItemRegistry.addUnLocal(clayWallDoubleSlabWE1, ItemClayWallSlab.class, "claywall_slab_double_WE1");
		ItemRegistry.addUnLocal(clayWallHalfSlabWE2, ItemClayWallSlab.class, "claywall_slab_WE2");
		ItemRegistry.addUnLocal(clayWallDoubleSlabWE2, ItemClayWallSlab.class, "claywall_slab_double_WE2");

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