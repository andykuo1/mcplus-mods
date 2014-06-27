package com.minecraftplus.modQuartz;

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

	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addShapelessRecipe(new ItemStack(Items.quartz, 4), Blocks.quartz_block);

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.swordQuartz, 1), new Object[] {
			"#", "#", "X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.shovelQuartz, 1), new Object[] {
			"#", "X", "X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.pickaxeQuartz, 1), new Object[] {
			"###", " X ", " X ",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.axeQuartz, 1), new Object[] {
			"##", "#X", " X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.axeQuartz, 1), new Object[] {
			"##", "X#", "X ",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.hoeQuartz, 1), new Object[] {
			"##", " X", " X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.hoeQuartz, 1), new Object[] {
			"##", "X ", "X ",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.helmetQuartz, 1), new Object[] {
			"###", "# #",
			Character.valueOf('#'), Blocks.quartz_block});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.plateQuartz, 1), new Object[] {
			"# #", "###", "###",
			Character.valueOf('#'), Blocks.quartz_block});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.legsQuartz, 1), new Object[] {
			"###", "# #", "# #",
			Character.valueOf('#'), Blocks.quartz_block});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Quartz.bootsQuartz, 1), new Object[] {
			"# #", "# #",
			Character.valueOf('#'), Blocks.quartz_block});
	}
}
