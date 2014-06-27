package com.minecraftplus.modClayTools;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_ClayTools.MODBASE, name = "MC+ " + MCP_ClayTools.MODBASE, version = "1.1.1")
public class MCP_ClayTools extends MCP
{
	protected static final String MODBASE = "ClayTools";

	@Instance("MCP_" + MCP_ClayTools.MODBASE)
	public static MCP_ClayTools INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	//NAME, BLOCKLEVEL, DURABILITY, SPEED, DAMAGE, ENCHANTABLITY
	public static ToolMaterial toolClay = EnumHelper.addToolMaterial("CLAY", 1, 104, 4.5F, 1.2F, 4);
	public static final Item swordClay = new ItemSwordClay(toolClay).setUnlocalizedName("clay_sword");
	public static final Item shovelClay = new ItemSpadeClay(toolClay).setUnlocalizedName("clay_shovel");
	public static final Item pickaxeClay = new ItemPickaxeClay(toolClay).setUnlocalizedName("clay_pickaxe");
	public static final Item axeClay = new ItemAxeClay(toolClay).setUnlocalizedName("clay_axe");
	public static final Item hoeClay = new ItemHoeClay(toolClay).setUnlocalizedName("clay_hoe");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.0");

		ItemRegistry.add(swordClay);
		ItemRegistry.add(shovelClay);
		ItemRegistry.add(pickaxeClay);
		ItemRegistry.add(axeClay);
		ItemRegistry.add(hoeClay);

		Registry.addRepairMaterial(toolClay, Items.clay_ball);

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