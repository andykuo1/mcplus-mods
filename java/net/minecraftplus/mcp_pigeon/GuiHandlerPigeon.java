package net.minecraftplus.mcp_pigeon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerPigeon implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
		case 0:
			EntityPigeon entity = (EntityPigeon) world.getEntityByID(y);
			return new ContainerPigeon(player, entity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
		case 0:
			EntityPigeon entity = (EntityPigeon) world.getEntityByID(y);
			return new GuiPigeon(player, entity);
		}
		return null;
	}

}
