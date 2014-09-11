package com.minecraftplus.modMortar;

import net.minecraft.block.Block;
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

@Mod(modid = MCP.D + MCP_Mortar.MODBASE, name = MCP.PRE + MCP_Mortar.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_Mortar implements MCPMod
{
	protected static final String MODBASE = "Mortar";

	@Instance(MCP.D + MCP_Mortar.MODBASE)
	public static MCP_Mortar INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	//TODO: Fix shift-click in gui
	//TODO: Adjust mortar recipes
	//TODO: Redo mortar texture

	public static final Block mortar = new BlockMortar().setBlockName("mortar");
	public static final Item rottenCompost = new ItemRottenCompost().setUnlocalizedName("rotten_compost");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(mortar);
		ItemRegistry.add(rottenCompost);

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