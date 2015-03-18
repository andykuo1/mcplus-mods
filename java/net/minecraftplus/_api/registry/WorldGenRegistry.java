package net.minecraftplus._api.registry;

import net.minecraftplus._api.handler.WorldGenHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Registry for {@link WorldGenHandler} as world generation handlers
 * */
public class WorldGenRegistry extends RegistryList<WorldGenHandler>
{
	public static final WorldGenRegistry INSTANCE = new WorldGenRegistry();

	//**************** Registry Setup ****************//

	@Override
	public void initialize()
	{
		for(WorldGenHandler worldGen : this.registry)
		{
			this.register(worldGen, worldGen.getGenWeight());
		}
	}

	//**************** Base Functions ****************//

	@Override
	public WorldGenHandler add(WorldGenHandler parWorldGenHandler)
	{
		return super.add(parWorldGenHandler);
	}

	//**************** Register Functions ****************//

	public void register(IWorldGenerator parWorldGen, int parWeight)
	{
		GameRegistry.registerWorldGenerator(parWorldGen, parWeight);
	}
}
