package net.minecraftplus._api.mod;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftplus._api.IMod;

public class MC implements IMod
{
	public static MC INSTANCE;
	
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent) {}

	@Override
	public void Initialize(FMLInitializationEvent parEvent) {}

	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent) {}

	@Override
	public String getModID()
	{
		return "minecraft";
	}

	@Override
	public String getVersion()
	{
		return "1.7.10";
	}

	@Override
	public Configuration getConfig()
	{
		return null;
	}

	@Override
	public boolean isConfigMod()
	{
		return false;
	}
}
