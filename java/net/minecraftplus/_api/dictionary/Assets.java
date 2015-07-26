package net.minecraftplus._api.dictionary;

import net.minecraftplus._api.dictionary.entry.Language;

public final class Assets
{
	private Assets() {}

	//For More Types:
	//	@EnumDyeColor
	//	@EnumParticleTypes

	public static final Language EN_US = new Language("en_US");

	public static final String resource(String parModID, String parResource)
	{
		return parModID + ":" + parResource;
	}

	public static final String javaDirectory()
	{
		return "..\\src\\main\\java";
	}

	public static final String directory()
	{
		return "..\\src\\main\\resources\\assets";
	}
}
