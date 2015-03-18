package net.minecraftplus.pigeon;

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
			EntityPigeon entity = (EntityPigeon) parWorld.getEntityByID(parY);
			return new ContainerPigeon(parPlayer, entity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int parID, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ)
	{
		switch(parID)
		{
		case 0:
			EntityPigeon entity = (EntityPigeon) parWorld.getEntityByID(parY);
			return new GuiPigeon(parPlayer, entity);
		}
		return null;
	}
}
