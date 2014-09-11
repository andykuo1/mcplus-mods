package com.minecraftplus.modTurnip;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

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

@Mod(modid = MCP.D + MCP_Turnip.MODBASE, name = MCP.PRE + MCP_Turnip.MODBASE, version = "1.1.0", dependencies = MCP.DEPENDENCY)
public class MCP_Turnip implements MCPMod
{
	protected static final String MODBASE = "Turnip";

	@Instance(MCP.D + MCP_Turnip.MODBASE)
	public static MCP_Turnip INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Block turnips = new BlockTurnip().setBlockName("turnips");

	public static final Item turnip = new ItemFoodstuff(2, 0.4F).setUnlocalizedName("turnip");
	public static final Item turnipSeeds = new ItemTurnipSeeds().setUnlocalizedName("turnip_seeds");
	public static final Item turnipSoup = new ItemFoodstuff(8, 0.6F).setUnlocalizedName("turnip_soup");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(turnips);
		ItemRegistry.add(turnip);
		ItemRegistry.add(turnipSeeds);
		ItemRegistry.add(turnipSoup);

		ItemRegistry.addDict(turnip, "cropTurnip");
		ItemRegistry.addDict(turnips, "cropTurnip");
		ItemRegistry.addDict(turnipSeeds, "seedTurnip");
		ItemRegistry.addDict(turnipSoup, "soupTurnip");

		MinecraftForge.addGrassSeed(new ItemStack(turnipSeeds), 1);

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