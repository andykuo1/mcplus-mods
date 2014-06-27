package com.minecraftplus.modClayTools;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus._common.dye.RecipesDyeable;

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
		par1Registry.addRecipe(new RecipesSwordClay());
		par1Registry.addRecipe(new RecipesDyeable(MCP_ClayTools.swordClay));
		par1Registry.addRecipe(new RecipesSpadeClay());
		par1Registry.addRecipe(new RecipesDyeable(MCP_ClayTools.shovelClay));
		par1Registry.addRecipe(new RecipesPickaxeClay());
		par1Registry.addRecipe(new RecipesDyeable(MCP_ClayTools.pickaxeClay));
		par1Registry.addRecipe(new RecipesAxeClay());
		par1Registry.addRecipe(new RecipesDyeable(MCP_ClayTools.axeClay));
		par1Registry.addRecipe(new RecipesHoeClay());
		par1Registry.addRecipe(new RecipesDyeable(MCP_ClayTools.hoeClay));
	}
}
