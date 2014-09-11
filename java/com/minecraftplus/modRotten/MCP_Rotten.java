package com.minecraftplus.modRotten;

import java.util.List;

import net.minecraft.item.ItemFood;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Rotten.MODBASE, name = MCP.PRE + MCP_Rotten.MODBASE, version = "1.0.2", dependencies = MCP.DEPENDENCY)
public class MCP_Rotten implements MCPMod
{
	protected static final String MODBASE = "Rotten";

	@Instance(MCP.D + MCP_Rotten.MODBASE)
	public static MCP_Rotten INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MinecraftForge.EVENT_BUS.register(new EventFoodDecayHandler());

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
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

		for(IRecipe recipe : recipes)
		{
			if (recipe.getRecipeOutput() != null && recipe.getRecipeOutput().getItem() instanceof ItemFood)
			{
				EventFoodDecayHandler.addCraftedFood(recipe.getRecipeOutput().getItem());
			}
		}

		/*
		Iterator iter = MCPMod.getFurnaceRecipes().keySet().iterator();
		while (iter.hasNext())
		{
			ItemStack itemstack = MCPMod.getFurnaceRecipes().get(iter.next());
			if (itemstack != null && itemstack.getItem() instanceof ItemFood)
			{
				EventFoodDecayHandler.addCookedFood(itemstack.getItem());
			}
		}*/
	}
}