package com.minecraftplus.modCocoa;

import net.minecraft.item.Item;

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

@Mod(modid = "MCP_" + MCP_Cocoa.MODBASE, name = "MC+ " + MCP_Cocoa.MODBASE, version = "1.3.0")
public class MCP_Cocoa extends MCP
{
	protected static final String MODBASE = "Cocoa";

	@Instance("MCP_" + MCP_Cocoa.MODBASE)
	public static MCP_Cocoa INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Item chocolateBar = new ItemFoodstuff(4, 0.2F).setUnlocalizedName("chocolate_bar");
	public static final Item chocolatePie = new ItemFoodstuff(7, 0.5F).setUnlocalizedName("chocolate_pie");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.2");

		ItemRegistry.add(chocolateBar);
		ItemRegistry.add(chocolatePie);
		
		ItemRegistry.addDict(chocolateBar, "foodChocolate");
		ItemRegistry.addDict(chocolatePie, "pieChocolate");

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