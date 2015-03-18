package net.minecraftplus._api;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IMod
{
	/**Called by Forge to initialize mod.
	 * <br>Must have {@link EventHandler} as annotation!
	 * 
	 * @param parEvent the event
	 * */
	public void PreInitialize(FMLPreInitializationEvent parEvent);

	/**Called by Forge to initialize mod.
	 * <br>Must have {@link EventHandler} as annotation!
	 * 
	 * @param parEvent the event
	 * */
	public void Initialize(FMLInitializationEvent parEvent);

	/**Called by Forge to initialize mod.
	 * <br>Must have {@link EventHandler} as annotation!
	 * 
	 * @param parEvent the event
	 * */
	public void PostInitialize(FMLPostInitializationEvent parEvent);
	
	/**
	 * Returns registered mod id
	 * 
	 * @return mod id
	 * */
	public String getModID();
	
	/**
	 * Returns current mod version
	 * 
	 * @return mod version
	 * */
	public String getVersion();
	
	/**
	 * Gets mod-specific config file
	 * 
	 * @return the {@link Configuration} of this mod
	 * */
	public Configuration getConfig();
	
	/**
	 * Checks whether is a config mod
	 * 
	 * @return if implements {@link IConfigMod}
	 * */
	public boolean isConfigMod();
}
