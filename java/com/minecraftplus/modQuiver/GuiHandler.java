package com.minecraftplus.modQuiver;

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
			return new ContainerQuiver(par2EntityPlayer, ItemQuiver.getQuiverInv(par2EntityPlayer, y == 0), y == 0 ? par2EntityPlayer.getCurrentEquippedItem() : par2EntityPlayer.getCurrentArmor(1), y == 0 ? par2EntityPlayer.inventory.currentItem : -2);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		switch (par1)
		{
		case 0:
			return new GuiQuiver(par2EntityPlayer, ItemQuiver.getQuiverInv(par2EntityPlayer, y == 0));
		}
		return null;
	}
}