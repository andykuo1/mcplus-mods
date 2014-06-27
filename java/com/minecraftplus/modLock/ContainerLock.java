package com.minecraftplus.modLock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import com.minecraftplus._base.MCP;

public class ContainerLock extends Container
{
	public EntityLock entityLock;
	public EntityPlayer entityPlayer;

	public ContainerLock(EntityPlayer player, EntityLock entity)
	{
		this.entityPlayer = player;
		this.entityLock = entity;
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return par1EntityPlayer.inventory.hasItem(MCP_Lock.lockpick) || par1EntityPlayer.capabilities.isCreativeMode;
	}

	public static void sendLockOpenPacket(EntityPlayer par1EntityPlayer, int par2, int par3)
	{
		MCP.packetHandler.sendToServer(new PacketLockOpen(par1EntityPlayer, par2, par3));
	}
}
