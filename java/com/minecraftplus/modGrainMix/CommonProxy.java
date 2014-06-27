package com.minecraftplus.modGrainMix;

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
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.wheat_seeds, Items.wheat_seeds, Items.wheat_seeds, Items.bowl});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.wheat_seeds, Items.wheat_seeds, Items.pumpkin_seeds, Items.bowl});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.wheat_seeds, Items.wheat_seeds, Items.melon_seeds, Items.bowl});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.wheat_seeds, Items.pumpkin_seeds, Items.pumpkin_seeds, Items.bowl});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.wheat_seeds, Items.pumpkin_seeds, Items.melon_seeds, Items.bowl});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.wheat_seeds, Items.melon_seeds, Items.melon_seeds, Items.bowl});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.pumpkin_seeds, Items.pumpkin_seeds, Items.pumpkin_seeds, Items.bowl});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.pumpkin_seeds, Items.pumpkin_seeds, Items.melon_seeds, Items.bowl});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.pumpkin_seeds, Items.melon_seeds, Items.melon_seeds, Items.bowl});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_GrainMix.grainMix), new Object[] {Items.melon_seeds, Items.melon_seeds, Items.melon_seeds, Items.bowl});
	}
}
