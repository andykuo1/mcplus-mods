package net.minecraftplus.mcp_satchel;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Recipes;
import net.minecraftplus._api.dictionary.Resources;
import net.minecraftplus._api.factory.ModelFactory;

@Mod(modid = _Satchel.MODID, version = _Satchel.VERSION, dependencies = "required-after:mcp_api")
public class _Satchel extends _Mod
{
	public static final String MODID = "mcp_satchel";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Satchel INSTANCE;

	public _Satchel() {}

	public static final Item satchel = new ItemSatchel().setUnlocalizedName("satchel");
	public static final Item enderSatchel = new ItemEnderSatchel().setUnlocalizedName("ender_satchel");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(satchel);
		MCP.item(enderSatchel);

		MCP.guiHandler(new GuiHandlerSatchel());

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		GameRegistry.addShapedRecipe(new ItemStack(satchel),
				"###",
				"YXY",
				"###",
				Character.valueOf('#'), Items.leather,
				Character.valueOf('X'), Blocks.chest,
				Character.valueOf('Y'), Items.string);

		GameRegistry.addShapedRecipe(new ItemStack(enderSatchel),
				"###",
				"YXY",
				"###",
				Character.valueOf('#'), Items.leather,
				Character.valueOf('X'), Blocks.ender_chest,
				Character.valueOf('Y'), Items.string);

		MCP.recipe(Recipes.DYEABLE(satchel));
		MCP.recipe(Recipes.DYEABLE(enderSatchel));

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
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(satchel) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(satchel)
				).toJSON());
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(enderSatchel) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(enderSatchel),
				Resources.ofTexture(enderSatchel, "overlay")
				).toJSON());

		super.Munge();
	}
}