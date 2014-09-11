package com.minecraftplus.modGrainMix;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._common.item.ItemFoodstuff;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_GrainMix.MODBASE, name = MCP.PRE + MCP_GrainMix.MODBASE, version = "1.2.1", dependencies = MCP.DEPENDENCY)
public class MCP_GrainMix implements MCPMod
{
	protected static final String MODBASE = "GrainMix";

	@Instance(MCP.D + MCP_GrainMix.MODBASE)
	public static MCP_GrainMix INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item grainMix = new ItemFoodstuff(5, 0.2F, false).setReturnItem(Items.bowl).setMaxStackSize(1).setUnlocalizedName("grain_mix");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(grainMix);

		ItemRegistry.addDict(grainMix, "foodGrainMix");

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