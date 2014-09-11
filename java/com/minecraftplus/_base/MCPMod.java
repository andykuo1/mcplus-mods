package com.minecraftplus._base;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface MCPMod
{
	public void preInit(FMLPreInitializationEvent par1Event);
	public void mainInit(FMLInitializationEvent par1Event);
	public void postInit(FMLPostInitializationEvent par1Event);
}
