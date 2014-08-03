package com.minecraftplus.modBerryBush;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.item.ItemFoodstuff;
import com.minecraftplus._common.render.RenderBlock;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_BerryBush.MODBASE, name = "MC+ " + MCP_BerryBush.MODBASE, version = "1.1.1")
public class MCP_BerryBush extends MCP
{
	protected static final String MODBASE = "BerryBush";

	@Instance("MCP_" + MCP_BerryBush.MODBASE)
	public static MCP_BerryBush INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Item raspberry = new ItemFoodstuff(2, 0.1F).setUnlocalizedName("raspberry");
	public static final Item blueberry = new ItemFoodstuff(2, 0.1F).setUnlocalizedName("blueberry");
	public static final Item blackberry = new ItemFoodstuff(2, 0.1F).setUnlocalizedName("blackberry");
	public static final Item cranberry = new ItemFoodstuff(2, 0.1F).setUnlocalizedName("cranberry");
	public static final Block raspberryBush = new BlockBerryBush(raspberry).setBlockName("raspberry_bush");
	public static final Block blueberryBush = new BlockBerryBush(blueberry).setBlockName("blueberry_bush");
	public static final Block blackberryBush = new BlockBerryBush(blackberry).setBlockName("blackberry_bush");
	public static final Block cranberryBush = new BlockBerryBush(cranberry).setBlockName("cranberry_bush");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.3");

		ItemRegistry.add(raspberry);
		ItemRegistry.add(blueberry);
		ItemRegistry.add(blackberry);
		ItemRegistry.add(cranberry);

		ItemRegistry.add(raspberryBush);
		ItemRegistry.add(blueberryBush);
		ItemRegistry.add(blackberryBush);
		ItemRegistry.add(cranberryBush);
		
		ItemRegistry.addDict(raspberryBush, "cropRaspberry");
		ItemRegistry.addDict(blueberryBush, "cropBlueberry");
		ItemRegistry.addDict(blackberryBush, "cropBlackberry");
		ItemRegistry.addDict(cranberryBush, "cropCranberry");
		
		ItemRegistry.addDict(raspberry, "fruitRaspberry");
		ItemRegistry.addDict(blueberry, "fruitBlueberry");
		ItemRegistry.addDict(blackberry, "fruitBlackberry");
		ItemRegistry.addDict(cranberry, "fruitCranberry");

		Registry.addWorldGen(new WorldGenBlockBerryBush(blueberryBush));
		Registry.addWorldGen(new WorldGenBlockBerryBush(raspberryBush));
		Registry.addWorldGen(new WorldGenBlockBerryBush(blackberryBush));
		Registry.addWorldGen(new WorldGenBlockBerryBush(cranberryBush));

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