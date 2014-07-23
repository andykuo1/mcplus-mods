package com.minecraftplus.modSulfur;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

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

@Mod(modid = "MCP_" + MCP_Sulfur.MODBASE, name = "MC+ " + MCP_Sulfur.MODBASE, version = "1.0.0")
public class MCP_Sulfur extends MCP
{
	protected static final String MODBASE = "Sulfur";

	@Instance("MCP_" + MCP_Sulfur.MODBASE)
	public static MCP_Sulfur INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Item sulfur = new ItemSulfur().setUnlocalizedName("sulfur");
	public static final Block sulfurOre = new BlockOreSulfur().setBlockName("sulfur_ore");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.2");

		ItemRegistry.add(sulfur);
		ItemRegistry.add(sulfurOre);

		ItemRegistry.addDict(sulfur, "materialSulfur");
		ItemRegistry.addDict(sulfurOre, "oreSulfur");

		Registry.addWorldGen(new WorldGenBlockSulfur());

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