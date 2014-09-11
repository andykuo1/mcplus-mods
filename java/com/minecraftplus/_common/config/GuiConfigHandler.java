package com.minecraftplus._common.config;

import net.minecraftforge.common.config.Configuration;

import com.minecraftplus._base.registry.ModRegistry;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GuiConfigHandler
{
	private final Configuration config;
	private final IConfigMod mod;
	private final String modid;

	public GuiConfigHandler(Configuration par1Configuration, IConfigMod par2IConfig, String par3ModID)
	{
		this.config = par1Configuration;
		this.mod = par2IConfig;
		this.modid = par3ModID;

		ModRegistry.addEventHandler(FMLCommonHandler.instance().bus(), this);

		this.mod.config(this.config);
		this.config.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent par1Event)
	{
		if (par1Event.modID.equals(this.modid))
		{
			this.mod.config(this.config);
			this.config.save();
		}
	}

	public Configuration getConfig()
	{
		return this.config;
	}
}
