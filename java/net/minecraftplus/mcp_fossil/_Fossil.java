package net.minecraftplus.mcp_fossil;

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
import net.minecraftplus._api.dictionary.Dimensions;
import net.minecraftplus._api.dictionary.Models;
import net.minecraftplus._api.dictionary.Resources;
import net.minecraftplus._api.dictionary.Variants;
import net.minecraftplus._api.minecraft.base.WorldGenOreBase;

@Mod(modid = _Fossil.MODID, version = _Fossil.VERSION, dependencies = "required-after:mcp_api")
public class _Fossil extends _Mod
{
	public static final String MODID = "mcp_fossil";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Fossil INSTANCE;

	public _Fossil() {}

	public static final Block fossilStone = new BlockFossilStone().setHardness(3.5F).setResistance(6.0F).setStepSound(Block.soundTypeStone).setUnlocalizedName("fossil_stone");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.block(fossilStone);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		GameRegistry.registerWorldGenerator(new WorldGenOreBase(fossilStone.getDefaultState(), 4, 6, Dimensions.SURFACE).setSpawnHeight(70), 10);

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
		//TODO: Test new MCF functions
		MCF.makeBlockModel(Resources.of(fossilStone), Models.BLOCK_MULTI_TEXTURE(
				Resources.ofTexture(fossilStone, "side"),
				Resources.ofTexture(fossilStone, "bottom"),
				Resources.ofTexture(fossilStone, "top"),
				Resources.ofTexture(fossilStone, "side"),
				Resources.ofTexture(fossilStone, "side"),
				Resources.ofTexture(fossilStone, "side"),
				Resources.ofTexture(fossilStone, "side")
				));
		MCF.makeItemModel(Resources.of(fossilStone), Models.ITEM_BLOCK(
				Resources.ofModelParent(fossilStone)));
		MCF.makeVariant(Resources.of(fossilStone), Variants.NORMAL(
				Resources.ofModel(fossilStone)
				));
		super.Munge();
	}
}