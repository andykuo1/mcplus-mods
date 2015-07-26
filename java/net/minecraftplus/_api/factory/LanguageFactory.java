package net.minecraftplus._api.factory;

import java.util.Iterator;

import net.minecraftplus._api.dictionary.entry.Language;
import net.minecraftplus._api.factory.writer.ResourceWriter;

/**Create language packs*/
public class LanguageFactory
{
	public static void write(String parDirectory, String parFileName, Language parLanguage)
	{
		StringBuilder str = new StringBuilder();

		Iterator<String> iter = parLanguage.iterator();
		while(iter.hasNext())
		{
			String unlocalized = iter.next();
			str.append(unlocalized + "=" + parLanguage.get(unlocalized) + "\n");
		}

		ResourceWriter.write(parDirectory, parFileName, str.toString());
	}
}
