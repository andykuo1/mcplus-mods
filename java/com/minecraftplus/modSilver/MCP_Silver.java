package com.minecraftplus.modSilver;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

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

@Mod(modid = "MCP_" + MCP_Silver.MODBASE, name = "MC+ " + MCP_Silver.MODBASE, version = "1.0.0")
public class MCP_Silver extends MCP
{
	protected static final String MODBASE = "Silver";

	@Instance("MCP_" + MCP_Silver.MODBASE)
	public static MCP_Silver INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .
	public static final Block silverOre = new BlockSilverOre().setBlockName("silver_ore");
	public static final Block silverBlock = new BlockSilver().setBlockName("block_of_silver");
	
	public static final Item silverIngot = new ItemSilverIngot().setUnlocalizedName("silver_ingot");
	public static final Item silverNugget = new ItemSilverNugget().setUnlocalizedName("silver_nugget");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.1");

		ItemRegistry.add(silverOre);
		ItemRegistry.add(silverBlock);
		ItemRegistry.add(silverIngot);
		ItemRegistry.add(silverNugget);
		
		Registry.addWorldGen(new WorldGenBlockOreSilver());
		
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