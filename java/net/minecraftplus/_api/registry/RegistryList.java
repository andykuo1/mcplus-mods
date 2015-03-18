package net.minecraftplus._api.registry;

import java.util.ArrayList;
import java.util.List;

/**Registry as {@link ArrayList} for any entry*/
public abstract class RegistryList<Entry> extends Registry<List<Entry>>
{
	public RegistryList()
	{
		super(new ArrayList<Entry>());
	}

	@Override
	public abstract void initialize();

	/**Adds the entry to the registry.
	 * <br>
	 * <br>Should be overriden!
	 * 
	 * @param parEntry the entry to register
	 * 
	 * @return the entry registered
	 * */
	protected Entry add(Entry parEntry)
	{
		this.registry.add(parEntry);
		return parEntry;
	}

	/**Gets the entry by:
	 * <br>
	 * <br><code>entry.equals(parEntry)</code>.
	 * <br>
	 * <br>Should be overriden!
	 * 
	 * @param parEntry the entry to equal
	 * 
	 * @return the entry equal to
	 * */
	protected Entry get(Object parEntry)
	{
		for(Entry entry : this.registry)
		{
			if (entry.equals(parEntry))
			{
				return entry;
			}
		}

		return null;
	}

	public void clear()
	{
		this.registry.clear();
	}
}
