package com.minecraftplus.modClayTools;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

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

@Mod(modid = MCP.D + MCP_ClayTools.MODBASE, name = MCP.PRE + MCP_ClayTools.MODBASE, version = "1.1.2", dependencies = MCP.DEPENDENCY)
public class MCP_ClayTools implements MCPMod
{
	protected static final String MODBASE = "ClayTools";

	@Instance(MCP.D + MCP_ClayTools.MODBASE)
	public static MCP_ClayTools INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

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
		ItemRegistry.add(swordClay);
		ItemRegistry.add(shovelClay);
		ItemRegistry.add(pickaxeClay);
		ItemRegistry.add(axeClay);
		ItemRegistry.add(hoeClay);

		ModRegistry.addRepairMaterial(toolClay, Items.clay_ball);

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