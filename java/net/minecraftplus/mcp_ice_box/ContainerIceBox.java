package net.minecraftplus.mcp_ice_box;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.IInventory;

public class ContainerIceBox extends ContainerDispenser
{
	public IInventory dispenser;

	public ContainerIceBox(IInventory playerInventory, IInventory dispenserInventoryIn)
	{
		super(playerInventory, dispenserInventoryIn);
		this.dispenser = dispenserInventoryIn;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return super.canInteractWith(playerIn);
	}
}
