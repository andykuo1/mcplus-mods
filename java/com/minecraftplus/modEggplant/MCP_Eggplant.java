package com.minecraftplus.modEggplant;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.item.ItemFoodstuff;
import com.minecraftplus._common.render.RenderBlock;
import com.minecraftplus.modCucumber.BlockCucumber;
import com.minecraftplus.modCucumber.BlockCucumberBase;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_Eggplant.MODBASE, name = "MC+ " + MCP_Eggplant.MODBASE, version = "1.0.0")
public class MCP_Eggplant extends MCP
{
	protected static final String MODBASE = "Eggplant";

	@Instance("MCP_" + MCP_Eggplant.MODBASE)
	public static MCP_Eggplant INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final RenderBlock renderEggplantPlant = new RenderBlockEggplantPlant();

	public static final Block eggplants = new BlockCucumber().setBlockName("eggplants_top");
	public static final Block eggplantsBase = new BlockCucumberBase().setBlockName("eggplants_bottom");

	public static final Item eggplant = new ItemFoodstuff(4, 0.4F).setUnlocalizedName("eggplant");
	public static final Item eggplantSeeds = new ItemEggplantSeeds().setUnlocalizedName("eggplant_seeds");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.1");

		ItemRegistry.add(eggplants);
		ItemRegistry.add(eggplantsBase);

		ItemRegistry.add(eggplant);
		ItemRegistry.add(eggplantSeeds);

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