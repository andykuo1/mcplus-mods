package com.minecraftplus.modSilver;

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
		par1Registry.addShapedRecipe(new ItemStack(MCP_Silver.silverBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), MCP_Silver.silverIngot});
		par1Registry.addShapedRecipe(new ItemStack(MCP_Silver.silverIngot, 1), new Object[] {
			"   ", "###", "###",
			Character.valueOf('#'), MCP_Silver.silverNugget});
		par1Registry.addShapelessRecipe(new ItemStack(MCP_Silver.silverIngot, 9), new Object[] {
			MCP_Silver.silverBlock
		});
		par1Registry.addSmelting(MCP_Silver.silverOre, new ItemStack (MCP_Silver.silverIngot), 1);
		};
	}

