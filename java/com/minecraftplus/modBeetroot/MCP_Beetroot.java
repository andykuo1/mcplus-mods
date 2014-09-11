package com.minecraftplus.modBeetroot;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.ModRegistry;
import com.minecraftplus._common.item.ItemFoodstuff;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Beetroot.MODBASE, name = MCP.PRE + MCP_Beetroot.MODBASE, version = "1.1.0", dependencies = MCP.DEPENDENCY)
public class MCP_Beetroot implements MCPMod
{
	protected static final String MODBASE = "Beetroot";

	@Instance(MCP.D + MCP_Beetroot.MODBASE)
	public static MCP_Beetroot INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Block beetroots = new BlockBeetroot().setBlockName("beetroots");

	public static final Item beetroot = new ItemFoodstuff(2, 0.4F).setUnlocalizedName("beetroot");
	public static final Item beetrootSeeds = new ItemBeetrootSeeds().setUnlocalizedName("beetroot_seeds");
	public static final Item beetrootSoup = new ItemFoodstuff(8, 0.6F).setUnlocalizedName("beetroot_soup");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(beetroots);
		ItemRegistry.add(beetroot);
		ItemRegistry.add(beetrootSeeds);
		ItemRegistry.add(beetrootSoup);

		ItemRegistry.addDict(beetroot, "cropBeetroot");
		ItemRegistry.addDict(beetroots, "cropBeetroot");
		ItemRegistry.addDict(beetrootSeeds, "seedBeetroot");
		ItemRegistry.addDict(beetrootSoup, "soupBeetroot");

		ModRegistry.addEventHandler(MinecraftForge.EVENT_BUS, new EventBeetrootDropHandler());

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