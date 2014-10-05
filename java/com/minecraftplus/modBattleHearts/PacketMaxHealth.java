package com.minecraftplus.modBattleHearts;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

import com.minecraftplus._common.packet.Packet;

public class PacketMaxHealth extends Packet
{
	private EntityPlayer entityplayer;
	private int maxHealth;
	private float health;

	public PacketMaxHealth() {}

	public PacketMaxHealth(EntityPlayer par1EntityPlayer)
	{
		this.entityplayer = par1EntityPlayer;
	}

	@Override
	public void encode(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeInt(EventMaxHealthHandler.getMaxHealthData(this.entityplayer));
		buffer.writeFloat(this.entityplayer.getHealth());
	}

	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		this.maxHealth = buffer.readInt();
		this.health = buffer.readInt();
	}

	@Override
	public void onClientSide(EntityPlayer player)
	{
		EventMaxHealthHandler.setMaxHealthData(player, this.maxHealth);
		EventMaxHealthHandler.updateMaxHealthFromData(player);
		player.setHealth(this.health);
	}

	@Override
	public void onServerSide(EntityPlayer player) {}
}
