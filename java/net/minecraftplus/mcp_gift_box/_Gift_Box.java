package net.minecraftplus.mcp_gift_box;

import net.minecraft.item.Item;
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
import net.minecraftplus._api.dictionary.Resources;

@Mod(modid = _Gift_Box.MODID, version = _Gift_Box.VERSION, dependencies = "required-after:mcp_api")
public class _Gift_Box extends _Mod
{
	public static final String MODID = "mcp_gift_box";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Gift_Box INSTANCE;

	public _Gift_Box() {}

	public static Item giftBox = new ItemGiftBox().setUnlocalizedName("gift_box");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(giftBox);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		MCP.recipe(new RecipesGiftBox(3, 3, giftBox,
				'X', 'X', 'X',
				'X', '#', 'X',
				'X', 'X', 'X'));

		MCP.recipe(new RecipesGiftBoxDyeable((ItemGiftBox) giftBox));

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
		MCF.makeItemModel(Resources.of(giftBox), Models.ITEM_BASE(
				Resources.ofTexture(giftBox),
				Resources.ofTexture(giftBox, "overlay")));

		super.Munge();
	}
}