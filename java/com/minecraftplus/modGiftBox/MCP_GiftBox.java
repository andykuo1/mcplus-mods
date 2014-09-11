package com.minecraftplus.modGiftBox;

import java.awt.Color;

import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_GiftBox.MODBASE, name = MCP.PRE + MCP_GiftBox.MODBASE, version = "1.2.0", dependencies = MCP.DEPENDENCY)
public class MCP_GiftBox implements MCPMod
{
	protected static final String MODBASE = "GiftBox";

	@Instance(MCP.D + MCP_GiftBox.MODBASE)
	public static MCP_GiftBox INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item giftBox = new ItemGiftBox().setUnlocalizedName("gift_box");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(giftBox);

		proxy.register();
	}

	@EventHandler
	@Override
	public void mainInit(FMLInitializationEvent par1Event)
	{

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