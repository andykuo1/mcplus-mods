package com.minecraftplus.modGiftBox;

import java.awt.Color;

import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_GiftBox.MODBASE, name = "MC+ " + MCP_GiftBox.MODBASE, version = "1.2.0")
public class MCP_GiftBox extends MCP
{
	protected static final String MODBASE = "GiftBox";

	@Instance("MCP_" + MCP_GiftBox.MODBASE)
	public static MCP_GiftBox INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Item giftBox = new ItemGiftBox().setUnlocalizedName("gift_box");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.2");

		ItemRegistry.add(giftBox);

		proxy.register(Registry.RENDER);
		proxy.register(Registry.ENTITY);
		proxy.register(Registry.CUSTOM_ENTITY);
	}

	@EventHandler
	@Override
	public void loadInit(FMLInitializationEvent par1Event)
	{
		MCP.initEvent(par1Event);

		proxy.register(Registry.RECIPE);
	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent par1Event)
	{
		
	}

	public static int getColorWithGoldenRatio(int parHexColor, float parSaturation, float parBrightness)
	{
		float goldenRatioConj = 0.618033988749895F;
		float hue = 1F - ((float) parHexColor / 0xFFFFFF) + 0.6F;
		hue += goldenRatioConj;
		hue %= 1;
		return Color.HSBtoRGB(hue, parSaturation, parBrightness);
	}
}