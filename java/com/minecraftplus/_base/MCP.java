package com.minecraftplus._base;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.handler.EventDyeRemoveHandler;
import com.minecraftplus._base.handler.FuelHandler;
import com.minecraftplus._base.handler.PacketHandler;
import com.minecraftplus._base.worldgen.WorldGenBlock;
import com.minecraftplus._base.worldgen.WorldGenHandler;
import com.minecraftplus._common.packet.Packet;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class MCP
{
	public static String VERSION = "1.3";

	public abstract void preInit(FMLPreInitializationEvent par1Event);
	public abstract void loadInit(FMLInitializationEvent par1Event);
	public abstract void postInit(FMLPostInitializationEvent par1Event);

	private static boolean initMain;
	private static boolean initEvent;
	private static boolean initClient;

	public static PacketHandler packetHandler = new PacketHandler("minecraftplus");
	public static FuelHandler fuelHandler = new FuelHandler();
	public static WorldGenHandler worldGenHandler = new WorldGenHandler();

	private static final Map<ItemStack, ItemStack> furnaceRecipes = new HashMap<ItemStack, ItemStack>();

	public MCP() {}

	public static boolean initMain(FMLPreInitializationEvent par1Event, String par2String)
	{
		if (!VERSION.equals(par2String))
		{
			System.out.print("MC+ Mods: ");
			System.out.print("WRONG VERSION > ");

			if (!isValidVersion(par2String))
			{
				System.err.println("OUTDATED");
			}
			else
			{
				System.out.println("RESOLVED");
			}
		}

		if (initMain) return true; initMain = true;

		packetHandler.initialize();

		GameRegistry.registerFuelHandler(fuelHandler);
		GameRegistry.registerWorldGenerator(worldGenHandler, 1);
		MinecraftForge.EVENT_BUS.register(new EventDyeRemoveHandler());
		return true;
	}

	public static void initEvent(FMLInitializationEvent par1Event)
	{
		if (initEvent) return; initEvent = true;
		furnaceRecipes.putAll(FurnaceRecipes.smelting().getSmeltingList());

		packetHandler.postInitialize();
	}

	@SideOnly(Side.CLIENT)
	public static void initClient()
	{
		if (initClient) return; initClient = true;
	}

	public static boolean isValidVersion(String par1Version)
	{
		return Float.valueOf(par1Version) <= Float.valueOf(VERSION);
	}

	public static void registerFuel(Item par1Item, Integer par2BurnTime)
	{
		fuelHandler.add(par1Item, par2BurnTime);
	}

	public static void registerFuel(Class<? extends Item> par1Class, Integer par2BurnTime)
	{
		fuelHandler.add(par1Class, par2BurnTime);
	}

	public static void registerWorldGenBlock(WorldGenBlock par1WorldGenBlock)
	{
		worldGenHandler.add(par1WorldGenBlock);
	}

	public static boolean registerPacket(Class<? extends Packet> par1Class)
	{
		if (!initMain) return false;
		packetHandler.registerPacket(par1Class);
		return true;
	}

	public static Map<ItemStack, ItemStack> getFurnaceRecipes()
	{
		return furnaceRecipes;
	}
}
