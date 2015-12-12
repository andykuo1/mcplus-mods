package net.minecraftplus.mcp_loom;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
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
import net.minecraftplus._api.dictionary.Variants;
import net.minecraftplus.mcp_wheel._Wheel;

@Mod(modid = _Loom.MODID, version = _Loom.VERSION, dependencies = "required-after:mcp_api;required-after:mcp_wheel")
public class _Loom extends _Mod
{
	public static final String MODID = "mcp_loom";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Loom INSTANCE;

	public _Loom() {}

	public static final Block loom = new BlockLoom(false).setUnlocalizedName("loom");
	public static final Block workingLoom = new BlockLoom(true).setUnlocalizedName("working_loom");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.lang("container.loom", "Loom");

		MCP.guiHandler(new GuiHandlerLoom());

		MCP.block(loom);
		MCP.block(workingLoom);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		GameRegistry.addRecipe(new ItemStack(loom, 1, 0),
				"#X ","###",
				Character.valueOf('#'), Blocks.planks,
				Character.valueOf('X'), _Wheel.wheel);
		GameRegistry.addRecipe(new ItemStack(loom, 1, 0),
				" X#","###",
				Character.valueOf('#'), Blocks.planks,
				Character.valueOf('X'), _Wheel.wheel);

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
		MCF.makeBlockModel(Resources.of(loom), Models.BLOCK_BASE(
				"blocks/planks_oak"));
		MCF.makeItemModel(Resources.of(loom), Models.ITEM_BASE(
				Resources.ofTexture(Item.getItemFromBlock(loom))));
		MCF.makeVariant(Resources.of(loom), Variants.DIRECTIONAL(
				new Object[]{Resources.ofModel(loom), 0, 0, 0},
				new Object[]{Resources.ofModel(loom), 0, 0, 0},
				new Object[]{Resources.ofModel(loom), 0, 0, 0},
				new Object[]{Resources.ofModel(loom), 0, 180, 0},
				new Object[]{Resources.ofModel(loom), 0, 270, 0},
				new Object[]{Resources.ofModel(loom), 0, 90, 0}));

		MCF.makeItemModel(Resources.of(workingLoom), Models.ITEM_BASE(
				Resources.ofTexture(Item.getItemFromBlock(loom))));
		MCF.makeVariant(Resources.of(workingLoom), Variants.DIRECTIONAL(
				new Object[]{Resources.ofModel(loom), 0, 0, 0},
				new Object[]{Resources.ofModel(loom), 0, 0, 0},
				new Object[]{Resources.ofModel(loom), 0, 0, 0},
				new Object[]{Resources.ofModel(loom), 0, 180, 0},
				new Object[]{Resources.ofModel(loom), 0, 270, 0},
				new Object[]{Resources.ofModel(loom), 0, 90, 0}));

		super.Munge();
	}
}