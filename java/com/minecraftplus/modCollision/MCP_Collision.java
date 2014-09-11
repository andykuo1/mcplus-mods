package com.minecraftplus.modCollision;

import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.LanguageRegistry;
import com.minecraftplus._base.registry.ModRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Collision.MODBASE, name = MCP.PRE + MCP_Collision.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_Collision implements MCPMod
{
	protected static final String MODBASE = "Collision";

	@Instance(MCP.D + MCP_Collision.MODBASE)
	public static MCP_Collision INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final DamageSource crashMinecart = new DamageSourceMinecart();

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ModRegistry.addEventHandler(MinecraftForge.EVENT_BUS, new EventMinecartCollisionHandler());
		LanguageRegistry.add("death.attack.minecart", "%1$s was hit by a speeding Minecart");

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