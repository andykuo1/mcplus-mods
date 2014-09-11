package com.minecraftplus.modClayTools;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;
import com.minecraftplus._common.dye.RecipesDyeable;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addRecipe(new RecipesSwordClay());
		ModRegistry.addRecipe(new RecipesDyeable(MCP_ClayTools.swordClay));
		ModRegistry.addRecipe(new RecipesSpadeClay());
		ModRegistry.addRecipe(new RecipesDyeable(MCP_ClayTools.shovelClay));
		ModRegistry.addRecipe(new RecipesPickaxeClay());
		ModRegistry.addRecipe(new RecipesDyeable(MCP_ClayTools.pickaxeClay));
		ModRegistry.addRecipe(new RecipesAxeClay());
		ModRegistry.addRecipe(new RecipesDyeable(MCP_ClayTools.axeClay));
		ModRegistry.addRecipe(new RecipesHoeClay());
		ModRegistry.addRecipe(new RecipesDyeable(MCP_ClayTools.hoeClay));
	}
}
