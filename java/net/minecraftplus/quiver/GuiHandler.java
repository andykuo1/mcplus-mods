package net.minecraftplus.quiver;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
			ItemStack quiver = parY == 0 ? parPlayer.getCurrentEquippedItem() : parPlayer.getCurrentArmor(1);
			return new ContainerQuiver(parPlayer, quiver);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int parID, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ)
	{
		switch(parID)
		{
		case 0:
			ItemStack quiver = parY == 0 ? parPlayer.getCurrentEquippedItem() : parPlayer.getCurrentArmor(1);
			return new GuiQuiver(parPlayer, quiver);
		}
		return null;
	}
}