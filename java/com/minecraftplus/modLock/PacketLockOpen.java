package com.minecraftplus.modLock;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import com.minecraftplus._common.packet.Packet;

public class PacketLockOpen extends Packet
{
	private EntityPlayer entityplayer;
	private int windowID;
	private int action;

	public PacketLockOpen() {}

	public PacketLockOpen(EntityPlayer par1EntityPlayer, int par2WindowID, int par3Action)
	{
		this.entityplayer = par1EntityPlayer;
		this.windowID = par2WindowID;
		this.action = par3Action;
	}

	@Override
	public void encode(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeInt(this.windowID);
		buffer.writeInt(this.action);
	}

	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		this.windowID = buffer.readInt();
		this.action = buffer.readInt();
	}

	@Override
	public void onClientSide(EntityPlayer player)
	{

	}

	@Override
	public void onServerSide(EntityPlayer player)
	{
		((EntityPlayerMP) player).func_143004_u();

		if (player.openContainer instanceof ContainerLock && player.openContainer.windowId == this.windowID && player.openContainer.isPlayerNotUsingContainer(player))
		{
			if (this.action == 1)
			{
				((ContainerLock) player.openContainer).entityLock.breakOpenLock(player);
			}
			else
			{
				((ContainerLock) player.openContainer).entityLock.breakCloseLock(player);
			}

			player.openContainer.detectAndSendChanges();
		}
	}
}
