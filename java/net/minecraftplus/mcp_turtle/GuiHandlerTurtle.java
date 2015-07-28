package net.minecraftplus.mcp_turtle;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerTurtle implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
		case 0:
			EntityTurtle entity = (EntityTurtle) world.getEntityByID(y);
			return new ContainerTurtle(player, entity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
		case 0:
			EntityTurtle entity = (EntityTurtle) world.getEntityByID(y);
			return new GuiTurtle(player, entity);
		}
		return null;
	}

}
