package net.minecraftplus.quartz;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapelessRecipe(new ItemStack(Items.quartz, 4), Blocks.quartz_block);

		this.addShapedRecipe(new ItemStack(ModQuartz.swordQuartz, 1), new Object[] {
			"#", "#", "X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		this.addShapedRecipe(new ItemStack(ModQuartz.shovelQuartz, 1), new Object[] {
			"#", "X", "X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		this.addShapedRecipe(new ItemStack(ModQuartz.pickaxeQuartz, 1), new Object[] {
			"###", " X ", " X ",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		this.addShapedRecipe(new ItemStack(ModQuartz.axeQuartz, 1), new Object[] {
			"##", "#X", " X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		this.addShapedRecipe(new ItemStack(ModQuartz.axeQuartz, 1), new Object[] {
			"##", "X#", "X ",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		this.addShapedRecipe(new ItemStack(ModQuartz.hoeQuartz, 1), new Object[] {
			"##", " X", " X",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		this.addShapedRecipe(new ItemStack(ModQuartz.hoeQuartz, 1), new Object[] {
			"##", "X ", "X ",
			Character.valueOf('#'), Blocks.quartz_block,
			Character.valueOf('X'), Items.stick});

		this.addShapedRecipe(new ItemStack(ModQuartz.helmetQuartz, 1), new Object[] {
			"###", "# #",
			Character.valueOf('#'), Blocks.quartz_block});

		this.addShapedRecipe(new ItemStack(ModQuartz.plateQuartz, 1), new Object[] {
			"# #", "###", "###",
			Character.valueOf('#'), Blocks.quartz_block});

		this.addShapedRecipe(new ItemStack(ModQuartz.legsQuartz, 1), new Object[] {
			"###", "# #", "# #",
			Character.valueOf('#'), Blocks.quartz_block});

		this.addShapedRecipe(new ItemStack(ModQuartz.bootsQuartz, 1), new Object[] {
			"# #", "# #",
			Character.valueOf('#'), Blocks.quartz_block});
	}
}
