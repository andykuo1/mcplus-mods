package com.minecraftplus.modSkullCandle;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.LanguageRegistry;
import com.minecraftplus._common.config.GuiConfigHandler;
import com.minecraftplus._common.config.IConfigMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_SkullCandle.MODBASE, name = MCP.PRE + MCP_SkullCandle.MODBASE, version = "1.0.2", dependencies = MCP.DEPENDENCY, guiFactory = MCP.A + MCP_SkullCandle.MODBASE + ".GuiFactory")
public class MCP_SkullCandle implements MCPMod, IConfigMod
{
	protected static final String MODBASE = "SkullCandle";

	@Instance("MCP_" + MCP_SkullCandle.MODBASE)
	public static MCP_SkullCandle INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static GuiConfigHandler configHandler;
	public static boolean isTorchVariant;

	public static final Block skullCandles = new BlockSkullCandle().setBlockName("skull_candles");
	public static final Block redstoneSkullCandles = new BlockRedstoneSkullCandle().setBlockName("redstone_skull_candles");
	public static final Item skullCandle = new ItemSkullCandle().setUnlocalizedName("skull_candle");
	public static final Item redstoneSkullCandle = new ItemRedstoneSkullCandle().setUnlocalizedName("redstone_skull_candle");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(skullCandles);
		ItemRegistry.add(redstoneSkullCandles);
		ItemRegistry.add(skullCandle);
		ItemRegistry.add(redstoneSkullCandle);

		LanguageRegistry.add("item.skull_candle.skeleton.name", "Skeleton Skull Candle");
		LanguageRegistry.add("item.skull_candle.wither.name", "Wither Skeleton Skull Candle");
		LanguageRegistry.add("item.skull_candle.zombie.name", "Zombie Skull Candle");
		LanguageRegistry.add("item.skull_candle.char.name", "Skull Candle");
		LanguageRegistry.add("item.skull_candle.creeper.name", "Creeper Skull Candle");

		LanguageRegistry.add("item.redstone_skull_candle.skeleton.name", "Powered Skeleton Skull Candle");
		LanguageRegistry.add("item.redstone_skull_candle.wither.name", "Powered Wither Skeleton Skull Candle");
		LanguageRegistry.add("item.redstone_skull_candle.zombie.name", "Powered Zombie Skull Candle");
		LanguageRegistry.add("item.redstone_skull_candle.char.name", "Powered Skull Candle");
		LanguageRegistry.add("item.redstone_skull_candle.creeper.name", "Powered Creeper Skull Candle");

		configHandler = new GuiConfigHandler(new Configuration(par1Event.getSuggestedConfigurationFile()), this, MCP.D + MODBASE);
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

	@Override
	public void config(Configuration par1Configuration)
	{
		String s = par1Configuration.getString("Model Type", Configuration.CATEGORY_GENERAL, "TORCH", "", new String[] {"TORCH", "GLOWING"});
		isTorchVariant = "TORCH".equals(s);
	}
}