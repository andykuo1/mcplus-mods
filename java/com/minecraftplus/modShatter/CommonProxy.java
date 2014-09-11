package com.minecraftplus.modShatter;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addEntity(EntityArrowShatter.class, "arrow_shatter");
	}
}
