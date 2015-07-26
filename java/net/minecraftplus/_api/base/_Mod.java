package net.minecraftplus._api.base;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.Munge;
import net.minecraftplus._api.dictionary.Exceptions;

public abstract class _Mod
{
	static
	{
		Munge.Open();
		Munge.PreInitialize();
	}

	public _Mod()
	{
		Exceptions.InvalidFormat(this.getClass().getSimpleName().charAt(0) == '_');
	}

	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		Configuration config = new Configuration(parEvent.getSuggestedConfigurationFile());
		config.load();

		this.Configure(config);

		if (config.hasChanged())
		{
			config.save();
		}
	}

	public void Initialize(FMLInitializationEvent parEvent)
	{
		Munge.Initialize(MCP.mod());
	}

	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		Munge.Close();
	}

	public void Configure(Configuration parConfiguration)
	{

	}

	public void Munge()
	{

	}
}
