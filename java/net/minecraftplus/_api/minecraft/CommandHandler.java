package net.minecraftplus._api.minecraft;

import java.util.Iterator;
import java.util.Set;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftplus._api.util.collection.SmallSet;

public final class CommandHandler
{
	public static final CommandHandler INSTANCE = new CommandHandler();
	private static boolean lock = false;
	public static boolean isLocked() { return lock; }
	public static void lock() { lock = true; }

	private CommandHandler() {}

	private final Set<CommandBase> commands = new SmallSet<CommandBase>();

	public CommandBase add(CommandBase parCommand)
	{
		assert(!lock);
		
		this.commands.add(parCommand);
		return parCommand;
	}

	public Iterator<CommandBase> iterator()
	{
		return this.commands.iterator();
	}

	public static ServerCommandManager getCommandManager()
	{
		return (ServerCommandManager) MinecraftServer.getServer().getCommandManager();
	}
}
