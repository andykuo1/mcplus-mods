package net.minecraftplus.cart.cargo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class ContainerCargoWorkbench extends ContainerWorkbench
{
	public ContainerCargoWorkbench(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
	{
		super(par1InventoryPlayer, par2World, par3, par4, par5);
	}

	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return true;
	}
}
