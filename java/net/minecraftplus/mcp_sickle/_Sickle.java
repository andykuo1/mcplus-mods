package net.minecraftplus.mcp_sickle;

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
import net.minecraftplus._api.dictionary.Resources;

@Mod(modid = _Sickle.MODID, version = _Sickle.VERSION, dependencies = "required-after:mcp_api")
public class _Sickle extends _Mod
{
	public static final String MODID = "mcp_sickle";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Sickle INSTANCE;

	public _Sickle() {}

	public static EventHandlerSickle eventHandler = new EventHandlerSickle();

	public static final Item sickle = new ItemSickle().setUnlocalizedName("sickle");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.eventHandler(eventHandler);

		MCP.item(sickle);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		GameRegistry.addShapedRecipe(new ItemStack(sickle, 1),
				"X ",
				" X",
				"# ",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(sickle, 1),
				" X",
				"X ",
				" #",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.iron_ingot);

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
		MCF.makeItemModel(Resources.of(sickle), Models.ITEM_BASE(
				Resources.ofTexture(sickle)));

		super.Munge();
	}
}