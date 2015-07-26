package net.minecraftplus._api.minecraft;

public final class PacketHandler
{
	public static final PacketHandler INSTANCE = new PacketHandler();
	public static boolean isLocked() { return INSTANCE.channel.isInitialized(); }

	private PacketHandler() {}

	private PacketChannel channel = new PacketChannel("minecraftplus");

	public void add(Class<? extends Packet> parPacketClass)
	{
		assert(!isLocked());

		this.channel.registerPacket(parPacketClass);
	}

	public PacketChannel getChannel()
	{
		return this.channel;
	}
}
