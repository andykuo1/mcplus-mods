package net.minecraftplus.mcp_ice_box;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
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

@Mod(modid = _Ice_Box.MODID, version = _Ice_Box.VERSION, dependencies = "required-after:mcp_api;required-after:mcp_rotten")
public class _Ice_Box extends _Mod
{
	public static final String MODID = "mcp_ice_box";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Ice_Box INSTANCE;

	public _Ice_Box() {}

	public static Block iceBox = new BlockIceBox().setUnlocalizedName("ice_box");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.lang("container.ice_box", "Ice Box");

		MCP.block(iceBox);

		MCP.guiHandler(new GuiHandlerIceBox());
		MCP.eventHandler(new EventHandlerIceBox());

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		GameRegistry.addRecipe(new ItemStack(iceBox, 1, 0),
				"###", "#X# ","###",
				Character.valueOf('#'), Blocks.planks,
				Character.valueOf('X'), Blocks.ice);

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
		MCF.makeBlockModel(Resources.of(iceBox), Models.BLOCK_BASE(
				Resources.ofTexture(iceBox)));
		
		super.Munge();
	}
}