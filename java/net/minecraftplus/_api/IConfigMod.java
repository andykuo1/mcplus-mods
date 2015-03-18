package net.minecraftplus._api;

import net.minecraftforge.common.config.Configuration;

public interface IConfigMod extends IMod
{
	public void Configure(Configuration parConfig);
}
