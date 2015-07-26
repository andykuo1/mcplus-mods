package net.minecraftplus.mcp_quartz;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftplus._api.MCF;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.dictionary.Assets;
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Recipes;
import net.minecraftplus._api.dictionary.Resources;

@Mod(modid = _Quartz.MODID, version = _Quartz.VERSION, dependencies = "required-after:mcp_api")
public class _Quartz extends _Mod
{
	public static final String MODID = "mcp_quartz";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Quartz INSTANCE;

	public _Quartz() {}

	//NAME, BLOCKLEVEL, DURABILITY, SPEED, DAMAGE, ENCHANTABLITY
	public static ToolMaterial toolQuartz = EnumHelper.addToolMaterial("QUARTZ", 0, 342, 3.0F, 2.0F, 4);
	public static final Item swordQuartz = new ItemSwordQuartz(toolQuartz).setUnlocalizedName("quartz_sword");
	public static final Item shovelQuartz = new ItemSpadeQuartz(toolQuartz).setUnlocalizedName("quartz_shovel");
	public static final Item pickaxeQuartz = new ItemPickaxeQuartz(toolQuartz).setUnlocalizedName("quartz_pickaxe");
	public static final Item axeQuartz = new ItemAxeQuartz(toolQuartz).setUnlocalizedName("quartz_axe");
	public static final Item hoeQuartz = new ItemHoeQuartz(toolQuartz).setUnlocalizedName("quartz_hoe");

	//NAME, DEFENSE, DAMAGE-SPREAD, ENCHANTABILITY
	public static ArmorMaterial armorQuartz = EnumHelper.addArmorMaterial("QUARTZ", Assets.resource(MODID, "quartz_armor"), 13, new int[] { 3, 4, 4, 2 }, 10);
	public static final Item helmetQuartz = new ItemArmorQuartz(armorQuartz, 1, 0).setUnlocalizedName("quartz_helmet");
	public static final Item plateQuartz = new ItemArmorQuartz(armorQuartz, 1, 1).setUnlocalizedName("quartz_chestplate");
	public static final Item legsQuartz = new ItemArmorQuartz(armorQuartz, 1, 2).setUnlocalizedName("quartz_leggings");
	public static final Item bootsQuartz = new ItemArmorQuartz(armorQuartz, 1, 3).setUnlocalizedName("quartz_boots");

	//TODO: Test in server with more than one person at digging speed

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(swordQuartz);
		MCP.item(shovelQuartz);
		MCP.item(pickaxeQuartz);
		MCP.item(axeQuartz);
		MCP.item(hoeQuartz);
		MCP.item(helmetQuartz);
		MCP.item(plateQuartz);
		MCP.item(legsQuartz);
		MCP.item(bootsQuartz);

		toolQuartz.setRepairItem(new ItemStack(Items.quartz));
		armorQuartz.customCraftingMaterial = Items.quartz;

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.recipe(Recipes.CONVERT(new ItemStack(Items.quartz, 4),
				Blocks.quartz_block));

		GameRegistry.addShapelessRecipe(new ItemStack(Items.quartz, 4), Blocks.quartz_block);

		MCP.recipe(Recipes.SWORD(new ItemStack(swordQuartz),
				Blocks.quartz_block,
				Items.stick));
		MCP.recipe(Recipes.SHOVEL(new ItemStack(shovelQuartz),
				Blocks.quartz_block,
				Items.stick));
		MCP.recipe(Recipes.AXE(new ItemStack(axeQuartz),
				Blocks.quartz_block,
				Items.stick));
		MCP.recipe(Recipes.HOE(new ItemStack(hoeQuartz),
				Blocks.quartz_block,
				Items.stick));
		MCP.recipe(Recipes.PICKAXE(new ItemStack(pickaxeQuartz),
				Blocks.quartz_block,
				Items.stick));

		MCP.recipe(Recipes.HELMET(new ItemStack(helmetQuartz),
				Blocks.quartz_block));
		MCP.recipe(Recipes.CHESTPLATE(new ItemStack(plateQuartz),
				Blocks.quartz_block));
		MCP.recipe(Recipes.LEGGINGS(new ItemStack(legsQuartz),
				Blocks.quartz_block));
		MCP.recipe(Recipes.BOOTS(new ItemStack(bootsQuartz),
				Blocks.quartz_block));

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
		MCF.makeItemModel(Resources.of(swordQuartz), Models.ITEM_TOOLS(
				Resources.ofTexture(swordQuartz)
				));
		MCF.makeItemModel(Resources.of(shovelQuartz), Models.ITEM_TOOLS(
				Resources.ofTexture(shovelQuartz)
				));
		MCF.makeItemModel(Resources.of(axeQuartz), Models.ITEM_TOOLS(
				Resources.ofTexture(axeQuartz)
				));
		MCF.makeItemModel(Resources.of(hoeQuartz), Models.ITEM_TOOLS(
				Resources.ofTexture(hoeQuartz)
				));
		MCF.makeItemModel(Resources.of(pickaxeQuartz), Models.ITEM_TOOLS(
				Resources.ofTexture(pickaxeQuartz)
				));
		MCF.makeItemModel(Resources.of(helmetQuartz), Models.ITEM_HELMET_BOOTS(
				Resources.ofTexture(helmetQuartz)
				));
		MCF.makeItemModel(Resources.of(plateQuartz), Models.ITEM_BASE(
				Resources.ofTexture(plateQuartz)
				));
		MCF.makeItemModel(Resources.of(legsQuartz), Models.ITEM_BASE(
				Resources.ofTexture(legsQuartz)
				));
		MCF.makeItemModel(Resources.of(bootsQuartz), Models.ITEM_HELMET_BOOTS(
				Resources.ofTexture(bootsQuartz)
				));

		super.Munge();
	}
}