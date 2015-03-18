package net.minecraftplus.turtle;

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
			EntityTurtle entity = (EntityTurtle) parWorld.getEntityByID(parY);
			return new ContainerTurtle(parPlayer, entity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int parID, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ)
	{
		switch(parID)
		{
		case 0:
			EntityTurtle entity = (EntityTurtle) parWorld.getEntityByID(parY);
			return new GuiTurtle(parPlayer, entity);
		}
		return null;
	}
}