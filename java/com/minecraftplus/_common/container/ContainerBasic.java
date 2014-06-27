package com.minecraftplus._common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class ContainerBasic extends Container
{
	public final EntityPlayer thePlayer;

	public ContainerBasic(EntityPlayer par1EntityPlayer)
	{
		this.thePlayer = par1EntityPlayer;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}
}
