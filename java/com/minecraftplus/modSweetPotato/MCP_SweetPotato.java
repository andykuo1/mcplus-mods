package com.minecraftplus.modSweetPotato;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.item.ItemFoodstuff;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_SweetPotato.MODBASE, name = "MC+ " + MCP_SweetPotato.MODBASE, version = "1.0.1")
public class MCP_SweetPotato extends MCP
{
	protected static final String MODBASE = "SweetPotato";

	@Instance("MCP_" + MCP_SweetPotato.MODBASE)
	public static MCP_SweetPotato INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Block sweetPotatoes = new BlockSweetPotato().setBlockName("sweet_potatoes");

	public static final Item sweetPotato = new ItemSweetPotato(4, 0.2F).setUnlocalizedName("sweet_potato");
	public static final Item bakedSweetPotato = new ItemFoodstuff(8, 0.8F).setUnlocalizedName("baked_sweet_potato");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.2");

		ItemRegistry.add(sweetPotatoes);
		ItemRegistry.add(sweetPotato);
		ItemRegistry.add(bakedSweetPotato);

		ItemRegistry.addDict(sweetPotato, "cropSweetPotato");
		ItemRegistry.addDict(sweetPotatoes, "cropSweetPotato");
		ItemRegistry.addDict(bakedSweetPotato, "foodBakedSweetPotato");

		MinecraftForge.EVENT_BUS.register(new EventSweetPotatoDropHandler());

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