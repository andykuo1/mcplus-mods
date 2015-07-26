package net.minecraftplus._api.util.collection;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

public class SmallSet<Value> extends AbstractSet<Value>
{
	private ArrayList<Value> values = null;

	public SmallSet()
	{
		this.values = new ArrayList<Value>();
	}

	public Value get(int parIndex)
	{
		return this.values.get(parIndex);
	}

	public int indexOf(Value parValue)
	{
		return this.values.indexOf(parValue);
	}

	@Override
	public boolean add(Value parValue)
	{
		if (this.values.contains(parValue)) return false;
		return this.values.add(parValue);
	}

	public boolean add(int parIndex, Value parValue)
	{
		if (this.values.contains(parValue)) return false;
		this.values.add(parIndex, parValue);
		return true;
	}

	@Override
	public boolean remove(Object parValue)
	{
		return this.values.remove(parValue);
	}

	@Override
	public boolean contains(Object parValue)
	{
		for(Value value : this.values)
		{
			if (parValue == null ? value == null : parValue.equals(value)) return true;
		}

		return false;
	}

	@Override
	public Iterator<Value> iterator()
	{
		return new Iterator<Value>() {
			private int posNext = 0;

			@Override
			public boolean hasNext()
			{
				return this.posNext < SmallSet.this.size();
			}

			@Override
			public Value next()
			{
				int pos = this.posNext++;
				return SmallSet.this.values.get(pos);
			}

			@Override
			public void remove()
			{
				int pos = --this.posNext;
				SmallSet.this.values.remove(pos);
			}};
	}

	@Override
	public int size()
	{
		return (SmallSet.this.values == null) ? 0 : SmallSet.this.values.size();
	}

	public ArrayList<Value> toArrayList()
	{
		return this.values;
	}
}