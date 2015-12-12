package net.minecraftplus.mcp_turnip;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
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

@Mod(modid = _Turnip.MODID, version = _Turnip.VERSION, dependencies = "required-after:mcp_api")
public class _Turnip extends _Mod
{
	public static final String MODID = "mcp_turnip";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Turnip INSTANCE;

	public _Turnip() {}

	public static final Block turnips = new BlockTurnip().setUnlocalizedName("turnips");

	public static final Item turnip = new ItemFood(2, 0.4F, false).setUnlocalizedName("turnip");
	public static final Item turnipSeeds = new ItemSeeds(turnips, Blocks.farmland).setUnlocalizedName("turnip_seeds");
	public static final Item turnipSoup = new ItemSoup(6).setUnlocalizedName("turnip_soup");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(turnip);
		MCP.item(turnipSeeds);
		MCP.item(turnipSoup);

		MCP.block(turnips);

		MinecraftForge.addGrassSeed(new ItemStack(turnipSeeds), 1);
		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.recipe(Recipes.SHAPELESS(new ItemStack(turnipSoup),
				turnip,
				turnip,
				turnip,
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
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(turnip) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(turnip)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(turnipSeeds) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(turnipSeeds)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(turnipSoup) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(turnipSoup)
				).toJSON());

		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(turnips) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(turnips, "stage_3")
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(turnips, "stage_0") + ".json", Models.BLOCK_CROP(
				Resources.ofTexture(turnips, "stage_0")
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(turnips, "stage_1") + ".json", Models.BLOCK_CROP(
				Resources.ofTexture(turnips, "stage_1")
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(turnips, "stage_2") + ".json", Models.BLOCK_CROP(
				Resources.ofTexture(turnips, "stage_2")
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(turnips, "stage_3") + ".json", Models.BLOCK_CROP(
				Resources.ofTexture(turnips, "stage_3")
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(turnips) + ".json", Variants.CROP(
				Resources.ofModel(turnips), 4
				).toJSON());

		super.Munge();
	}
}