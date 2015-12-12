package net.minecraftplus.mcp_torch;

import net.minecraft.block.Block;
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
import net.minecraftplus._api.dictionary.Variants;

@Mod(modid = _Torch.MODID, version = _Torch.VERSION, dependencies = "required-after:mcp_api")
public class _Torch extends _Mod
{
	public static final String MODID = "mcp_torch";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Torch INSTANCE;

	public _Torch() {}

	public static final Block litTorch = new BlockTorchFire(true).setUnlocalizedName("lit_torch");
	public static final Block unlitTorch = new BlockTorchFire(false).setUnlocalizedName("unlit_torch");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.block(litTorch);
		MCP.block(unlitTorch);

		MCP.eventHandler(new EventHandlerTorch());

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
		MCF.makeBlockModel(Resources.of(litTorch), Models.BLOCK(
				"block/torch",
				new String[] {"torch", "blocks/torch_on"}));
		MCF.makeBlockModel(Resources.of(litTorch, "wall"), Models.BLOCK(
				"block/torch_wall",
				new String[] {"torch", "blocks/torch_on"}));
		MCF.makeItemModel(Resources.of(litTorch), Models.ITEM_BASE(
				"blocks/torch_on"));
		MCF.makeVariant(Resources.of(litTorch), Variants.DIRECTIONAL(
				new Object[]{Resources.ofModel(litTorch), 0, 0, 0},
				new Object[]{Resources.ofModel(litTorch), 0, 0, 0},
				new Object[]{Resources.ofModel(litTorch, "wall"), 0, 270, 0},
				new Object[]{Resources.ofModel(litTorch, "wall"), 0, 90, 0},
				new Object[]{Resources.ofModel(litTorch, "wall"), 0, 180, 0},
				new Object[]{Resources.ofModel(litTorch, "wall"), 0, 0, 0}));

		MCF.makeBlockModel(Resources.of(unlitTorch), Models.BLOCK(
				"block/torch",
				new String[] {"torch", Resources.ofTexture(unlitTorch)}));
		MCF.makeBlockModel(Resources.of(unlitTorch, "wall"), Models.BLOCK(
				"block/torch_wall",
				new String[] {"torch", Resources.ofTexture(unlitTorch)}));
		MCF.makeItemModel(Resources.of(unlitTorch), Models.ITEM_BASE(
				Resources.ofTexture(unlitTorch)));
		MCF.makeVariant(Resources.of(unlitTorch), Variants.DIRECTIONAL(
				new Object[]{Resources.ofModel(unlitTorch), 0, 0, 0},
				new Object[]{Resources.ofModel(unlitTorch), 0, 0, 0},
				new Object[]{Resources.ofModel(unlitTorch, "wall"), 0, 270, 0},
				new Object[]{Resources.ofModel(unlitTorch, "wall"), 0, 90, 0},
				new Object[]{Resources.ofModel(unlitTorch, "wall"), 0, 180, 0},
				new Object[]{Resources.ofModel(unlitTorch, "wall"), 0, 0, 0}));

		super.Munge();
	}
}