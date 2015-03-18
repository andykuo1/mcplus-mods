package net.minecraftplus.skullcandle;

import net.minecraftforge.common.config.Configuration;
import net.minecraftplus._api.IConfigMod;
import cpw.mods.fml.client.event.ConfigChangedEvent;
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

		this.mod.Configure(this.config);
		this.config.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent par1Event)
	{
		if (par1Event.modID.equals(this.modid))
		{
			this.mod.Configure(this.config);
			this.config.save();
		}
	}

	public Configuration getConfig()
	{
		return this.config;
	}
}
