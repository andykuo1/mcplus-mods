package com.minecraftplus.modSoulExtractor;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

public class CommonProxy implements ICommonProxy
{
	@Override
	public void register(Registry.RenderMode par1Registry)
	{

	}

	@Override
	public void register(Registry.CustomEntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.EntityMode par1Registry)
	{
		par1Registry.addTileEntity(TileEntitySoulExtractor.class, "soul_extractor");
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addShapedRecipe(new ItemStack(MCP_SoulExtractor.soulExtractor, 1), new Object[] {
			" X ", "Y#Y", "###",
			Character.valueOf('#'), Blocks.obsidian,
			Character.valueOf('X'), Items.ender_pearl,
			Character.valueOf('Y'), Items.gold_ingot});
	}
}
