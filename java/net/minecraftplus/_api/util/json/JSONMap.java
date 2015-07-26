package net.minecraftplus._api.util.json;

import java.util.Iterator;
import java.util.Map;

import net.minecraftplus._api.util.collection.SmallMap;

public class JSONMap
{
	private final Map<String, Object> map = new SmallMap<String, Object>();

	public final Object get(String parID)
	{
		return this.map.get(parID);
	}

	public final Iterator<String> iterator()
	{
		return this.map.keySet().iterator();
	}

	public final JSONMap put(String parID, String parString)
	{
		this.map.put(parID, parString);
		return this;
	}

	public final JSONMap put(String parID, Object parObject)
	{
		this.map.put(parID, parObject);
		return this;
	}

	public final JSONMap put(String parID, JSONMap parJSONMap)
	{
		this.map.put(parID, parJSONMap);
		return this;
	}

	public final JSONMap put(String parID, int parInt)
	{
		this.map.put(parID, parInt);
		return this;
	}

	public final JSONMap put(String parID, float parFloat)
	{
		this.map.put(parID, parFloat);
		return this;
	}

	public final JSONMap put(String parID, boolean parBoolean)
	{
		this.map.put(parID, parBoolean);
		return this;
	}

	public final JSONMap put(String parID, String... parArray)
	{
		this.map.put(parID, parArray);
		return this;
	}

	public final JSONMap put(String parID, Object[] parArray)
	{
		this.map.put(parID, parArray);
		return this;
	}

	public final JSONMap put(String parID, JSONMap... parArray)
	{
		this.map.put(parID, parArray);
		return this;
	}

	public final JSONMap put(String parID, int... parArray)
	{
		this.map.put(parID, parArray);
		return this;
	}

	public final JSONMap put(String parID, float... parArray)
	{
		this.map.put(parID, parArray);
		return this;
	}

	public final JSONMap put(String parID, boolean... parArray)
	{
		this.map.put(parID, parArray);
		return this;
	}

	public final JSON toJSON()
	{
		return this.toJSON(new JSON());
	}

	public final JSON toJSON(JSON parJSON)
	{
		JSON json = parJSON;
		Iterator<String> iter = this.iterator();
		while(iter.hasNext())
		{
			String id = iter.next();
			Object val = this.get(id);

			if (val instanceof String)
			{
				json.entry(id, (String) val);
			}
			else if (val instanceof JSONMap)
			{
				json.entry(id, ((JSONMap) val).toJSON(new JSON()));
			}
			else if (val instanceof Integer)
			{
				json.entry(id, (int)((Integer) val));
			}
			else if (val instanceof Float)
			{
				json.entry(id, (float)((Float) val));
			}
			else if (val instanceof Boolean)
			{
				json.entry(id, (boolean)((Boolean) val));
			}
			else if (val instanceof String[])
			{
				json.entry(id, (String[]) val);
			}
			else if (val instanceof JSONMap[])
			{
				JSON[] jsons = new JSON[((JSONMap[]) val).length];
				for(int i = 0; i < jsons.length; ++i)
				{
					jsons[i] = ((JSONMap[]) val)[i].toJSON(new JSON());
				}
				json.entry(id, jsons);
			}
			else if (val instanceof int[])
			{
				json.entry(id, (int[]) val);
			}
			else if (val instanceof float[])
			{
				json.entry(id, (float[]) val);
			}
			else if (val instanceof int[])
			{
				json.entry(id, (boolean[]) val);
			}
			else if (val instanceof Object[])
			{
				json.entry(id, (Object[]) val);
			}
			else if (val instanceof Object)
			{
				json.entry(id, (Object) val);
			}
		}

		return json;
	}
}
