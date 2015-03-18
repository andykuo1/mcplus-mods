package net.minecraftplus.gems;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModGems.MODID, version = ModGems.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModGems extends MCP
{
	public static final String MODID = "gems";
	public static final String VERSION = "1.2.1";

	@Instance(MCP.MCPID + MODID)
	public static ModGems INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Item ruby = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("ruby");
	public static final Block rubyOre = new BlockOreRuby().setBlockName("ruby_ore");
	public static final Block rubyBlock = new BlockRuby().setBlockName("block_of_ruby");

	public static final Item sapphire = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("sapphire");
	public static final Block sapphireOre = new BlockOreSapphire().setBlockName("sapphire_ore");
	public static final Block sapphireBlock = new BlockSapphire().setBlockName("block_of_sapphire");

	public static final Item amethyst = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("amethyst");
	public static final Block amethystOre = new BlockOreAmethyst().setBlockName("amethyst_ore");
	public static final Block amethystBlock = new BlockAmethyst().setBlockName("block_of_amethyst");

	public ModGems()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(ruby).setOreDict("gemRuby");
		MCP.ITEMS().add(rubyOre).setOreDict("oreRuby");
		MCP.ITEMS().add(rubyBlock).setLocalName("Block of Ruby");

		MCP.ITEMS().add(sapphire).setOreDict("gemSapphire");
		MCP.ITEMS().add(sapphireOre).setOreDict("oreSapphire");
		MCP.ITEMS().add(sapphireBlock).setLocalName("Block of Sapphire");

		MCP.ITEMS().add(amethyst).setOreDict("gemAmethyst");
		MCP.ITEMS().add(amethystOre).setOreDict("oreAmethyst");
		MCP.ITEMS().add(amethystBlock).setLocalName("Block of Amethyst");
		MCP.ITEMS().initialize();

		MCP.WORLDGENS().add(new WorldGenRuby());
		MCP.WORLDGENS().add(new WorldGenSapphire());
		MCP.WORLDGENS().add(new WorldGenAmethyst());

		proxy.register();
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		super.PostInitialize(parEvent);
	}
}
