package com.minecraftplus.modIceBox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
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
			TileEntity tileentity = par3World.getTileEntity(x, y, z);
			return tileentity != null ? new ContainerIceBox(par2EntityPlayer.inventory, (IInventory) tileentity) : null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		switch (par1)
		{
		case 0:
			TileEntity tileentity = par3World.getTileEntity(x, y, z);
			return tileentity != null ? new GuiIceBox(par2EntityPlayer.inventory, (IInventory) tileentity) : null;
		}
		return null;
	}
}
