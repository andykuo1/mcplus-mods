package com.minecraftplus.modGlowingSkull;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.LanguageRegistry;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_GlowingSkull.MODBASE, name = "MC+ " + MCP_GlowingSkull.MODBASE, version = "1.0.0")
public class MCP_GlowingSkull extends MCP
{
	protected static final String MODBASE = "GlowingSkull";

	@Instance("MCP_" + MCP_GlowingSkull.MODBASE)
	public static MCP_GlowingSkull INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Block glowingSkulls = new BlockGlowingSkull().setBlockName("glowing_skulls");
	public static final Block redstoneGlowingSkulls = new BlockRedstoneGlowingSkull().setBlockName("glowing_redstone_skulls");
	public static final Item glowingSkull = new ItemGlowingSkull().setUnlocalizedName("glowing_skull");
	public static final Item redstoneGlowingSkull = new ItemRedstoneGlowingSkull().setUnlocalizedName("glowing_redstone_skull");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.1");

		ItemRegistry.add(glowingSkulls);
		ItemRegistry.add(redstoneGlowingSkulls);
		ItemRegistry.add(glowingSkull);
		ItemRegistry.add(redstoneGlowingSkull);

		LanguageRegistry.add("item.glowing_skull.skeleton.name", "Glowing Skeleton Skull");
		LanguageRegistry.add("item.glowing_skull.wither.name", "Glowing Wither Skeleton Skull");
		LanguageRegistry.add("item.glowing_skull.zombie.name", "Glowing Zombie Skull");
		LanguageRegistry.add("item.glowing_skull.char.name", "Glowing Skull");
		LanguageRegistry.add("item.glowing_skull.creeper.name", "Glowing Creeper Skull");
		
		LanguageRegistry.add("item.glowing_redstone_skull.skeleton.name", "Glowing Skeleton Redstone Skull");
		LanguageRegistry.add("item.glowing_redstone_skull.wither.name", "Glowing Wither Skeleton Redstone Skull");
		LanguageRegistry.add("item.glowing_redstone_skull.zombie.name", "Glowing Zombie Redstone Skull");
		LanguageRegistry.add("item.glowing_redstone_skull.char.name", "Glowing Redstone Skull");
		LanguageRegistry.add("item.glowing_redstone_skull.creeper.name", "Glowing Creeper Redstone Skull");

		proxy.register(Registry.RENDER);
		proxy.register(Registry.ENTITY);
		proxy.register(Registry.CUSTOM_ENTITY);
	}

	@EventHandler
	@Override
	public void loadInit(FMLInitializationEvent par1Event)
	{
		MCP.initEvent(par1Event);

		proxy.register(Registry.RECIPE);
	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent par1Event)
	{

	}
}