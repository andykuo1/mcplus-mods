package com.minecraftplus.modSoulExtractor;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

import com.minecraftplus._common.packet.Packet;

public class PacketAbsorbSoul extends Packet
{
	private EntityPlayer entityplayer;
	private int windowID;

	public PacketAbsorbSoul() {}

	public PacketAbsorbSoul(EntityPlayer par1EntityPlayer, int par2WindowID)
	{
		this.entityplayer = par1EntityPlayer;
		this.windowID = par2WindowID;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeInt(this.windowID);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		int i = buffer.readInt();
		this.windowID = i;
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		((EntityPlayerMP) player).func_143004_u();

		if (player.openContainer.windowId == this.windowID && player.openContainer.isPlayerNotUsingContainer(player))
		{
			((ContainerSoulExtractor) player.openContainer).soulExtractor.absorbSoul(player);
			player.openContainer.detectAndSendChanges();
		}
	}
}
