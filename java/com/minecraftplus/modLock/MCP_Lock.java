package com.minecraftplus.modLock;

import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.ModRegistry;
import com.minecraftplus._base.registry.PacketRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Lock.MODBASE, name = MCP.PRE + MCP_Lock.MODBASE, version = "1.2.2", dependencies = MCP.DEPENDENCY)
public class MCP_Lock implements MCPMod
{
	protected static final String MODBASE = "Lock";

	@Instance(MCP.D + MCP_Lock.MODBASE)
	public static MCP_Lock INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item ironLock = new ItemIronLock().setUnlocalizedName("iron_lock");
	public static final Item lockpick = new ItemLockpick().setUnlocalizedName("lockpick");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(ironLock);
		ItemRegistry.add(lockpick);

		ModRegistry.addGuiHandler(this, new GuiHandler());
		PacketRegistry.add(PacketLockOpen.class);

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