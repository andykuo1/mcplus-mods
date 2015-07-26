package net.minecraftplus.mcp_battle_hearts;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftplus._api.minecraft.Packet;

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
		buffer.writeInt(EventHandlerBattleHearts.getExtendedHealth(this.entityplayer));
		buffer.writeFloat(this.entityplayer.getHealth());
	}

	@Override
	public void decode(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		this.maxHealth = buffer.readInt();
		this.health = buffer.readFloat();
	}

	@Override
	public void onClientSide(EntityPlayer parEntityPlayer)
	{
		EventHandlerBattleHearts.setExtendedHealth(parEntityPlayer, this.maxHealth);
		EventHandlerBattleHearts.setMaxHealth(parEntityPlayer, EventHandlerBattleHearts.getExtendedHealth(parEntityPlayer));
		parEntityPlayer.setHealth(this.health);
	}

	@Override
	public void onServerSide(EntityPlayer player) {}
}
