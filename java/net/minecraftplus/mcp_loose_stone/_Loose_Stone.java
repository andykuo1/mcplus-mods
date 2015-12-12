package net.minecraftplus.mcp_loose_stone;

import net.minecraft.block.Block;
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

@Mod(modid = _Loose_Stone.MODID, version = _Loose_Stone.VERSION, dependencies = "required-after:mcp_api")
public class _Loose_Stone extends _Mod
{
	public static final String MODID = "mcp_loose_stone";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Loose_Stone INSTANCE;

	public _Loose_Stone() {}

	public static final Block looseStone = new BlockLooseStone().setHardness(1F).setResistance(6F).setStepSound(Block.soundTypePiston).setUnlocalizedName("loose_stone");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.block(looseStone);

		GameRegistry.registerWorldGenerator(new WorldGenLooseStone(), 8);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
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
		MCF.makeBlockModel(Resources.of(looseStone), Models.BLOCK_BASE(
				"blocks/stone"));
		MCF.makeItemModel(Resources.of(looseStone), Models.ITEM_BLOCK(
				Resources.ofModelParent(looseStone)));
		MCF.makeVariant(Resources.of(looseStone), Variants.NORMAL(
				Resources.ofModel(looseStone)
				));

		super.Munge();
	}
}