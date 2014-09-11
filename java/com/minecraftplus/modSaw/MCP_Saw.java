package com.minecraftplus.modSaw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.registry.ItemRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.D + MCP_Saw.MODBASE, name = MCP.PRE + MCP_Saw.MODBASE, version = "1.0.2", dependencies = MCP.DEPENDENCY)
public class MCP_Saw implements MCPMod
{
	protected static final String MODBASE = "Saw";

	@Instance(MCP.D + MCP_Saw.MODBASE)
	public static MCP_Saw INSTANCE;

	@SidedProxy(clientSide = MCP.A + MODBASE + MCP.B, serverSide = MCP.A + MODBASE + MCP.C)
	public static CommonProxy proxy;

	public static final Map<WoodPlank, ItemStack> woodPlanks = new HashMap<WoodPlank, ItemStack>();

	public static final Block saw = new BlockSaw().setBlockName("saw");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		ItemRegistry.add(saw);

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
		List objects = CraftingManager.getInstance().getRecipeList();

		for(Object obj : objects)
		{
			if (obj instanceof ShapedRecipes)
			{
				ItemStack[] recipe = ((ShapedRecipes) obj).recipeItems;
				if (recipe.length == 1 && recipe[0] != null)
				{
					Block block = Block.getBlockFromItem(recipe[0].getItem());
					if (block instanceof BlockLog)
					{
						WoodPlank woodplank = new WoodPlank(block, recipe[0].getItemDamage());
						woodPlanks.put(woodplank, ((ShapedRecipes) obj).getRecipeOutput());
					}
				}
			}

			if (obj instanceof ShapelessRecipes)
			{
				List recipe = ((ShapelessRecipes) obj).recipeItems;
				if (recipe.size() == 1 && recipe.get(0) instanceof ItemStack)
				{
					Block block = Block.getBlockFromItem(((ItemStack) recipe.get(0)).getItem());
					if (block instanceof BlockLog)
					{
						WoodPlank woodplank = new WoodPlank(block, ((ItemStack) recipe.get(0)).getItemDamage());
						woodPlanks.put(woodplank, ((ShapelessRecipes) obj).getRecipeOutput());
					}
				}
			}
		}

		List<IRecipe> trashBin = new ArrayList<IRecipe>();

		for(Object obj : objects)
		{
			IRecipe recipe = (IRecipe) obj;
			if (recipe.getRecipeOutput() != null)
			{
				if (recipe.getRecipeOutput().getItem() == Item.getItemFromBlock(Blocks.planks))
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
}