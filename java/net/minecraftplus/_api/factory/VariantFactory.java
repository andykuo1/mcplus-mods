package net.minecraftplus._api.factory;

import net.minecraftplus._api.dictionary.Assets;
import net.minecraftplus._api.factory.writer.ResourceWriter;
import net.minecraftplus._api.util.json.JSON;

/**Create json variant files*/
public class VariantFactory
{
	public static void write(String parDirectory, String parFileName, JSON parJSON)
	{
		ResourceWriter.write(parDirectory, parFileName, parJSON.toString());
	}
}
