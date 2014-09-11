package com.minecraftplus.modSatchel;

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

@Mod(modid = MCP.D + MCP_Satchel.MODBASE, name = MCP.PRE + MCP_Satchel.MODBASE, version = "1.3.2", dependencies = MCP.DEPENDENCY)
public class MCP_Satchel implements MCPMod
{
	protected static final String MODBASE = "Satchel";

	@Instance(MCP.D + MCP_Satchel.MODBASE)
	public static MCP_Satchel INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item satchel = new ItemSatchel().setUnlocalizedName("satchel");
	public static final Item enderSatchel = new ItemEnderSatchel().setUnlocalizedName("ender_satchel");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(satchel);
		ItemRegistry.add(enderSatchel);

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