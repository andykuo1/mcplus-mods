package net.minecraftplus.claytools;

import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addRecipe(new RecipesSwordClay());
		this.addRecipe(new RecipesDyeable(ModClayTools.swordClay));
		this.addRecipe(new RecipesSpadeClay());
		this.addRecipe(new RecipesDyeable(ModClayTools.shovelClay));
		this.addRecipe(new RecipesPickaxeClay());
		this.addRecipe(new RecipesDyeable(ModClayTools.pickaxeClay));
		this.addRecipe(new RecipesAxeClay());
		this.addRecipe(new RecipesDyeable(ModClayTools.axeClay));
		this.addRecipe(new RecipesHoeClay());
		this.addRecipe(new RecipesDyeable(ModClayTools.hoeClay));
	}
}
