package net.minecraftplus.battlehearts;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandMaxHealth extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "setmaxhealth";
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_)
	{
		return "commands.setmaxhealth.usage";
	}

	@Override
	public void processCommand(ICommandSender par1ICommandSender, String[] par2)
	{
		if (par2.length == 1 && par2[0].length() > 0)
		{
			EntityPlayerMP entityplayermp = getCommandSenderAsPlayer(par1ICommandSender);

			if (entityplayermp == null)
			{
				throw new PlayerNotFoundException();
			}
			else
			{
				int i = parseIntBounded(par1ICommandSender, par2[0], 0, 1000);

				EventHealthHandler.setExtendedHealth(entityplayermp, i * 2);
				EventHealthHandler.setMaxHealth(entityplayermp, EventHealthHandler.getExtendedHealth(entityplayermp));
				entityplayermp.setHealth(entityplayermp.getMaxHealth());
			}
		}
		else if (par2.length == 2 && par2[0].length() > 0 && par2[1].length() > 0)
		{
			EntityPlayerMP entityplayermp = getPlayer(par1ICommandSender, par2[0]);

			if (entityplayermp == null)
			{
				throw new PlayerNotFoundException();
			}
			else
			{
				int i = parseIntBounded(par1ICommandSender, par2[1], 0, 1000);

				EventHealthHandler.setExtendedHealth(entityplayermp, i * 2);
				EventHealthHandler.setMaxHealth(entityplayermp, EventHealthHandler.getExtendedHealth(entityplayermp));
				entityplayermp.setHealth(entityplayermp.getMaxHealth());
			}
		}
		else
		{
			throw new WrongUsageException("commands.setmaxhealth.usage", new Object[0]);
		}
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
	{
		return p_71516_2_.length != 2 ? getListOfStringsMatchingLastWord(p_71516_2_, MinecraftServer.getServer().getAllUsernames()) : null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
	{
		return p_82358_2_ == 0;
	}
}
