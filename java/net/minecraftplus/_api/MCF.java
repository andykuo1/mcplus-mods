package net.minecraftplus._api;

import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftplus._api.base.Proxy;
import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.dictionary.Assets;
import net.minecraftplus._api.factory.LanguageFactory;
import net.minecraftplus._api.factory.ModFactory;
import net.minecraftplus._api.factory.ModelFactory;
import net.minecraftplus._api.factory.ProxyFactory;
import net.minecraftplus._api.factory.VariantFactory;
import net.minecraftplus._api.factory.writer.ResourceWriter;
import net.minecraftplus._api.util.json.JSONMap;
import net.minecraftplus.mcp_api._API;

/**Minecraft Factory*/
public final class MCF
{
	private MCF() {}

	public static void makeItemModel(String parResourceName, JSONMap parJSON)
	{
		assert(MCP.mod().getMod() instanceof _Mod);

		ModContainer container = MCP.mod();
		ModelFactory.write(itemModelDirectory(container.getModId()), parResourceName + ".json", parJSON.toJSON());
	}

	public static void makeBlockModel(String parResourceName, JSONMap parJSON)
	{
		assert(MCP.mod().getMod() instanceof _Mod);

		ModContainer container = MCP.mod();
		ModelFactory.write(blockModelDirectory(container.getModId()), parResourceName + ".json", parJSON.toJSON());
	}

	public static void makeVariant(String parResourceName, JSONMap parJSON)
	{
		assert(MCP.mod().getMod() instanceof _Mod);

		ModContainer container = MCP.mod();
		VariantFactory.write(variantDirectory(container.getModId()), parResourceName + ".json", parJSON.toJSON());
	}

	public static void makeMod(String parDirectory, String parFileName)
	{
		String modid = parDirectory.substring(parDirectory.lastIndexOf('\\') + 1);
		ModFactory.write(parDirectory, parFileName, "_CommonProxy", "_ClientProxy", "net.minecraftplus", new String[]{MCP.class.getName()}, _Mod.class, modid, "1.0.0", new String[]{_API.MODID}, null);
	}

	public static void makeProxy(String parDirectory)
	{
		String modid = parDirectory.substring(parDirectory.lastIndexOf('\\') + 1);
		ProxyFactory.write(parDirectory, "_CommonProxy.java", "net.minecraftplus", new String[]{MCS.class.getName()}, Proxy.class, modid, Side.SERVER);
		ProxyFactory.write(parDirectory, "_ClientProxy.java", "net.minecraftplus", new String[]{MCC.class.getName()}, "_CommonProxy", modid, Side.CLIENT);
	}

	public static void makeDirectory(String parDirectory)
	{
		String modid = parDirectory.substring(parDirectory.lastIndexOf('\\') + 1);
		ResourceWriter.write(parDirectory, "readme.txt", "#" + modid.toUpperCase());
	}

	public static void makeLanguagePack(String parDirectory)
	{
		String modid = parDirectory.substring(parDirectory.lastIndexOf('\\') + 1);
		LanguageFactory.write(parDirectory, Assets.EN_US.toString() + ".lang", Assets.EN_US);
	}

	public static final String modDirectory(String parModID)
	{
		return mcpDirectory() + "\\" + parModID;
	}

	public static final String mcpDirectory()
	{
		return Assets.javaDirectory() + "\\net\\minecraftplus";
	}

	public static final String langDirectory(String parModID)
	{
		return Assets.directory() + "\\" + parModID + "\\lang";
	}

	public static final String itemModelDirectory(String parModID)
	{
		return modelDirectory(parModID) + "\\item";
	}

	public static final String blockModelDirectory(String parModID)
	{
		return modelDirectory(parModID) + "\\block";
	}

	public static final String modelDirectory(String parModID)
	{
		return Assets.directory() + "\\" + parModID + "\\models";
	}

	public static final String variantDirectory(String parModID)
	{
		return Assets.directory() + "\\" + parModID + "\\blockstates";
	}
}
