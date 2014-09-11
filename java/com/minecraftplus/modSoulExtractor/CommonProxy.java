package com.minecraftplus.modSoulExtractor;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addTileEntity(TileEntitySoulExtractor.class, "soul_extractor");

		ModRegistry.addShapedRecipe(new ItemStack(MCP_SoulExtractor.soulExtractor, 1), new Object[] {
			" X ", "Y#Y", "###",
			Character.valueOf('#'), Blocks.obsidian,
			Character.valueOf('X'), Items.ender_pearl,
			Character.valueOf('Y'), Items.gold_ingot});
	}
}
