package net.minecraftplus.mcp_beetroot;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftplus._api.MCF;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Recipes;
import net.minecraftplus._api.dictionary.Resources;
import net.minecraftplus._api.dictionary.Variants;
import net.minecraftplus._api.factory.ModelFactory;
import net.minecraftplus._api.factory.VariantFactory;

@Mod(modid = _Beetroot.MODID, version = _Beetroot.VERSION, dependencies = "required-after:mcp_api")
public class _Beetroot extends _Mod
{
	public static final String MODID = "mcp_beetroot";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Beetroot INSTANCE;

	public _Beetroot() {}

	public static EventHandlerBeetroot eventHandler = new EventHandlerBeetroot();

	public static final Block beetroots = new BlockBeetroot().setUnlocalizedName("beetroots");

	public static final Item beetroot = new ItemFood(2, 0.4F, false).setUnlocalizedName("beetroot");
	public static final Item beetrootSeeds = new ItemSeeds(beetroots, Blocks.farmland).setUnlocalizedName("beetroot_seeds");
	public static final Item beetrootSoup = new ItemFood(8, 0.6F, false).setUnlocalizedName("beetroot_soup");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.eventHandler(eventHandler);

		MCP.item(beetroot);
		MCP.item(beetrootSeeds);
		MCP.item(beetrootSoup);

		MCP.block(beetroots);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.recipe(Recipes.CONVERT(new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeDamage()),
				beetroot));
		MCP.recipe(Recipes.SHAPELESS(new ItemStack(beetrootSoup),
				beetroot,
				beetroot,
				beetroot,
				beetroot,
				Items.bowl));

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
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(beetroot) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(beetroot)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(beetrootSeeds) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(beetrootSeeds)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(beetrootSoup) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(beetrootSoup)
				).toJSON());

		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(beetroots) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(beetroots)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(beetroots, "stage_0") + ".json", Models.BLOCK_CROP(
				Resources.ofTexture(beetroots, "stage_0")
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(beetroots, "stage_1") + ".json", Models.BLOCK_CROP(
				Resources.ofTexture(beetroots, "stage_1")
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(beetroots, "stage_2") + ".json", Models.BLOCK_CROP(
				Resources.ofTexture(beetroots, "stage_2")
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(beetroots, "stage_3") + ".json", Models.BLOCK_CROP(
				Resources.ofTexture(beetroots, "stage_3")
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(beetroots) + ".json", Variants.CROP(
				Resources.ofModel(beetroots), 4
				).toJSON());

		super.Munge();
	}
}