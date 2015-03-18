package net.minecraftplus.cart;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.world.World;
import net.minecraftplus.cart.cargo.CargoFurnace;
import net.minecraftplus.cart.cargo.ContainerCargoFurnace;
import net.minecraftplus.cart.cargo.ContainerCargoWorkbench;
import net.minecraftplus.cart.cargo.GuiCargoFurnace;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		switch (par1)
		{
		case 0:
			Entity entity = par3World.getEntityByID(y);
			return entity != null ? new ContainerChest(par2EntityPlayer.inventory, ((EntityCart) entity).getCargoInventory()) : null;
		case 1:
			Entity entity1 = par3World.getEntityByID(y);
			return entity1 != null ? new ContainerCargoWorkbench(par2EntityPlayer.inventory, par3World, x, 1, z) : null;
		case 4:
			Entity entity2 = par3World.getEntityByID(y);
			return entity2 != null ? new ContainerCargoFurnace(par2EntityPlayer.inventory, (CargoFurnace) ((EntityCart) entity2).getCargo()) : null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int par1, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z)
	{
		switch (par1)
		{
		case 0:
			Entity entity = par3World.getEntityByID(y);
			return entity != null ? new GuiChest(par2EntityPlayer.inventory, ((EntityCart) entity).getCargoInventory()) : null;
		case 1:
			Entity entity1 = par3World.getEntityByID(y);
			return entity1 != null ? new GuiCrafting(par2EntityPlayer.inventory, par3World, x, 1, z) : null;
		case 4:
			Entity entity2 = par3World.getEntityByID(y);
			return entity2 != null ? new GuiCargoFurnace(par2EntityPlayer.inventory, (CargoFurnace) ((EntityCart) entity2).getCargo()) : null;
		}
		return null;
	}
}
