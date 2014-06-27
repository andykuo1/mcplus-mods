package com.minecraftplus._base;

import com.minecraftplus._base.registry.Registry;


public interface ICommonProxy
{
	public void register(Registry.RenderMode par1Registry);
	public void register(Registry.CustomEntityMode par1Registry);
	public void register(Registry.EntityMode par1Registry);
	public void register(Registry.RecipeMode par1Registry);
}
