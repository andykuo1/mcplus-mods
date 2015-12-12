package net.minecraftplus.mcp_loom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandlerLoom implements IGuiHandler
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
				return new ContainerLoom(parPlayer.inventory, ((TileEntityLoom)tileentity));
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
				return new GuiLoom(parPlayer.inventory, ((TileEntityLoom)tileentity));
			}
		}
		return null;
	}
}
