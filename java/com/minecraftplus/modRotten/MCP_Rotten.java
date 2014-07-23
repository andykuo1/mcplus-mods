package com.minecraftplus.modRotten;

import java.util.Iterator;
import java.util.List;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "MCP_" + MCP_Rotten.MODBASE, name = "MC+ " + MCP_Rotten.MODBASE, version = "1.0.2")
public class MCP_Rotten extends MCP
{
	protected static final String MODBASE = "Rotten";

	@Instance("MCP_" + MCP_Rotten.MODBASE)
	public static MCP_Rotten INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.2");

		MinecraftForge.EVENT_BUS.register(new EventFoodDecayHandler());

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
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

		for(IRecipe recipe : recipes)
		{
			if (recipe.getRecipeOutput() != null && recipe.getRecipeOutput().getItem() instanceof ItemFood)
			{
				EventFoodDecayHandler.addCraftedFood(recipe.getRecipeOutput().getItem());
			}
		}

		Iterator iter = MCP.getFurnaceRecipes().keySet().iterator();
		while (iter.hasNext())
		{
			ItemStack itemstack = MCP.getFurnaceRecipes().get(iter.next());
			if (itemstack != null && itemstack.getItem() instanceof ItemFood)
			{
				EventFoodDecayHandler.addCookedFood(itemstack.getItem());
			}
		}
	}
}