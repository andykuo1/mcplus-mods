package net.minecraftplus._api.util.json;

public final class JSON
{
	private final StringBuilder str = new StringBuilder();
	private int cursor;

	public JSON()
	{
		this.begin();
	}

	public final JSON entry(String parID, String parString)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add("\"" + parString + "\"").add(',').line();
	}

	public final JSON entry(String parID, Object parObject)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add("\"" + parObject + "\"").add(',').line();
	}

	public final JSON entry(String parID, JSON parJSON)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add(parJSON).add(',').line();
	}

	public final JSON entry(String parID, int parInt)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add("" + parInt).add(',').line();
	}

	public final JSON entry(String parID, float parFloat)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add("" + parFloat).add(',').line();
	}

	public final JSON entry(String parID, boolean parBoolean)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add(parBoolean ? "true" : "false").add(',').line();
	}

	public final JSON entry(String parID, String... parArray)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add(parArray).add(',').line();
	}

	public final JSON entry(String parID, Object[] parArray)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add(parArray).add(',').line();
	}

	public final JSON entry(String parID, JSON... parArray)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add(parArray).add(',').line();
	}

	public final JSON entry(String parID, int... parArray)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add(parArray).add(',').line();
	}

	public final JSON entry(String parID, float... parArray)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add(parArray).add(',').line();
	}

	public final JSON entry(String parID, boolean... parArray)
	{
		return this.tab(this.cursor).add("\"" + parID + "\"" + ": ").add(parArray).add(',').line();
	}

	private final JSON begin()
	{
		return this.tab(this.cursor++).add('{').line();
	}

	private final JSON end()
	{
		return this.del(2).line().tab(--this.cursor).add('}');
	}

	private final JSON add(String parString)
	{
		this.str.append(parString);
		return this;
	}

	private final JSON add(char parCharacter)
	{
		this.str.append(parCharacter);
		return this;
	}

	private final JSON add(JSON parJSON)
	{
		StringBuilder tabs = new StringBuilder();
		for(int i = 0; i < this.cursor; ++i) tabs.append('\t');
		this.str.append(parJSON.toString().replace("\n", "\n" + tabs));
		return this;
	}

	private final JSON add(Object[] parArray)
	{
		this.str.append('[');

		boolean flag = true;
		for(Object obj : parArray)
		{
			if (flag) flag = false;
			else this.str.append(',');
			this.str.append(" \"" + obj.toString() + "\"");
		}

		this.str.append(" ]");
		return this;
	}

	private final JSON add(JSON[] parArray)
	{
		this.str.append('[');

		boolean flag = true;
		for(Object obj : parArray)
		{
			if (flag) flag = false;
			else this.str.append(",");

			StringBuilder tabs = new StringBuilder();
			for(int i = 0; i < this.cursor; ++i) tabs.append('\t');
			this.str.append("\n" + tabs + obj.toString().replace("\n", "\n" + tabs));
		}

		this.str.append(" ]");
		return this;
	}

	private final JSON add(int[] parArray)
	{
		this.str.append('[');

		boolean flag = true;
		for(int obj : parArray)
		{
			if (flag) flag = false;
			else this.str.append(',');
			this.str.append(" " + obj + "");
		}

		this.str.append(" ]");
		return this;
	}

	private final JSON add(float[] parArray)
	{
		this.str.append('[');

		boolean flag = true;
		for(float obj : parArray)
		{
			if (flag) flag = false;
			else this.str.append(',');
			this.str.append(" " + obj + "");
		}

		this.str.append(" ]");
		return this;
	}

	private final JSON add(boolean[] parArray)
	{
		this.str.append('[');

		boolean flag = true;
		for(boolean obj : parArray)
		{
			if (flag) flag = false;
			else this.str.append(',');
			this.str.append(" " + (obj ? "true" : "false") + "");
		}

		this.str.append(" ]");
		return this;
	}

	private final JSON del(int parLength)
	{
		this.str.delete(this.str.length() - parLength, this.str.length());
		return this;
	}

	private final JSON line()
	{
		this.str.append('\n');
		return this;
	}

	private final JSON tab(int parTabs)
	{
		for(int i = 0; i < parTabs; ++i)
		{
			this.str.append('\t');
		}
		return this;
	}

	@Override
	public final String toString()
	{
		this.end();
		return this.str.toString();
	}
}