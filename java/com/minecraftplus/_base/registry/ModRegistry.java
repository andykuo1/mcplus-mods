package com.minecraftplus._base.registry;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;

import com.minecraftplus._base.MCPMod;
import com.minecraftplus._base.worldgen.WorldGenBlock;
import com.minecraftplus._common.packet.Packet;

import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRegistry
{
	public static void addRepairMaterial(ToolMaterial par1ToolMaterial, Item par2Item)
	{
		par1ToolMaterial.customCraftingMaterial = par2Item;
	}

	public static void addRepairMaterial(ArmorMaterial par1ArmorMaterial, Item par2Item)
	{
		par1ArmorMaterial.customCraftingMaterial = par2Item;
	}

	public static void addGuiHandler(MCPMod par1MCP, IGuiHandler parIGuiHandler)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(par1MCP, parIGuiHandler);
	}

	public static void addEventHandler(EventBus par1EventBus, Object par2Object)
	{
		par1EventBus.register(par2Object);
	}

	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////	CUSTOM ENTITY
	/////////////////////////////////////////////////////////////////

	public static void addCustomEntity(Class<? extends Entity> par1Class, String par2String, int par3ID, MCPMod par4MCP, int par5TrackRange, int par6UpdateFreq, boolean par7Velocity)
	{
		EntityRegistry.registerModEntity(par1Class, par2String, par3ID, par4MCP, par5TrackRange, par6UpdateFreq, par7Velocity);
	}

	public static void addCustomEntity(Class<? extends Entity> par1Class, String par2String, MCPMod par3MCP, int par4TrackRange, int par5UpdateFreq, boolean par6Velocity)
	{
		addCustomEntity(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId(), par3MCP, par4TrackRange, par5UpdateFreq, par6Velocity);
	}

	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////	ENTITY
	/////////////////////////////////////////////////////////////////

	public static void addEntity(Class<? extends Entity> par1Class, String par2String, int par3ID)
	{
		EntityRegistry.registerGlobalEntityID(par1Class, par2String, par3ID);
		LanguageRegistry.add(par1Class);
	}

	public static void addEntity(Class<? extends Entity> par1Class, String par2String)
	{
		addEntity(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId());
	}

	public static void addEntity(Class<? extends Entity> par1Class, String par2String, int par3ID, int par4Color, int par5Color)
	{
		EntityRegistry.registerGlobalEntityID(par1Class, par2String, par3ID, par4Color, par5Color);
		LanguageRegistry.add(par1Class);
	}

	public static void addEntity(Class<? extends Entity> par1Class, String par2String, int par3Color, int par4Color)
	{
		addEntity(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId(), par3Color, par4Color);
	}

	public static void addEntitySpawn(String par1String, int par2Probability, int par3Min, int par4Max, EnumCreatureType par5Creature, BiomeGenBase...par6BiomeGenBase)
	{
		EntityRegistry.addSpawn(par1String, par2Probability, par3Min, par4Max, par5Creature, par6BiomeGenBase);
	}

	public static void addEntityUnLocal(Class<? extends Entity> par1Class, String par2String, int par3ID)
	{
		EntityRegistry.registerGlobalEntityID(par1Class, par2String, par3ID);
	}

	public static void addEntityUnLocal(Class<? extends Entity> par1Class, String par2String)
	{
		addEntityUnLocal(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId());
	}

	public static void addEntityUnLocal(Class<? extends Entity> par1Class, String par2String, int par3ID, int par4Color, int par5Color)
	{
		EntityRegistry.registerGlobalEntityID(par1Class, par2String, par3ID, par4Color, par5Color);
	}

	public static void addEntityUnLocal(Class<? extends Entity> par1Class, String par2String, int par3Color, int par4Color)
	{
		addEntityUnLocal(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId(), par3Color, par4Color);
	}

	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////	TILE ENTITY
	/////////////////////////////////////////////////////////////////

	public static void addTileEntity(Class<? extends TileEntity> par1Class, String par2String)
	{
		GameRegistry.registerTileEntity(par1Class, par2String);
		LanguageRegistry.add("container." + par2String, LanguageRegistry.getNameReadable(par2String));
	}

	public static void addTileEntityUnLocal(Class<? extends TileEntity> par1Class, String par2String)
	{
		GameRegistry.registerTileEntity(par1Class, par2String);
	}

	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////	RECIPE
	/////////////////////////////////////////////////////////////////

	public static IRecipe addShapedRecipe(ItemStack par1ItemStack, Object...par2Object)
	{
		return GameRegistry.addShapedRecipe(par1ItemStack, par2Object);
	}

	public static IRecipe addShapelessRecipe(ItemStack par1ItemStack, Object...par2Object)
	{
		//GameRegistry.addShapelessRecipe(par1ItemStack, par2Object);

		ArrayList arraylist = new ArrayList();
		Object[] aobject = par2Object;
		int i = par2Object.length;

		for (int j = 0; j < i; ++j)
		{
			Object object1 = aobject[j];

			if (object1 instanceof ItemStack)
			{
				arraylist.add(((ItemStack)object1).copy());
			}
			else if (object1 instanceof Item)
			{
				arraylist.add(new ItemStack((Item)object1));
			}
			else
			{
				if (!(object1 instanceof Block))
				{
					throw new RuntimeException("Invalid shapeless recipy!");
				}

				arraylist.add(new ItemStack((Block)object1));
			}
		}

		IRecipe recipe = new ShapelessRecipes(par1ItemStack, arraylist);
		CraftingManager.getInstance().getRecipeList().add(recipe);
		return recipe;
	}

	public static IRecipe addRecipe(IRecipe par1IRecipe)
	{
		GameRegistry.addRecipe(par1IRecipe);
		return par1IRecipe;
	}

	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////	SMELTING
	/////////////////////////////////////////////////////////////////

	public static void addSmelting(Item par1Item, ItemStack par2ItemStack, float par3)
	{
		GameRegistry.addSmelting(par1Item, par2ItemStack, par3);
	}

	public static void addSmelting(Block par1Block, ItemStack par2ItemStack, float par3)
	{
		GameRegistry.addSmelting(par1Block, par2ItemStack, par3);
	}

	public static void addSmelting(ItemStack par1ItemStack, ItemStack par2ItemStack, float par3)
	{
		GameRegistry.addSmelting(par1ItemStack, par2ItemStack, par3);
	}

	/////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////	FUEL
	/////////////////////////////////////////////////////////////////

	public static void addFuel(Item par1Item, int par2BurnTime)
	{
		FuelRegistry.add(par1Item, par2BurnTime);
	}
}
