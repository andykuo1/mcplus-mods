package net.minecraftplus._api.tool;


public class LangTool
{
	public static String trim(String parString)
	{
		parString = parString.replace("item.", "");
		parString = parString.replace("tile.", "");
		parString = parString.replace("entity.", "");
		parString = parString.replace("container.", "");
		parString = parString.replace("itemGroup.", "");
		parString = parString.replace(".name", "");

		return parString;
	}

	public static String translate(String parUnlocalName)
	{
		parUnlocalName = trim(parUnlocalName);

		boolean toUpperCase = true;
		for(int i = 0; i < parUnlocalName.length(); i++)
		{
			char c = parUnlocalName.charAt(i);

			if (Character.isUpperCase(c) && (i > 0 && parUnlocalName.charAt(i - 1) != ' '))
			{
				parUnlocalName = parUnlocalName.substring(0, i) + " " + parUnlocalName.substring(i);
				toUpperCase = false;
			}

			if (toUpperCase)
			{
				parUnlocalName = parUnlocalName.substring(0, i) + Character.toUpperCase(c) + parUnlocalName.substring(i + 1);
				toUpperCase = false;
			}

			if (c == '_')
			{
				parUnlocalName = parUnlocalName.substring(0, i) + " " + parUnlocalName.substring(i + 1);
				toUpperCase = true;
			}
		}

		return parUnlocalName;
	}

	public static String untranslate(String parString)
	{
		for(int i = 1; i < parString.length() - 1; i++)
		{
			if (Character.isUpperCase(parString.charAt(i)) && parString.charAt(i - 1) != ' ')
			{
				parString = parString.substring(0, i) + " " + parString.substring(i);
				i++;
			}
		}

		return parString.replaceAll(" ", "_").toLowerCase();
	}
}
