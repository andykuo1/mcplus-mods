package net.minecraftplus._api.dictionary.entry;

import java.util.Iterator;
import java.util.Map;

import net.minecraftplus._api.util.collection.SmallMap;

/**Holds localization data for a single language pack*/
public final class Language
{
	/**Map to hold localization strings*/
	private final Map<String, String> langMap = new SmallMap<String, String>();

	/**Name of the language pack*/
	private final String langPack;

	/**Constructs as passed-in language pack*/
	public Language(String parLangPack)
	{
		this.langPack = parLangPack;
	}

	/**Put string to localization value in language mapping*/
	public final void put(String parID, String parValue)
	{
		this.langMap.put(parID, parValue);
	}

	/**Get localized string by id from language mapping*/
	public final String get(String parID)
	{
		return this.langMap.get(parID);
	}

	/**Iterator of keys for the language mapping*/
	public Iterator<String> iterator()
	{
		return this.langMap.keySet().iterator();
	}
	
	@Override
	public String toString()
	{
		return this.langPack;
	}
}
