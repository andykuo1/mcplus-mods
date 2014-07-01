package com.minecraftplus.modShatter;

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
		par1Registry.addEntity(EntityArrowShatter.class, "arrow_shatter");
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{

	}
}
