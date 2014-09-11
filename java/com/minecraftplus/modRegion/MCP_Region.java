package com.minecraftplus.modRegion;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.LanguageRegistry;
import com.minecraftplus._common.config.GuiConfigHandler;
import com.minecraftplus._common.config.IConfigMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Region.MODBASE, name = MCP.PRE + MCP_Region.MODBASE, version = "1.2.0", dependencies = MCP.DEPENDENCY, guiFactory = MCP.A + MCP_Region.MODBASE + ".GuiFactory")
public class MCP_Region implements MCPMod, IConfigMod
{
	protected static final String MODBASE = "Region";

	@Instance(MCP.D + MCP_Region.MODBASE)
	public static MCP_Region INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static GuiConfigHandler configHandler;
	public static int x, y, scale;

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		BiomeGenBase[] biomes = BiomeGenBase.getBiomeGenArray();
		for (BiomeGenBase biome : biomes)
		{
			if (biome != null)
			{
				LanguageRegistry.add("biome." + LanguageRegistry.getNameUnlocal(biome.biomeName), LanguageRegistry.getNameReadable(biome.biomeName));
			}
		}

		LanguageRegistry.add("world.the_nether", "The Nether");
		LanguageRegistry.add("world.the_end", "The End");
		LanguageRegistry.add("world.new_world", "New World");

		configHandler = new GuiConfigHandler(new Configuration(par1Event.getSuggestedConfigurationFile()), this, MCP.D + MODBASE);

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

	@Override
	public void config(Configuration par1Configuration)
	{
		x = par1Configuration.getInt("Position X", Configuration.CATEGORY_GENERAL, 0, -1, 1, "");
		y = par1Configuration.getInt("Position Y", Configuration.CATEGORY_GENERAL, 0, -1, 1, "");
		scale = par1Configuration.getInt("Text Scale", Configuration.CATEGORY_GENERAL, 55, 1, 100, "");
	}
}