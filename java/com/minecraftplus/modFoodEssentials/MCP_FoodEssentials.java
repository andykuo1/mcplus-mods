package com.minecraftplus.modFoodEssentials;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionHelper;

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

@Mod(modid = MCP.D + MCP_FoodEssentials.MODBASE, name = MCP.PRE + MCP_FoodEssentials.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_FoodEssentials implements MCPMod
{
	protected static final String MODBASE = "FoodEssentials";

	@Instance("MCP_" + MCP_FoodEssentials.MODBASE)
	public static MCP_FoodEssentials INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Item salt = new ItemSalt().setUnlocalizedName("salt");

	public static final Item cabbageSeeds = new ItemCabbageSeeds().setUnlocalizedName("cabbage_seeds");

	public static final Item cabbage = new ItemFoodstuff(2, 0.4F).setUnlocalizedName("cabbage");
	public static final Block cabbages = new BlockCabbage().setBlockName("cabbage_top");
	public static final Block cabbagesBase = new BlockCabbageBase().setBlockName("cabbage_bottom");


	public static final Item rawSquid = new ItemFoodstuff(3, 0.3F).setPotionEffect(PotionHelper.spiderEyeEffect).setUnlocalizedName("raw_squid");
	public static final Item calamari = new ItemFoodstuff(8, 0.8F).setUnlocalizedName("calamari");

	public static final Item beefStew = new ItemFoodstuff(12, 1.0F).setUnlocalizedName("beef_stew");

	public static final Block saltOre = new BlockSaltOre().setBlockName("salt_ore");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(saltOre);
		ItemRegistry.add(salt);
		ItemRegistry.add(rawSquid);
		ItemRegistry.add(calamari);
		ItemRegistry.add(cabbage);
		ItemRegistry.add(cabbageSeeds);

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