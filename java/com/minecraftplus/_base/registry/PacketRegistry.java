package com.minecraftplus._base.registry;

import com.minecraftplus._base.MCP;
import com.minecraftplus._common.packet.Packet;
import com.minecraftplus._common.packet.PacketHandler;

public class PacketRegistry
{
	private static PacketHandler handler = new PacketHandler(MCP.MODID);

	public static void add(Class<? extends Packet> par1Class)
	{
		handler.registerPacket(par1Class);
	}

	public static PacketHandler getPacketHandler()
	{
		return handler;
	}
}
