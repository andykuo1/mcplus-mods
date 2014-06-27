package com.minecraftplus._base.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;

import com.minecraftplus._base.MCP;
import com.minecraftplus._base.worldgen.WorldGenBlock;
import com.minecraftplus._common.packet.Packet;
import com.minecraftplus._common.render.RenderBlock;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registry
{
	public static final RenderMode RENDER = new RenderMode();
	public static final CustomEntityMode CUSTOM_ENTITY = new CustomEntityMode();
	public static final EntityMode ENTITY = new EntityMode();
	public static final RecipeMode RECIPE = new RecipeMode();

	public static void addRepairMaterial(ToolMaterial par1ToolMaterial, Item par2Item)
	{
		par1ToolMaterial.customCraftingMaterial = par2Item;
	}

	public static void addRepairMaterial(ArmorMaterial par1ArmorMaterial, Item par2Item)
	{
		par1ArmorMaterial.customCraftingMaterial = par2Item;
	}

	public static void addWorldGen(WorldGenBlock par1WorldGenBlock)
	{
		MCP.registerWorldGenBlock(par1WorldGenBlock);
	}

	public static void addGuiHandler(MCP par1MCP, IGuiHandler parIGuiHandler)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(par1MCP, parIGuiHandler);
	}

	public static boolean addPacket(Class<? extends Packet> par1Class)
	{
		return MCP.registerPacket(par1Class);
	}

	public static boolean addRenderBlock(RenderBlock par1RenderBlock)
	{
		return MCP.registerRenderBlock(par1RenderBlock);
	}

	public static class RenderMode
	{
		public void addEntityRender(Class<? extends Entity> par1Class, Render par2Render)
		{
			RenderingRegistry.registerEntityRenderingHandler(par1Class, par2Render);
		}
	}

	public static class CustomEntityMode
	{
		public void addCustomEntity(Class<? extends Entity> par1Class, String par2String, int par3ID, MCP par4MCP, int par5TrackRange, int par6UpdateFreq, boolean par7Velocity)
		{
			EntityRegistry.registerModEntity(par1Class, par2String, par3ID, par4MCP, par5TrackRange, par6UpdateFreq, par7Velocity);
		}

		public void addCustomEntity(Class<? extends Entity> par1Class, String par2String, MCP par3MCP, int par4TrackRange, int par5UpdateFreq, boolean par6Velocity)
		{
			this.addCustomEntity(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId(), par3MCP, par4TrackRange, par5UpdateFreq, par6Velocity);
		}
	}

	public static class EntityMode
	{
		public void addEntity(Class<? extends Entity> par1Class, String par2String, int par3ID)
		{
			EntityRegistry.registerGlobalEntityID(par1Class, par2String, par3ID);
			LanguageRegistry.add(par1Class);
		}

		public void addEntity(Class<? extends Entity> par1Class, String par2String)
		{
			this.addEntity(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId());
		}

		public void addEntity(Class<? extends Entity> par1Class, String par2String, int par3ID, int par4Color, int par5Color)
		{
			EntityRegistry.registerGlobalEntityID(par1Class, par2String, par3ID, par4Color, par5Color);
			LanguageRegistry.add(par1Class);
		}

		public void addEntity(Class<? extends Entity> par1Class, String par2String, int par3Color, int par4Color)
		{
			this.addEntity(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId(), par3Color, par4Color);
		}

		public void addEntitySpawn(String par1String, int par2Probability, int par3Min, int par4Max, EnumCreatureType par5Creature, BiomeGenBase...par6BiomeGenBase)
		{
			EntityRegistry.addSpawn(par1String, par2Probability, par3Min, par4Max, par5Creature, par6BiomeGenBase);
		}

		public void addTileEntity(Class<? extends TileEntity> par1Class, String par2String)
		{
			GameRegistry.registerTileEntity(par1Class, par2String);
			LanguageRegistry.add("container." + par2String, LanguageRegistry.getNameReadable(par2String));
		}

		public void addEntityUnLocal(Class<? extends Entity> par1Class, String par2String, int par3ID)
		{
			EntityRegistry.registerGlobalEntityID(par1Class, par2String, par3ID);
		}

		public void addEntityUnLocal(Class<? extends Entity> par1Class, String par2String)
		{
			this.addEntityUnLocal(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId());
		}

		public void addEntityUnLocal(Class<? extends Entity> par1Class, String par2String, int par3ID, int par4Color, int par5Color)
		{
			EntityRegistry.registerGlobalEntityID(par1Class, par2String, par3ID, par4Color, par5Color);
		}

		public void addEntityUnLocal(Class<? extends Entity> par1Class, String par2String, int par3Color, int par4Color)
		{
			this.addEntityUnLocal(par1Class, par2String, EntityRegistry.findGlobalUniqueEntityId(), par3Color, par4Color);
		}

		public void addTileEntityUnLocal(Class<? extends TileEntity> par1Class, String par2String)
		{
			GameRegistry.registerTileEntity(par1Class, par2String);
		}
	}

	public static class RecipeMode
	{
		public void addShapedRecipe(ItemStack par1ItemStack, Object...par2Object)
		{
			GameRegistry.addShapedRecipe(par1ItemStack, par2Object);
		}

		public void addShapelessRecipe(ItemStack par1ItemStack, Object...par2Object)
		{
			GameRegistry.addShapelessRecipe(par1ItemStack, par2Object);
		}

		public void addRecipe(IRecipe par1IRecipe)
		{
			GameRegistry.addRecipe(par1IRecipe);
		}

		public void addSmelting(Item par1Item, ItemStack par2ItemStack, float par3)
		{
			GameRegistry.addSmelting(par1Item, par2ItemStack, par3);
		}

		public void addSmelting(Block par1Block, ItemStack par2ItemStack, float par3)
		{
			GameRegistry.addSmelting(par1Block, par2ItemStack, par3);
		}

		public void addSmelting(ItemStack par1ItemStack, ItemStack par2ItemStack, float par3)
		{
			GameRegistry.addSmelting(par1ItemStack, par2ItemStack, par3);
		}

		public void addFuel(Item par1Item, int par2BurnTime)
		{
			MCP.registerFuel(par1Item, par2BurnTime);
		}

		public void addFuel(Class<? extends Item> par1Class, int par2BurnTime)
		{
			MCP.registerFuel(par1Class, par2BurnTime);
		}
	}
}
