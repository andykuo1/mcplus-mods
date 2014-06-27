package com.minecraftplus.modWoodenBucket;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

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

@Mod(modid = "MCP_" + MCP_WoodenBucket.MODBASE, name = "MC+ " + MCP_WoodenBucket.MODBASE, version = "1.1.1")
public class MCP_WoodenBucket extends MCP
{
	protected static final String MODBASE = "WoodenBucket";

	@Instance("MCP_" + MCP_WoodenBucket.MODBASE)
	public static MCP_WoodenBucket INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Item woodenBucketEmpty = new ItemWoodenBucketEmpty().setUnlocalizedName("wooden_bucket");
	public static final Item woodenBucketWater = new ItemWoodenBucket(Blocks.flowing_water).setContainerItem(woodenBucketEmpty).setUnlocalizedName("wooden_water_bucket");
	public static final Item woodenBucketLava = new ItemWoodenBucket(Blocks.flowing_lava).setContainerItem(woodenBucketEmpty).setUnlocalizedName("wooden_lava_bucket");
	public static final Item woodenBucketMilk = new ItemWoodenBucketMilk().setContainerItem(woodenBucketEmpty).setUnlocalizedName("wooden_milk_bucket");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.0");

		ItemRegistry.add(woodenBucketEmpty);
		ItemRegistry.add(woodenBucketWater);
		ItemRegistry.add(woodenBucketLava);
		ItemRegistry.add(woodenBucketMilk);

		MinecraftForge.EVENT_BUS.register(new EventFillCauldronHandler());

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