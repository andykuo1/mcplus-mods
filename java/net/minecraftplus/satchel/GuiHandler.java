package net.minecraftplus.satchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int parID, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ)
	{
		switch(parID)
		{
		case 0:
			return new ContainerSatchel(parPlayer, parPlayer.getCurrentEquippedItem());
		case 1:
			return new ContainerEnderSatchel(parPlayer, parPlayer.getCurrentEquippedItem());
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
			return new GuiEnderSatchel(parPlayer, parPlayer.getCurrentEquippedItem());
		}
		return null;
	}
}
