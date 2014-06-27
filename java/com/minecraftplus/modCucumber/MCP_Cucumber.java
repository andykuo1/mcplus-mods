package com.minecraftplus.modCucumber;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.item.ItemFoodstuff;
import com.minecraftplus._common.render.RenderBlock;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_Cucumber.MODBASE, name = "MC+ " + MCP_Cucumber.MODBASE, version = "1.0.0")
public class MCP_Cucumber extends MCP
{
	protected static final String MODBASE = "Cucumber";

	@Instance("MCP_" + MCP_Cucumber.MODBASE)
	public static MCP_Cucumber INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final RenderBlock renderCucumberPlant = new RenderBlockCucumberPlant();

	public static final Block cucumbers = new BlockCucumber().setBlockName("cucumbers_top");
	public static final Block cucumbersBase = new BlockCucumberBase().setBlockName("cucumbers_bottom");

	public static final Item cucumber = new ItemFoodstuff(5, 0.4F).setUnlocalizedName("cucumber");
	public static final Item cucumberSeeds = new ItemCucumberSeeds().setUnlocalizedName("cucumber_seeds");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.0");

		ItemRegistry.add(cucumbers);
		ItemRegistry.add(cucumbersBase);
		
		ItemRegistry.add(cucumber);
		ItemRegistry.add(cucumberSeeds);

		Registry.addRenderBlock(renderCucumberPlant);

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