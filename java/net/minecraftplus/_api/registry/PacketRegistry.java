package net.minecraftplus._api.registry;

import net.minecraftplus._api.handler.Packet;
import net.minecraftplus._api.handler.PacketHandler;
import net.minecraftplus._api.mod.MCP;

/**
 * Registry for {@link Packet} as server and client packets
 * */
public class PacketRegistry extends Registry<PacketHandler>
{
	public static final PacketRegistry INSTANCE = new PacketRegistry();

	//**************** Registry Setup ****************//

	public PacketRegistry()
	{
		super(new PacketHandler(MCP.MODID));
	}

	@Override
	public void initialize()
	{
		this.registry.initialize();
	}

	public void postInitialize()
	{
		this.registry.postInitialize();
	}

	public PacketHandler getHandler()
	{
		return this.registry;
	}

	//**************** Base Functions ****************//

	public void add(Class<? extends Packet> parPacket)
	{
		this.registry.registerPacket(parPacket);
	}
}
