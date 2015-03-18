package net.minecraftplus._api.registry;

/**
 * Registry for any type
 * */
public abstract class Registry<RegistryType>
{
	/**The registry to hold entries*/
	protected final RegistryType registry;

	/**Construct the registry as passed-in object
	 * 
	 * @param parRegistry the new registry
	 * */
	public Registry(RegistryType parRegistry)
	{
		this.registry = parRegistry;
	}

	/**
	 * Initialize all objects in registry.
	 * <br>
	 * <br>Will <b>FINALIZE</b> the registry!
	 * */
	public abstract void initialize();
}
