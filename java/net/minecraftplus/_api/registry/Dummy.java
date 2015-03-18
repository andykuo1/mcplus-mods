package net.minecraftplus._api.registry;

/**Object to register in-place of loose data.
 * <br>
 * <br>Used by any {@link Registry} object.
 * */
public class Dummy<ID>
{
	private final ID id;

	public Dummy(ID parID)
	{
		this.id = parID;
	}

	/**
	 * Returns id of dummy
	 * 
	 * @return id
	 * */
	protected final ID id()
	{
		return this.id;	
	}

	@Override
	public String toString()
	{
		return this.id.toString();
	}
}