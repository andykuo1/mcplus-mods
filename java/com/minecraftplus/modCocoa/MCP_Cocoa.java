package com.minecraftplus.modCocoa;

import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._common.item.ItemFoodstuff;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Cocoa.MODBASE, name = MCP.PRE + MCP_Cocoa.MODBASE, version = "1.3.0", dependencies = MCP.DEPENDENCY)
public class MCP_Cocoa implements MCPMod
{
	protected static final String MODBASE = "Cocoa";

	@Instance(MCP.D + MCP_Cocoa.MODBASE)
	public static MCP_Cocoa INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item chocolateBar = new ItemFoodstuff(4, 0.2F).setUnlocalizedName("chocolate_bar");
	public static final Item chocolatePie = new ItemFoodstuff(7, 0.5F).setUnlocalizedName("chocolate_pie");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(chocolateBar);
		ItemRegistry.add(chocolatePie);

		ItemRegistry.addDict(chocolateBar, "foodChocolate");
		ItemRegistry.addDict(chocolatePie, "pieChocolate");

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