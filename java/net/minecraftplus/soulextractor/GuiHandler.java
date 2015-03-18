package net.minecraftplus.soulextractor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		TileEntity tileentity = par3World.getTileEntity(x, y, z);
		if (tileentity != null)
		{
			switch (par1)
			{
			case 0:
				return new ContainerSoulExtractor(par2EntityPlayer, (TileEntitySoulExtractor) tileentity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		TileEntity tileentity = par3World.getTileEntity(x, y, z);
		if (tileentity != null)
		{
			switch (par1)
			{
			case 0:
				return new GuiSoulExtractor(par2EntityPlayer, (TileEntitySoulExtractor) tileentity);
			}
		}
		return null;
	}
}