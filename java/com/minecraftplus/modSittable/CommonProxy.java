package com.minecraftplus.modSittable;

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
		par1Registry.addCustomEntity(EntitySittable.class, "sittable", MCP_Sittable.INSTANCE, 160, Integer.MAX_VALUE, false);
	}

	@Override
	public void register(Registry.EntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{

	}
}
