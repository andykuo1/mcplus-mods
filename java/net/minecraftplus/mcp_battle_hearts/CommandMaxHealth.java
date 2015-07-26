package net.minecraftplus.mcp_battle_hearts;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandMaxHealth extends CommandBase
{
	@Override
	public String getName()
	{
		return "setmaxhealth";
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender parSender)
	{
		return "commands.setmaxhealth.usage";
	}

	@Override
	public void execute(ICommandSender parSender, String[] parCommand) throws PlayerNotFoundException, NumberInvalidException, WrongUsageException
	{
		if (parCommand.length == 1 && parCommand[0].length() > 0)
		{
			EntityPlayerMP entityplayermp = getCommandSenderAsPlayer(parSender);

			if (entityplayermp == null)
			{
				throw new PlayerNotFoundException();
			}
			else
			{
				int i = parseInt(parCommand[0], 4, 1000);

				EventHandlerBattleHearts.setExtendedHealth(entityplayermp, (i * 2) - 8);
				EventHandlerBattleHearts.setMaxHealth(entityplayermp, EventHandlerBattleHearts.getExtendedHealth(entityplayermp));
				entityplayermp.setHealth(entityplayermp.getMaxHealth());
			}
		}
		else if (parCommand.length == 2 && parCommand[0].length() > 0 && parCommand[1].length() > 0)
		{
			EntityPlayerMP entityplayermp = getPlayer(parSender, parCommand[0]);

			if (entityplayermp == null)
			{
				throw new PlayerNotFoundException();
			}
			else
			{
				int i = parseInt(parCommand[1], 4, 1000);

				EventHandlerBattleHearts.setExtendedHealth(entityplayermp, (i * 2) - 8);
				EventHandlerBattleHearts.setMaxHealth(entityplayermp, EventHandlerBattleHearts.getExtendedHealth(entityplayermp));
				entityplayermp.setHealth(entityplayermp.getMaxHealth());
			}
		}
		else
		{
			throw new WrongUsageException("commands.setmaxhealth.usage", new Object[0]);
		}
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
	{
		return args.length != 2 ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
	{
		return p_82358_2_ == 0;
	}
}
