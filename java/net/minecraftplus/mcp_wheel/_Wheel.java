package net.minecraftplus.mcp_wheel;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
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

@Mod(modid = _Wheel.MODID, version = _Wheel.VERSION, dependencies = "required-after:mcp_api")
public class _Wheel extends _Mod
{
	public static final String MODID = "mcp_wheel";
	public static final String VERSION = "1.0.0";

	@SidedProxy(serverSide="net.minecraftplus." + MODID + "._CommonProxy", clientSide="net.minecraftplus." + MODID + "._ClientProxy")
	public static _CommonProxy proxy;

	@Instance(MODID)
	public static _Wheel INSTANCE;

	public _Wheel() {}

	public static boolean USE_SLIME = true;
	
	public static Item wheel = new Item().setUnlocalizedName("wooden_wheel");

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		MCP.item(wheel);

		super.PreInitialize(parEvent);
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		GameRegistry.addRecipe(new ItemStack(wheel),
				"###",
				"#X#",
				"###",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), USE_SLIME ? Items.slime_ball : Items.clay_ball);

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
		Property propSlime = parConfiguration.get("GENERAL", "useSlime", true);
		
		USE_SLIME = propSlime.getBoolean();
		
		super.Configure(parConfiguration);
	}

	@Override
	public void Munge()
	{
		ModelFactory.write(MCF.itemModelDirectory(MODID), Resources.of(wheel) + ".json", Models.ITEM_BASE(
				Resources.ofTexture(wheel)
				).toJSON());

		super.Munge();
	}
}