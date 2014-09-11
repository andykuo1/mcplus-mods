package com.minecraftplus.modSulfur;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.WorldGenRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Sulfur.MODBASE, name = MCP.PRE + MCP_Sulfur.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_Sulfur implements MCPMod
{
	protected static final String MODBASE = "Sulfur";

	@Instance(MCP.D + MCP_Sulfur.MODBASE)
	public static MCP_Sulfur INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item sulfur = new ItemSulfur().setUnlocalizedName("sulfur");
	public static final Block sulfurOre = new BlockOreSulfur().setBlockName("sulfur_ore");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(sulfur);
		ItemRegistry.add(sulfurOre);

		ItemRegistry.addDict(sulfur, "materialSulfur");
		ItemRegistry.addDict(sulfurOre, "oreSulfur");

		WorldGenRegistry.add(new WorldGenBlockSulfur());

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