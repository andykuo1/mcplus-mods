package net.minecraftplus.mcp_ice_box;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerIceBox implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int parID, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ)
	{
		TileEntity tileentity = parWorld.getTileEntity(new BlockPos(parX, parY, parZ));
		if (tileentity != null)
		{
			switch(parID)
			{
			case 0:
				return new ContainerIceBox(parPlayer.inventory, ((TileEntityIceBox)tileentity));
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int parID, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ)
	{
		TileEntity tileentity = parWorld.getTileEntity(new BlockPos(parX, parY, parZ));
		if (tileentity != null)
		{
			switch(parID)
			{
			case 0:
				return new GuiIceBox(parPlayer.inventory, ((TileEntityIceBox)tileentity));
			}
		}
		return null;
	}
}
