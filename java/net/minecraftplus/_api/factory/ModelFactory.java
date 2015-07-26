package net.minecraftplus._api.factory;

import net.minecraftplus._api.factory.writer.ResourceWriter;
import net.minecraftplus._api.util.json.JSON;

/**Create json model files*/
public class ModelFactory
{
	public static void write(String parDirectory, String parFileName, JSON parJSON)
	{
		ResourceWriter.write(parDirectory, parFileName, parJSON.toString());
	}
}
