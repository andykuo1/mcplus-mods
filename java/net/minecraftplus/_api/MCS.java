package net.minecraftplus._api;

import net.minecraft.command.CommandBase;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftplus._api.minecraft.CommandHandler;

/**Minecraft Server*/
public final class MCS
{
	private MCS() {}

	/**Register the entity class with passed-in name to a global unique entity id*/
	public static final void entity(Class<? extends Entity> parEntity, String parEntityName)
	{
		EntityRegistry.registerGlobalEntityID(parEntity, parEntityName, EntityRegistry.findGlobalUniqueEntityId());
	}

	/**Register the entity class with passed-in name and spawn egg to a global unique entity id*/
	public static final void entity(Class<? extends Entity> parEntity, String parEntityName, int parBackColor, int parForeColor)
	{
		EntityRegistry.registerGlobalEntityID(parEntity, parEntityName, EntityRegistry.findGlobalUniqueEntityId(), parBackColor, parForeColor);
	}

	/**Register the command with the server*/
	public static final CommandBase command(CommandBase parCommand)
	{
		assert(!CommandHandler.isLocked());

		return CommandHandler.INSTANCE.add(parCommand);
	}
}
