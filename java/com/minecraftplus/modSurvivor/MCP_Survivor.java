package com.minecraftplus.modSurvivor;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
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

@Mod(modid = MCP.D + MCP_Survivor.MODBASE, name = MCP.PRE + MCP_Survivor.MODBASE, version = "1.0.0", dependencies = MCP.DEPENDENCY)
public class MCP_Survivor implements MCPMod
{
	protected static final String MODBASE = "Survivor";

	@Instance("MCP_" + MCP_Survivor.MODBASE)
	public static MCP_Survivor INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	//NAME, BLOCKLEVEL, DURABILITY, SPEED, DAMAGE, ENCHANTABLITY
	public static ToolMaterial toolFlint = EnumHelper.addToolMaterial("FLINT", 0, 34, 2.6F, 0.6F, 10);
	public static final Item hatchet = new ItemHatchet(toolFlint).setUnlocalizedName("hatchet");
	public static final Item dagger = new ItemDagger(toolFlint).setUnlocalizedName("dagger");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(hatchet);
		ItemRegistry.add(dagger);

		ModRegistry.addRepairMaterial(toolFlint, Items.flint);

		proxy.register();
	}

	@EventHandler
	@Override
	public void mainInit(FMLInitializationEvent par1Event)
	{
		List<IRecipe> trashBin = new ArrayList<IRecipe>();
		List<Object> objects = CraftingManager.getInstance().getRecipeList();

		for(Object obj : objects)
		{
			IRecipe recipe = (IRecipe) obj;
			if (recipe.getRecipeOutput() != null)
			{
				if (recipe.getRecipeOutput().getItem() == Items.stick)
				{
					trashBin.add(recipe);
				}
			}
		}

		for(IRecipe recipe : trashBin)
		{
			CraftingManager.getInstance().getRecipeList().remove(recipe);
		}
	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent par1Event)
	{

	}
}