package com.minecraftplus.modBeetroot;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.item.ItemFoodstuff;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_Beetroot.MODBASE, name = "MC+ " + MCP_Beetroot.MODBASE, version = "1.1.0")
public class MCP_Beetroot extends MCP
{
	protected static final String MODBASE = "Beetroot";

	@Instance("MCP_" + MCP_Beetroot.MODBASE)
	public static MCP_Beetroot INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Block beetroots = new BlockBeetroot().setBlockName("beetroots");

	public static final Item beetroot = new ItemFoodstuff(2, 0.4F).setUnlocalizedName("beetroot");
	public static final Item beetrootSeeds = new ItemBeetrootSeeds().setUnlocalizedName("beetroot_seeds");
	public static final Item beetrootSoup = new ItemFoodstuff(8, 0.6F).setUnlocalizedName("beetroot_soup");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.2");

		ItemRegistry.add(beetroots);
		ItemRegistry.add(beetroot);
		ItemRegistry.add(beetrootSeeds);
		ItemRegistry.add(beetrootSoup);

		ItemRegistry.addDict(beetroot, "cropBeetroot");
		ItemRegistry.addDict(beetroots, "cropBeetroot");
		ItemRegistry.addDict(beetrootSeeds, "seedBeetroot");
		ItemRegistry.addDict(beetrootSoup, "soupBeetroot");

		Registry.addEventHandler(MinecraftForge.EVENT_BUS, new EventBeetrootDropHandler());

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