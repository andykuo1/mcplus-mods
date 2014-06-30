package com.minecraftplus.modSaw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

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

@Mod(modid = "MCP_" + MCP_Saw.MODBASE, name = "MC+ " + MCP_Saw.MODBASE, version = "1.0.0")
public class MCP_Saw extends MCP
{
	protected static final String MODBASE = "Saw";

	@Instance("MCP_" + MCP_Saw.MODBASE)
	public static MCP_Saw INSTANCE;

	@SidedProxy(clientSide = "com.minecraftplus.mod" + MODBASE + ".ClientProxy", serverSide = "com.minecraftplus.mod" + MODBASE + ".CommonProxy")
	public static CommonProxy proxy;

	//TODO: Nothing yet. . .

	public static final Map<WoodPlank, ItemStack> woodPlanks = new HashMap<WoodPlank, ItemStack>();

	public static class WoodPlank
	{
		private final Block block;
		private final int metadata;

		public WoodPlank(Block par1Block, int par2)
		{
			this.block = par1Block;
			this.metadata = par2;
		}

		public Block getBlock()
		{
			return this.block;
		}

		public int getBlockMetadata()
		{
			return this.metadata;
		}

		@Override
		public int hashCode()
		{
			return block.hashCode();
		}

		@Override
		public boolean equals(Object par1Object)
		{
			if (par1Object instanceof WoodPlank)
			{
				WoodPlank woodplank = (WoodPlank) par1Object;
				return this.block == woodplank.block && this.metadata == woodplank.metadata;
			}

			return false;
		}
	}

	public static final Block saw = new BlockSaw().setBlockName("saw");

	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent par1Event)
	{
		MCP.initMain(par1Event, "1.1");

		ItemRegistry.add(saw);

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
	}
}