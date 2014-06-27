package com.minecraftplus.modRuby;

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

@Mod(modid = "MCP_" + MCP_Ruby.MODBASE, name = "MC+ " + MCP_Ruby.MODBASE, version = "1.1.0")
public class MCP_Ruby extends MCP
{
	protected static final String MODBASE = "Ruby";

	@Instance("MCP_" + MCP_Ruby.MODBASE)
	public static MCP_Ruby INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Item ruby = new ItemRuby().setUnlocalizedName("ruby");
	public static final Block rubyOre = new BlockOreRuby().setBlockName("ruby_ore");
	public static final Block rubyBlock = new BlockRuby().setBlockName("block_of_ruby");
	
	public static final Item Sapphire = new ItemSapphire().setUnlocalizedName("Sapphire");
	public static final Block SapphireOre = new BlockOreSapphire().setBlockName("Sapphire_Ore");
	public static final Block SapphireBlock = new BlockSapphire().setBlockName("Block_Of_Sapphire");
	

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.0");

		ItemRegistry.add(ruby);
		ItemRegistry.add(rubyOre);
		ItemRegistry.add(rubyBlock);
		
		ItemRegistry.add(Sapphire);
		ItemRegistry.add(SapphireOre);
		ItemRegistry.add(SapphireBlock);

		Registry.addWorldGen(new WorldGenBlockRuby());
		Registry.addWorldGen(new WorldGenBlockSapphire());

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