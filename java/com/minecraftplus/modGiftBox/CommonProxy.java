package com.minecraftplus.modGiftBox;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;
import com.minecraftplus._common.dye.RecipesDyeable;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addRecipe(new RecipesGiftBox());
		ModRegistry.addRecipe(new RecipesDyeable(MCP_GiftBox.giftBox));
	}
}
