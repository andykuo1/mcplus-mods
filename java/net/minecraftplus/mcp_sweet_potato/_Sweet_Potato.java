package net.minecraftplus.mcp_sweet_potato;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeedFood;
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
import net.minecraftplus._api.dictionary.ExperienceDrops;
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Resources;
import net.minecraftplus._api.dictionary.Variants;

@Mod(modid = _Sweet_Potato.MODID, version = _Sweet_Potato.VERSION, dependencies = "required-after:mcp_api")
public class _Sweet_Potato extends _Mod
{
	public static final String MODID = "mcp_sweet_potato";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Sweet_Potato INSTANCE;

	public _Sweet_Potato() {}

	public static EventHandlerSweetPotato eventHandler = new EventHandlerSweetPotato();

	public static final Block sweetPotatoes = new BlockSweetPotatoes().setUnlocalizedName("sweet_potatoes");

	public static final Item sweetPotato = new ItemSeedFood(4, 0.1F, sweetPotatoes, Blocks.farmland).setUnlocalizedName("sweet_potato");
	public static final Item bakedSweetPotato = new ItemFood(10, 0.4F, false).setUnlocalizedName("baked_sweet_potato");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.eventHandler(eventHandler);

		MCP.item(sweetPotato);
		MCP.item(bakedSweetPotato);

		MCP.block(sweetPotatoes);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.smelt(sweetPotato, new ItemStack(bakedSweetPotato), ExperienceDrops.BAKED_POTATO);

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
		MCF.makeItemModel(Resources.of(sweetPotato), Models.ITEM_BASE(
				Resources.ofTexture(sweetPotato)
				));
		MCF.makeItemModel(Resources.of(bakedSweetPotato), Models.ITEM_BASE(
				Resources.ofTexture(bakedSweetPotato)
				));

		//TODO: This is missing an inventory model apparently . . .
		MCF.makeItemModel(Resources.of(sweetPotatoes), Models.ITEM_BLOCK(
				Resources.ofModelParent(sweetPotatoes)
				));
		MCF.makeBlockModel(Resources.of(sweetPotatoes, "stage_0"), Models.BLOCK_CROP(
				Resources.ofTexture(sweetPotatoes, "stage_0")
				));
		MCF.makeBlockModel(Resources.of(sweetPotatoes, "stage_1"), Models.BLOCK_CROP(
				Resources.ofTexture(sweetPotatoes, "stage_1")
				));
		MCF.makeBlockModel(Resources.of(sweetPotatoes, "stage_2"), Models.BLOCK_CROP(
				Resources.ofTexture(sweetPotatoes, "stage_2")
				));
		MCF.makeBlockModel(Resources.of(sweetPotatoes, "stage_3"), Models.BLOCK_CROP(
				Resources.ofTexture(sweetPotatoes, "stage_3")
				));
		MCF.makeVariant(Resources.of(sweetPotatoes), Variants.CROP(
				Resources.ofModel(sweetPotatoes), 4
				));

		super.Munge();
	}
}