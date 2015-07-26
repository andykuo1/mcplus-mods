package net.minecraftplus.mcp_clippers;

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
import net.minecraftplus._api.factory.ModelFactory;

@Mod(modid = _Clippers.MODID, version = _Clippers.VERSION, dependencies = "required-after:mcp_api")
public class _Clippers extends _Mod
{
	public static final String MODID = "mcp_clippers";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Clippers INSTANCE;

	public _Clippers() {}

	public static final Item clippers = new ItemClippers().setUnlocalizedName("clippers");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(clippers);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		GameRegistry.addShapedRecipe(new ItemStack(clippers, 1),
				" #",
				"# ",
				Character.valueOf('#'), new ItemStack(Items.gold_ingot));
		GameRegistry.addShapedRecipe(new ItemStack(clippers, 1),
				"# ",
				" #",
				Character.valueOf('#'), new ItemStack(Items.gold_ingot));

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
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(clippers) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(clippers)
				).toJSON());

		super.Munge();
	}
}