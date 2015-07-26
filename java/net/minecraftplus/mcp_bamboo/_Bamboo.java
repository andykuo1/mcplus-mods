package net.minecraftplus.mcp_bamboo;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
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

@Mod(modid = _Bamboo.MODID, version = _Bamboo.VERSION, dependencies = "required-after:mcp_api")
public class _Bamboo extends _Mod
{
	public static final String MODID = "mcp_bamboo";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Bamboo INSTANCE;

	public _Bamboo() {}

	public static final Block bamboo = new BlockBamboo().setHardness(0.5F).setStepSound(Block.soundTypeGrass).setUnlocalizedName("bamboo");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.block(bamboo);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.recipe(Recipes.COMPRESSED(new ItemStack(bamboo),
				Items.reeds));
		MCP.recipe(Recipes.CONVERT(new ItemStack(Items.reeds, 9),
				bamboo));

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
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(bamboo) + ".json", Models.ITEM_BLOCK(
				Resources.ofModelParent(bamboo)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(bamboo) + ".json", Models.BLOCK_COLUMN(
				Resources.ofTexture(bamboo, "top"),
				Resources.ofTexture(bamboo)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(bamboo, "side") + ".json", Models.BLOCK_COLUMN_SIDE(
				Resources.ofTexture(bamboo, "top"),
				Resources.ofTexture(bamboo)
				).toJSON());
		ModelFactory.write(MCF.blockModelDirectory(MODID), Resources.of(bamboo, "bark") + ".json", Models.BLOCK_BASE(
				Resources.ofTexture(bamboo)
				).toJSON());
		VariantFactory.write(MCF.variantDirectory(MODID), Resources.of(bamboo) + ".json", Variants.COLUMN(
				Resources.ofModel(bamboo),
				Resources.ofModel(bamboo, "side"),
				Resources.ofModel(bamboo, "bark")
				).toJSON());

		super.Munge();
	}
}