package net.minecraftplus.mcp_satchel;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerSatchel implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int parID, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ)
	{
		switch(parID)
		{
		case 0:
			return new ContainerSatchel(parPlayer, parPlayer.getCurrentEquippedItem());
		case 1:
			return new ContainerEnderSatchel(parPlayer, parPlayer.getInventoryEnderChest(), parPlayer.getCurrentEquippedItem());
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int parID, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ)
	{
		switch(parID)
		{
		case 0:
			return new GuiSatchel(parPlayer, parPlayer.getCurrentEquippedItem());
		case 1:
			return new GuiChest(parPlayer.inventory, parPlayer.getInventoryEnderChest());
		}
		return null;
	}
}
