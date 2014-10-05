package com.minecraftplus._base.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;

public class CommandRegistry
{
	private static final List<ICommand> COMMAND_QUEUE = new ArrayList<ICommand>();

	public static void add(ICommand par1ICommand)
	{
		COMMAND_QUEUE.add(par1ICommand);
	}

	public static void remove(ICommand par1ICommand)
	{
		COMMAND_QUEUE.remove(par1ICommand);
	}

	public static List<ICommand> getCommandList()
	{
		return COMMAND_QUEUE;
	}
}
