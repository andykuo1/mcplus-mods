package com.minecraftplus.modWoodenBucket;

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
		ModRegistry.addFuel(MCP_WoodenBucket.woodenBucketEmpty, 200);

		ModRegistry.addShapedRecipe(new ItemStack(MCP_WoodenBucket.woodenBucketEmpty, 1), new Object[] {
			"# #", " X ",
			Character.valueOf('#'), Blocks.planks,
			Character.valueOf('X'), Items.bowl});
	}
}
