package com.minecraftplus.modSkullCandle;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

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

@Mod(modid = "MCP_" + MCP_SkullCandle.MODBASE, name = "MC+ " + MCP_SkullCandle.MODBASE, version = "1.0.0")
public class MCP_SkullCandle extends MCP
{
	protected static final String MODBASE = "SkullCandle";

	@Instance("MCP_" + MCP_SkullCandle.MODBASE)
	public static MCP_SkullCandle INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Block skullCandles = new BlockSkullCandle().setBlockName("skull_candles");
	public static final Block redstoneSkullCandles = new BlockRedstoneSkullCandle().setBlockName("redstone_skull_candles");
	public static final Item skullCandle = new ItemSkullCandle().setUnlocalizedName("skull_candle");
	public static final Item redstoneSkullCandle = new ItemRedstoneSkullCandle().setUnlocalizedName("redstone_skull_candle");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.1");

		ItemRegistry.add(skullCandles);
		ItemRegistry.add(redstoneSkullCandles);
		ItemRegistry.add(skullCandle);
		ItemRegistry.add(redstoneSkullCandle);

		LanguageRegistry.add("item.skull_candle.skeleton.name", "Skeleton Skull Candle");
		LanguageRegistry.add("item.skull_candle.wither.name", "Wither Skeleton Skull Candle");
		LanguageRegistry.add("item.skull_candle.zombie.name", "Zombie Skull Candle");
		LanguageRegistry.add("item.skull_candle.char.name", "Skull Candle");
		LanguageRegistry.add("item.skull_candle.creeper.name", "Creeper Skull Candle");
		
		LanguageRegistry.add("item.redstone_skull_candle.skeleton.name", "Skeleton Redstone Skull Candle");
		LanguageRegistry.add("item.redstone_skull_candle.wither.name", "Wither Skeleton Redstone Skull Candle");
		LanguageRegistry.add("item.redstone_skull_candle.zombie.name", "Zombie Redstone Skull Candle");
		LanguageRegistry.add("item.redstone_skull_candle.char.name", "Redstone Skull Candle");
		LanguageRegistry.add("item.redstone_skull_candle.creeper.name", "Creeper Redstone Skull Candle");

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