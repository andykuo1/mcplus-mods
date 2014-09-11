package com.minecraftplus.modCart;

import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.ModRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Cart.MODBASE, name = MCP.PRE + MCP_Cart.MODBASE, version = "1.3.0", dependencies = "required-after:MCP_Wheel")
public class MCP_Cart implements MCPMod
{
	protected static final String MODBASE = "Cart";

	@Instance(MCP.D + MCP_Cart.MODBASE)
	public static MCP_Cart INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item cart = new ItemCart().setUnlocalizedName("wooden_cart");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(cart);

		ModRegistry.addGuiHandler(this, new GuiHandler());

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
}