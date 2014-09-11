package com.minecraftplus.modCart;

import com.minecraftplus._base.registry.RenderRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@SideOnly(Side.CLIENT)
	@Override
	public void register()
	{
		super.register();
		RenderRegistry.addEntityRender(EntityCart.class, new RenderCart());
	}
}
