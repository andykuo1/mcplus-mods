package com.minecraftplus.modSilver;

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

@Mod(modid = "MCP_Silver" + MCP_Silver.MODBASE, name = "MC+ Silver" + MCP_Silver.MODBASE, version = "1.0.0")
public class MCP_Silver extends MCP
{
	protected static final String MODBASE = "Silver";

	@Instance("MCP_Silver" + MCP_Silver.MODBASE)
	public static MCP_Silver INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.modSilver" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.modSilver" + MODBASE + ".CommonProxy")
	
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Item silverIngot = new ItemSilverIngot().setUnlocalizedName("silver_ingot");
	public static final Item silverNugget = new ItemSilverNugget().setUnlocalizedName("silver_nugget");
	public static final Item silverSword = new ItemSilverSword().setUnlocalizedName("silver_sword");
	
	public static final Block silverOre = new BlockOreSilver().setBlockName("silver_ore");
	public static final Block silverBlock = new BlockSilver().setBlockName("block_of_silver");
			
	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.1");
		
		ItemRegistry.add(silverIngot);
		ItemRegistry.add(silverNugget);
		ItemRegistry.add(silverSword);
		
		ItemRegistry.add(silverOre);
		ItemRegistry.add(silverBlock);
		
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