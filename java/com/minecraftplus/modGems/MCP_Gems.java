package com.minecraftplus.modGems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.LanguageRegistry;
import com.minecraftplus._base.registry.WorldGenRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Gems.MODBASE, name = MCP.PRE + MCP_Gems.MODBASE, version = "1.2.1", dependencies = MCP.DEPENDENCY)
public class MCP_Gems implements MCPMod
{
	protected static final String MODBASE = "Gems";

	@Instance(MCP.D + MCP_Gems.MODBASE)
	public static MCP_Gems INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

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
		LanguageRegistry.add("tile.block_of_ruby.name", "Block of Ruby");
		LanguageRegistry.add("tile.block_of_sapphire.name", "Block of Sapphire");
		LanguageRegistry.add("tile.block_of_amethyst.name", "Block of Amethyst");

		ItemRegistry.add(ruby);
		ItemRegistry.add(rubyOre);
		ItemRegistry.addUnLocal(rubyBlock);

		ItemRegistry.add(sapphire);
		ItemRegistry.add(sapphireOre);
		ItemRegistry.addUnLocal(sapphireBlock);

		ItemRegistry.add(amethyst);
		ItemRegistry.add(amethystOre);
		ItemRegistry.addUnLocal(amethystBlock);

		ItemRegistry.addDict(ruby, "gemRuby");
		ItemRegistry.addDict(sapphire, "gemSapphire");
		ItemRegistry.addDict(amethyst, "gemAmethyst");

		ItemRegistry.addDict(rubyOre, "oreRuby");
		ItemRegistry.addDict(sapphireOre, "oreSapphire");
		ItemRegistry.addDict(amethystOre, "oreAmethyst");

		WorldGenRegistry.add(new WorldGenBlockRuby());
		WorldGenRegistry.add(new WorldGenBlockSapphire());
		WorldGenRegistry.add(new WorldGenBlockAmethyst());

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