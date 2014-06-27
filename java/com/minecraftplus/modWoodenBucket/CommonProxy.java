package com.minecraftplus.modWoodenBucket;

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
		par1Registry.addFuel(MCP_WoodenBucket.woodenBucketEmpty, 200);
		par1Registry.addFuel(MCP_WoodenBucket.woodenBucketLava, 20000);

		par1Registry.addShapedRecipe(new ItemStack(MCP_WoodenBucket.woodenBucketEmpty, 1), new Object[] {
			"# #", " X ",
			Character.valueOf('#'), Blocks.planks,
			Character.valueOf('X'), Items.bowl});
	}
}
