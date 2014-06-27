package com.minecraftplus.modSatchel;

import net.minecraft.client.gui.inventory.GuiChest;
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
			return new ContainerSatchel(par2EntityPlayer, ItemSatchel.getSatchelInv(par2EntityPlayer), par2EntityPlayer.getCurrentEquippedItem());
		case 1:
			return new ContainerEnderSatchel(par2EntityPlayer, par2EntityPlayer.getInventoryEnderChest(), par2EntityPlayer.getCurrentEquippedItem());
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		switch (par1)
		{
		case 0:
			return new GuiSatchel(par2EntityPlayer, ItemSatchel.getSatchelInv(par2EntityPlayer), par2EntityPlayer.getCurrentEquippedItem());
		case 1:
			return new GuiChest(par2EntityPlayer.inventory, par2EntityPlayer.getInventoryEnderChest());
		}
		return null;
	}
}