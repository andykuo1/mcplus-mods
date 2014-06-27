package com.minecraftplus.modGiftBox;

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
		par1Registry.addRecipe(new RecipesGiftBox());
		par1Registry.addRecipe(new RecipesDyeable(MCP_GiftBox.giftBox));
	}
}
