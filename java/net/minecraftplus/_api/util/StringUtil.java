package net.minecraftplus._api.util;

public class StringUtil
{
	public static final String camelToUnderscore(String parString)
	{
		StringBuilder str = new StringBuilder();
		str.append(Character.toLowerCase(parString.charAt(0)));
		for(int i = 1; i < parString.length(); ++i)
		{
			char c = parString.charAt(i);
			if (Character.isUpperCase(c))
			{
				str.append("_");
			}
			str.append(Character.toLowerCase(c));
		}

		return str.toString();
	}

	public static final String joinWith(Object[] parStrings, String parSeperator)
	{
		assert(parStrings != null);
		assert(parStrings.length > 0);

		String result = parStrings[0].toString();
		for(int i = 1; i < parStrings.length; ++i)
		{
			result += parSeperator + parStrings[i].toString();
		}

		return result;
	}

	public static final String[] toStrings(Object[] parObjects)
	{
		String[] strs = new String[parObjects.length];
		for(int i = 0; i < parObjects.length; ++i)
		{
			strs[i] = "" + parObjects[i];
		}
		return strs;
	}
}
