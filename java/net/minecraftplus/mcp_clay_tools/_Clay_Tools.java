package net.minecraftplus.mcp_clay_tools;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
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

@Mod(modid = _Clay_Tools.MODID, version = _Clay_Tools.VERSION, dependencies = "required-after:mcp_api")
public class _Clay_Tools extends _Mod
{
	public static final String MODID = "mcp_clay_tools";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Clay_Tools INSTANCE;

	public _Clay_Tools() {}

	//NAME, BLOCKLEVEL, DURABILITY, SPEED, DAMAGE, ENCHANTABLITY
	public static ToolMaterial toolClay = EnumHelper.addToolMaterial("HARDENED_CLAY", 1, 104, 4.5F, 1.2F, 4);
	public static final Item swordClay = new ItemSwordClay(toolClay).setUnlocalizedName("clay_sword");
	public static final Item shovelClay = new ItemSpadeClay(toolClay).setUnlocalizedName("clay_shovel");
	public static final Item axeClay = new ItemAxeClay(toolClay).setUnlocalizedName("clay_axe");
	public static final Item hoeClay = new ItemHoeClay(toolClay).setUnlocalizedName("clay_hoe");
	public static final Item pickaxeClay = new ItemPickaxeClay(toolClay).setUnlocalizedName("clay_pickaxe");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(swordClay);
		MCP.item(shovelClay);
		MCP.item(axeClay);
		MCP.item(hoeClay);
		MCP.item(pickaxeClay);

		toolClay.setRepairItem(new ItemStack(Blocks.hardened_clay));

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.recipe(Recipes.SWORD(new ItemStack(swordClay), Blocks.hardened_clay, Items.stick));
		MCP.recipe(new RecipesSwordClay(1, 3, swordClay,
				'X',
				'X',
				'#'));

		MCP.recipe(Recipes.SHOVEL(new ItemStack(shovelClay), Blocks.hardened_clay, Items.stick));
		MCP.recipe(new RecipesToolClay(1, 3, shovelClay,
				'X',
				'#',
				'#'));

		MCP.recipe(Recipes.AXE(new ItemStack(axeClay), Blocks.hardened_clay, Items.stick));
		MCP.recipe(new RecipesToolClay(2, 3, axeClay,
				'X', 'X',
				'X', '#',
				' ', '#'));

		MCP.recipe(Recipes.HOE(new ItemStack(hoeClay), Blocks.hardened_clay, Items.stick));
		MCP.recipe(new RecipesHoeClay(2, 3, hoeClay,
				'X', 'X',
				' ', '#',
				' ', '#'));

		MCP.recipe(Recipes.PICKAXE(new ItemStack(pickaxeClay), Blocks.hardened_clay, Items.stick));
		MCP.recipe(new RecipesToolClay(3, 3, pickaxeClay,
				'X', 'X', 'X',
				' ', '#', ' ',
				' ', '#', ' '));

		MCP.recipe(new RecipesSwordClayDyeable(swordClay));
		MCP.recipe(new RecipesToolClayDyeable(shovelClay));
		MCP.recipe(new RecipesToolClayDyeable(axeClay));
		MCP.recipe(new RecipesHoeClayDyeable(hoeClay));
		MCP.recipe(new RecipesToolClayDyeable(pickaxeClay));

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
		MCF.makeItemModel(Resources.of(swordClay), Models.ITEM_TOOLS(
				Resources.ofTexture(swordClay, "overlay"),
				Resources.ofTexture(swordClay)));
		MCF.makeItemModel(Resources.of(shovelClay), Models.ITEM_TOOLS(
				Resources.ofTexture(shovelClay, "overlay"),
				Resources.ofTexture(shovelClay)));
		MCF.makeItemModel(Resources.of(axeClay), Models.ITEM_TOOLS(
				Resources.ofTexture(axeClay, "overlay"),
				Resources.ofTexture(axeClay)));
		MCF.makeItemModel(Resources.of(hoeClay), Models.ITEM_TOOLS(
				Resources.ofTexture(hoeClay, "overlay"),
				Resources.ofTexture(hoeClay)));
		MCF.makeItemModel(Resources.of(pickaxeClay), Models.ITEM_TOOLS(
				Resources.ofTexture(pickaxeClay, "overlay"),
				Resources.ofTexture(pickaxeClay)));

		super.Munge();
	}
}