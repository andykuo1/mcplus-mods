package net.minecraftplus._api;

import net.minecraft.command.CommandBase;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
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

	/**Register the entity class with passed-in name to mod by id and custom info*/
	public static final void entity(Class<? extends Entity> parEntity, String parEntityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
	{
		EntityRegistry.registerModEntity(parEntity, parEntityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	/**Register the entity class with passed-in name and spawn egg to mod by id and custom info*/
	public static final void entity(Class<? extends Entity> parEntity, String parEntityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int parBackColor, int parForeColor)
	{
		EntityRegistry.registerModEntity(parEntity, parEntityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, parBackColor, parForeColor);
	}

	/**Register the tile entity class with passed-in id*/
	public static final void tileEntity(Class<? extends TileEntity> parEntity, String parEntityID)
	{
		GameRegistry.registerTileEntity(parEntity, parEntityID);
	}

	/**Register the command with the server*/
	public static final CommandBase command(CommandBase parCommand)
	{
		assert(!CommandHandler.isLocked());

		return CommandHandler.INSTANCE.add(parCommand);
	}
}
