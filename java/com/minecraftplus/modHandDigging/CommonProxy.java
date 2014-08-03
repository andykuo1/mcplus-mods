package com.minecraftplus.modHandDigging;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

public class CommonProxy implements ICommonProxy
{
	public static boolean FIRE_PIT_RECIPE = false;

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
		par1Registry.addShapedRecipe(new ItemStack(Blocks.cobblestone, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), MCP_HandDigging.rock});

		par1Registry.addShapedRecipe(new ItemStack(Blocks.dirt, 1), new Object[] {
			"##", "##",
			Character.valueOf('#'), MCP_HandDigging.dirtBall});

		if (FIRE_PIT_RECIPE)
		{
			par1Registry.addShapedRecipe(new ItemStack(com.minecraftplus.modFirePit.MCP_FirePit.firePit, 1, 0), new Object[] {
				"##","XX",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), MCP_HandDigging.rock});
		}
	}
}
