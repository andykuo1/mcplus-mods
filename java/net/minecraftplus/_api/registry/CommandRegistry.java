package net.minecraftplus._api.registry;

import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;

/**
 * Registry for {@link ICommand} as in-game commands
 * */
public class CommandRegistry extends RegistryList<ICommand>
{
	public static final CommandRegistry INSTANCE = new CommandRegistry();

	//**************** Registry Setup ****************//

	@Override
	public void initialize()
	{
		ServerCommandManager manager = (ServerCommandManager) MinecraftServer.getServer().getCommandManager();
		for(ICommand command : this.registry)
		{
			manager.registerCommand(command);
		}
	}

	//**************** Base Functions ****************//

	@Override
	public ICommand add(ICommand parCommand)
	{
		return super.add(parCommand);
	}
}