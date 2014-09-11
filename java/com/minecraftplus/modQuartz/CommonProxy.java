package com.minecraftplus.modQuartz;

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
		ModRegistry.addShapelessRecipe(new ItemStack(Items.quartz, 4), Blocks.quartz_block);

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.swordQuartz, 1), new Object[] {
			"#", "#", "X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.shovelQuartz, 1), new Object[] {
			"#", "X", "X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.pickaxeQuartz, 1), new Object[] {
			"###", " X ", " X ",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.axeQuartz, 1), new Object[] {
			"##", "#X", " X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.axeQuartz, 1), new Object[] {
			"##", "X#", "X ",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.hoeQuartz, 1), new Object[] {
			"##", " X", " X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.hoeQuartz, 1), new Object[] {
			"##", "X ", "X ",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.helmetQuartz, 1), new Object[] {
			"###", "# #",
			Character.valueOf('#'), Blocks.quartz_block});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.plateQuartz, 1), new Object[] {
			"# #", "###", "###",
			Character.valueOf('#'), Blocks.quartz_block});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.legsQuartz, 1), new Object[] {
			"###", "# #", "# #",
			Character.valueOf('#'), Blocks.quartz_block});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Quartz.bootsQuartz, 1), new Object[] {
			"# #", "# #",
			Character.valueOf('#'), Blocks.quartz_block});
	}
}
