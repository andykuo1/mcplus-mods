package com.minecraftplus.modGems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.LanguageRegistry;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_Gems.MODBASE, name = "MC+ " + MCP_Gems.MODBASE, version = "1.1.1")
public class MCP_Gems extends MCP
{
	protected static final String MODBASE = "Gems";

	@Instance("MCP_" + MCP_Gems.MODBASE)
	public static MCP_Gems INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Item ruby = new ItemRuby().setUnlocalizedName("ruby");
	public static final Block rubyOre = new BlockOreRuby().setBlockName("ruby_ore");
	public static final Block rubyBlock = new BlockRuby().setBlockName("block_of_ruby");

	public static final Item sapphire = new ItemSapphire().setUnlocalizedName("sapphire");
	public static final Block sapphireOre = new BlockOreSapphire().setBlockName("sapphire_ore");
	public static final Block sapphireBlock = new BlockSapphire().setBlockName("block_of_sapphire");

	public static final Item amethyst = new ItemAmethyst().setUnlocalizedName("amethyst");
	public static final Block amethystOre = new BlockOreAmethyst().setBlockName("amethyst_ore");
	public static final Block amethystBlock = new BlockAmethyst().setBlockName("block_of_amethyst");


	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.1");

		ItemRegistry.add(ruby);
		ItemRegistry.add(rubyOre);
		ItemRegistry.addUnLocal(rubyBlock);

		ItemRegistry.add(sapphire);
		ItemRegistry.add(sapphireOre);
		ItemRegistry.addUnLocal(sapphireBlock);

		ItemRegistry.add(amethyst);
		ItemRegistry.add(amethystOre);
		ItemRegistry.addUnLocal(amethystBlock);
		
		LanguageRegistry.add("tile.block_of_ruby.name", "Block of Ruby");
		LanguageRegistry.add("tile.block_of_sapphire.name", "Block of Sapphire");
		LanguageRegistry.add("tile.block_of_amethyst.name", "Block of Amethyst");

		Registry.addWorldGen(new WorldGenBlockRuby());
		Registry.addWorldGen(new WorldGenBlockSapphire());
		Registry.addWorldGen(new WorldGenBlockAmethyst());

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