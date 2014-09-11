package com.minecraftplus.modWoodenBucket;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_WoodenBucket.MODBASE, name = MCP.PRE + MCP_WoodenBucket.MODBASE, version = "1.2.0", dependencies = MCP.DEPENDENCY)
public class MCP_WoodenBucket implements MCPMod
{
	protected static final String MODBASE = "WoodenBucket";

	@Instance("MCP_" + MCP_WoodenBucket.MODBASE)
	public static MCP_WoodenBucket INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item woodenBucketEmpty = new ItemWoodenBucketEmpty().setUnlocalizedName("wooden_bucket");
	public static final Item woodenBucketWater = new ItemWoodenBucket(Blocks.flowing_water).setContainerItem(woodenBucketEmpty).setUnlocalizedName("wooden_water_bucket");
	public static final Item woodenBucketMilk = new ItemWoodenBucketMilk().setContainerItem(woodenBucketEmpty).setUnlocalizedName("wooden_milk_bucket");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(woodenBucketEmpty);
		ItemRegistry.add(woodenBucketWater);
		ItemRegistry.add(woodenBucketMilk);

		MinecraftForge.EVENT_BUS.register(new EventFillCauldronHandler());

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