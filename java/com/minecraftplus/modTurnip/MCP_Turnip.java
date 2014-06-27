package com.minecraftplus.modTurnip;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

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

@Mod(modid = "MCP_" + MCP_Turnip.MODBASE, name = "MC+ " + MCP_Turnip.MODBASE, version = "1.0.0")
public class MCP_Turnip extends MCP
{
	protected static final String MODBASE = "Turnip";

	@Instance("MCP_" + MCP_Turnip.MODBASE)
	public static MCP_Turnip INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Block turnips = new BlockTurnip().setBlockName("turnips");

	public static final Item turnip = new ItemFoodstuff(2, 0.4F).setUnlocalizedName("turnip");
	public static final Item turnipSeeds = new ItemTurnipSeeds().setUnlocalizedName("turnip_seeds");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.0");

		ItemRegistry.add(turnips);
		ItemRegistry.add(turnip);
		ItemRegistry.add(turnipSeeds);

		MinecraftForge.addGrassSeed(new ItemStack(turnipSeeds), 6);

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