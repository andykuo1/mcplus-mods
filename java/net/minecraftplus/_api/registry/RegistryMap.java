package net.minecraftplus._api.registry;

import java.util.HashMap;
import java.util.Map;

/**Registry as {@link HashMap} for any entry*/
public abstract class RegistryMap<Key, Value> extends Registry<Map<Key, Value>>{

	public RegistryMap()
	{
		super(new HashMap<Key, Value>());
	}

	@Override
	public abstract void initialize();

	/**Adds the value to key in the registry.
	 * <br>
	 * <br>Should be overriden!
	 * 
	 * @param parKey the key to register
	 * @param parValue the value to register
	 * 
	 * @return the key registered
	 * */
	protected Key add(Key parKey, Value parValue)
	{
		this.registry.put(parKey, parValue);
		return parKey;
	}

	/**Gets the key by:
	 * <br>
	 * <br><code>key.equals(parKey)</code>.
	 * <br>
	 * <br>Should be overriden!
	 * 
	 * @param parKey the key to equal
	 * 
	 * @return the key equal to
	 * */
	protected Key getKey(Object parKey)
	{
		for(Key key : this.registry.keySet())
		{
			if (key.equals(parKey))
			{
				return key;
			}
		}

		return null;
	}

	/**Gets the value by:
	 * <br>
	 * <br><code>value.equals(parValue)</code>.
	 * <br>
	 * <br>Should be overriden!
	 * 
	 * @param parValue the value to equal
	 * 
	 * @return the value equal to
	 * */
	protected Value getValue(Object parValue)
	{
		for(Value value : this.registry.values())
		{
			if (value.equals(parValue))
			{
				return value;
			}
		}

		return null;
	}

	public void clear()
	{
		this.registry.clear();
	}
}
