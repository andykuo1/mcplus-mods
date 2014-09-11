package com.minecraftplus.modMortar;

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
			TileEntityMortar tileentity = (TileEntityMortar)par3World.getTileEntity(x, y, z);
			return tileentity != null ? new ContainerMortar(par2EntityPlayer, tileentity) : null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		switch (par1)
		{
		case 0:
			TileEntityMortar tileentity = (TileEntityMortar)par3World.getTileEntity(x, y, z);
			return tileentity != null ? new GuiMortar(par2EntityPlayer.inventory, tileentity) : null;
		}
		return null;
	}
}