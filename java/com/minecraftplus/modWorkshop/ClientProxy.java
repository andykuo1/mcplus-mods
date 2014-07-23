package com.minecraftplus.modWorkshop;

import net.minecraftforge.common.MinecraftForge;

import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@SideOnly(Side.CLIENT)
	@Override
	public void register(Registry.RenderMode par1Registry)
	{
		this.register(Registry.CUSTOM_ENTITY);
	}
}
