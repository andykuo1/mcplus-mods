package net.minecraftplus._api.util.collection;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SmallMap<Key, Value> extends AbstractMap<Key, Value>
{
	private ArrayList<Key> keys = null;
	private ArrayList<Value> values = null;

	private ArrayList<Key> keyArray()
	{
		if (this.keys == null)
		{
			this.keys = new ArrayList<Key>(0);
		}

		return this.keys;
	}

	private ArrayList<Value> valueArray()
	{
		if (this.values == null)
		{
			this.values = new ArrayList<Value>(0);
		}

		return this.values;
	}

	public SmallMap() {}

	public SmallMap(Map<? extends Key, ? extends Value> parMap)
	{
		putAllInternal(this.keyArray(), this.valueArray(), parMap);
	}

	private static <Key, Value> void putAllInternal(ArrayList<Key> parKeys, ArrayList<Value> parValues, Map<? extends Key, ? extends Value> parMap)
	{
		for (Iterator<? extends Map.Entry<? extends Key, ? extends Value>> i = parMap.entrySet().iterator(); i.hasNext();)
		{
			Map.Entry<? extends Key, ? extends Value> entry = i.next();
			putInternal(parKeys, parValues, entry.getKey(), entry.getValue());
		}
	}

	private static <Key, Value> Value putInternal(ArrayList<Key> parKeys, ArrayList<Value> parValues, Key parKey, Value parValue)
	{
		int i = parKeys.indexOf(parKey);
		if (i == -1)
		{
			parKeys.add(parKey);
			parValues.add(parValue);
			return null;
		}
		else
		{
			return parValues.set(i, parValue);
		}
	}

	@Override
	public Value put(Key parKey, Value parValue)
	{
		return putInternal(this.keyArray(), this.valueArray(), parKey, parValue);
	}

	@Override
	public Set<Entry<Key, Value>> entrySet()
	{
		return new AbstractSet<Entry<Key, Value>>() {
			@Override
			public Iterator<Entry<Key, Value>> iterator()
			{
				return new Iterator<Entry<Key, Value>>(){

					private int posNext = 0;

					@Override
					public boolean hasNext()
					{
						return this.posNext < SmallMap.this.size();
					}

					@Override
					public Entry<Key, Value> next()
					{
						int pos = this.posNext++;
						return new SimpleEntry<Key, Value>(SmallMap.this.keys.get(pos), SmallMap.this.values.get(pos));
					}

					@Override
					public void remove()
					{
						int pos = --this.posNext;
						SmallMap.this.keys.remove(pos);
						SmallMap.this.values.remove(pos);
					}};
			}

			@Override
			public int size()
			{
				return (SmallMap.this.keys == null) ? 0 : SmallMap.this.keys.size();
			}};
	}

}