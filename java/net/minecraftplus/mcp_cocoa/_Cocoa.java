package net.minecraftplus.mcp_cocoa;

import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
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
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Recipes;
import net.minecraftplus._api.dictionary.Resources;
import net.minecraftplus._api.factory.ModelFactory;

@Mod(modid = _Cocoa.MODID, version = _Cocoa.VERSION, dependencies = "required-after:mcp_api")
public class _Cocoa extends _Mod
{
	public static final String MODID = "mcp_cocoa";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Cocoa INSTANCE;

	public _Cocoa() {}

	public static final Item cocoaBar = new ItemFood(4, 0.2F, false).setUnlocalizedName("cocoa_bar");
	public static final Item cocoaPie = new ItemFood(7, 0.5F, false).setUnlocalizedName("cocoa_pie");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(cocoaBar);
		MCP.item(cocoaPie);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		GameRegistry.addRecipe(new ItemStack(cocoaBar, 1),
				"###",
				"###",
				Character.valueOf('#'), new ItemStack(Items.dye, 1, EnumDyeColor.BROWN.getDyeDamage()));

		MCP.recipe(Recipes.CONVERT(new ItemStack(Items.dye, 6, EnumDyeColor.BROWN.getDyeDamage()),
				cocoaBar));
		MCP.recipe(Recipes.SHAPELESS(new ItemStack(cocoaPie),
				cocoaBar, cocoaBar, Items.egg, Items.sugar));

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
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(cocoaBar) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(cocoaBar)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(cocoaPie) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(cocoaPie)
				).toJSON());

		super.Munge();
	}
}