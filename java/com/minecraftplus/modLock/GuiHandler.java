package com.minecraftplus.modLock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		switch (par1)
		{
		case 0:
			return new ContainerLock(par2EntityPlayer, (EntityLock) par3World.getEntityByID(y));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		switch (par1)
		{
		case 0:
			return new GuiLock(par2EntityPlayer, (EntityLock) par3World.getEntityByID(y));
		}
		return null;
	}
}
