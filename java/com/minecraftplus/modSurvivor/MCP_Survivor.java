package com.minecraftplus.modSurvivor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.ItemRegistry;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.item.ItemBase;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_Survivor.MODBASE, name = "MC+ " + MCP_Survivor.MODBASE, version = "1.0.0")
public class MCP_Survivor extends MCP
{
	protected static final String MODBASE = "Survivor";

	@Instance("MCP_" + MCP_Survivor.MODBASE)
	public static MCP_Survivor INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	//NAME, BLOCKLEVEL, DURABILITY, SPEED, DAMAGE, ENCHANTABLITY
	public static ToolMaterial toolFlint = EnumHelper.addToolMaterial("FLINT", 0, 34, 2.6F, 0.6F, 10);
	public static final Item hatchet = new ItemHatchet(toolFlint).setUnlocalizedName("hatchet");
	public static final Item dagger = new ItemDagger(toolFlint).setUnlocalizedName("dagger");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.2");

		ItemRegistry.add(hatchet);
		ItemRegistry.add(dagger);

		Registry.addRepairMaterial(toolFlint, Items.flint);

		Registry.addEventHandler(MinecraftForge.EVENT_BUS, new EventResourceStartHandler());

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
		Object[] objs = CraftingManager.getInstance().getRecipeList().toArray();
		for(Object obj : objs)
		{
			IRecipe recipe = (IRecipe) obj;
			if (recipe.getRecipeOutput() != null)
			{
				if (recipe.getRecipeOutput().getItem() == Item.getItemFromBlock(Blocks.planks))
				{
					CraftingManager.getInstance().getRecipeList().remove(recipe);
				}
			}
		}
	}
}