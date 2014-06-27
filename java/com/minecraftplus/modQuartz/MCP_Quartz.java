package com.minecraftplus.modQuartz;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
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

@Mod(modid = "MCP_" + MCP_Quartz.MODBASE, name = "MC+ " + MCP_Quartz.MODBASE, version = "1.3.1")
public class MCP_Quartz extends MCP
{
	protected static final String MODBASE = "Quartz";

	@Instance("MCP_" + MCP_Quartz.MODBASE)
	public static MCP_Quartz INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	//NAME, BLOCKLEVEL, DURABILITY, SPEED, DAMAGE, ENCHANTABLITY
	public static ToolMaterial toolQuartz = EnumHelper.addToolMaterial("QUARTZ", 0, 342, 3.0F, 2.0F, 4);
	public static final Item swordQuartz = new ItemSwordQuartz(toolQuartz).setUnlocalizedName("quartz_sword");
	public static final Item shovelQuartz = new ItemSpadeQuartz(toolQuartz).setUnlocalizedName("quartz_shovel");
	public static final Item pickaxeQuartz = new ItemPickaxeQuartz(toolQuartz).setUnlocalizedName("quartz_pickaxe");
	public static final Item axeQuartz = new ItemAxeQuartz(toolQuartz).setUnlocalizedName("quartz_axe");
	public static final Item hoeQuartz = new ItemHoeQuartz(toolQuartz).setUnlocalizedName("quartz_hoe");

	//NAME, DEFENSE, DAMAGE-SPREAD, ENCHANTABILITY
	public static ArmorMaterial armorQuartz = EnumHelper.addArmorMaterial("QUARTZ", 13, new int[]{3, 4, 4, 2}, 10);
	public static final Item helmetQuartz = new ItemArmorQuartz(armorQuartz, 1, 0).setUnlocalizedName("quartz_helmet");
	public static final Item plateQuartz = new ItemArmorQuartz(armorQuartz, 1, 1).setUnlocalizedName("quartz_chestplate");
	public static final Item legsQuartz = new ItemArmorQuartz(armorQuartz, 1, 2).setUnlocalizedName("quartz_leggings");
	public static final Item bootsQuartz = new ItemArmorQuartz(armorQuartz, 1, 3).setUnlocalizedName("quartz_boots");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.0");

		ItemRegistry.add(swordQuartz);
		ItemRegistry.add(shovelQuartz);
		ItemRegistry.add(pickaxeQuartz);
		ItemRegistry.add(axeQuartz);
		ItemRegistry.add(hoeQuartz);
		ItemRegistry.add(helmetQuartz);
		ItemRegistry.add(plateQuartz);
		ItemRegistry.add(legsQuartz);
		ItemRegistry.add(bootsQuartz);

		Registry.addRepairMaterial(toolQuartz, Items.quartz);
		Registry.addRepairMaterial(armorQuartz, Items.quartz);

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