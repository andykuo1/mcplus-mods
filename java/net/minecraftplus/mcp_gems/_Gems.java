package net.minecraftplus.mcp_gems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressed;
import net.minecraft.block.material.MapColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftplus._api.MCF;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.dictionary.Dimensions;
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Recipes;
import net.minecraftplus._api.dictionary.Resources;
import net.minecraftplus._api.dictionary.Variants;
import net.minecraftplus._api.factory.ModelFactory;
import net.minecraftplus._api.factory.VariantFactory;
import net.minecraftplus._api.minecraft.base.BlockOreBase;
import net.minecraftplus._api.minecraft.base.WorldGenOreBase;

@Mod(modid = _Gems.MODID, version = _Gems.VERSION, dependencies = "required-after:mcp_api")
public class _Gems extends _Mod
{
	public static final String MODID = "mcp_gems";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Gems INSTANCE;

	public _Gems() {}

	public static final Item ruby = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("ruby");
	public static final Block rubyOre = new BlockOreBase(ruby).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("ruby_ore");
	public static final Block rubyBlock = new BlockCompressed(MapColor.redColor).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("block_of_ruby");

	public static final Item sapphire = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("sapphire");
	public static final Block sapphireOre = new BlockOreBase(sapphire).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("sapphire_ore");
	public static final Block sapphireBlock = new BlockCompressed(MapColor.blueColor).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("block_of_sapphire");

	public static final Item amethyst = new Item().setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("amethyst");
	public static final Block amethystOre = new BlockOreBase(amethyst).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("amethyst_ore");
	public static final Block amethystBlock = new BlockCompressed(MapColor.purpleColor).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston).setUnlocalizedName("block_of_amethyst");


	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(ruby);
		MCP.item(sapphire);
		MCP.item(amethyst);

		MCP.block(rubyOre);
		MCP.block(sapphireOre);
		MCP.block(amethystOre);
		MCP.block(rubyBlock);
		MCP.block(sapphireBlock);
		MCP.block(amethystBlock);

		MCP.lang(rubyBlock.getUnlocalizedName() + ".name", "Block of Ruby");
		MCP.lang(sapphireBlock.getUnlocalizedName() + ".name", "Block of Sapphire");
		MCP.lang(amethystBlock.getUnlocalizedName() + ".name", "Block of Amethyst");

		GameRegistry.registerWorldGenerator(new WorldGenOreBase(rubyOre.getDefaultState(), 4, 6, Dimensions.SURFACE).setSpawnHeight(70), 10);
		GameRegistry.registerWorldGenerator(new WorldGenOreBase(sapphireOre.getDefaultState(), 4, 6, Dimensions.SURFACE).setSpawnHeight(70), 10);
		GameRegistry.registerWorldGenerator(new WorldGenOreBase(amethystOre.getDefaultState(), 8, 6, Dimensions.NETHER), 10);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.recipe(Recipes.COMPRESSED(new ItemStack(rubyBlock),
				ruby));
		MCP.recipe(Recipes.COMPRESSED(new ItemStack(sapphireBlock),
				sapphire));
		MCP.recipe(Recipes.COMPRESSED(new ItemStack(amethystBlock),
				amethyst));

		MCP.recipe(Recipes.CONVERT(new ItemStack(ruby, 9),
				rubyBlock));		
		MCP.recipe(Recipes.CONVERT(new ItemStack(sapphire, 9),
				sapphireBlock));		
		MCP.recipe(Recipes.CONVERT(new ItemStack(amethyst, 9),
				amethystBlock));

		proxy.Initialize();
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		super.PostInitialize(parEvent);
	}

	@Override
	public void Configure(Configuration parConfiguration)
	{
		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(_Gems.ruby) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(_Gems.ruby)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(_Gems.sapphire) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(_Gems.sapphire)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(_Gems.amethyst) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(_Gems.amethyst)
				).toJSON());

		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(_Gems.rubyOre) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(_Gems.rubyOre)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(_Gems.sapphireOre) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(_Gems.sapphireOre)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(_Gems.amethystOre) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(_Gems.amethystOre)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(_Gems.rubyOre) + ".json", Models.BLOCK_BASE(
				Resources.ofTexture(_Gems.rubyOre)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(_Gems.sapphireOre) + ".json", Models.BLOCK_BASE(
				Resources.ofTexture(_Gems.sapphireOre)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(_Gems.amethystOre) + ".json", Models.BLOCK_BASE(
				Resources.ofTexture(_Gems.amethystOre)
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(_Gems.rubyOre) + ".json", Variants.NORMAL(
				Resources.ofModel(_Gems.rubyOre)
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(_Gems.sapphireOre) + ".json", Variants.NORMAL(
				Resources.ofModel(_Gems.sapphireOre)
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(_Gems.amethystOre) + ".json", Variants.NORMAL(
				Resources.ofModel(_Gems.amethystOre)
				).toJSON());

		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(_Gems.rubyBlock) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(_Gems.rubyBlock)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(_Gems.sapphireBlock) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(_Gems.sapphireBlock)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(_Gems.amethystBlock) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(_Gems.amethystBlock)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(_Gems.rubyBlock) + ".json", Models.BLOCK_BASE(
				Resources.ofTexture(_Gems.rubyBlock)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(_Gems.sapphireBlock) + ".json", Models.BLOCK_BASE(
				Resources.ofTexture(_Gems.sapphireBlock)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(_Gems.amethystBlock) + ".json", Models.BLOCK_BASE(
				Resources.ofTexture(_Gems.amethystBlock)
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(_Gems.rubyBlock) + ".json", Variants.NORMAL(
				Resources.ofModel(_Gems.rubyBlock)
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(_Gems.sapphireBlock) + ".json", Variants.NORMAL(
				Resources.ofModel(_Gems.sapphireBlock)
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(_Gems.amethystBlock) + ".json", Variants.NORMAL(
				Resources.ofModel(_Gems.amethystBlock)
				).toJSON());

		super.Munge();
	}
}