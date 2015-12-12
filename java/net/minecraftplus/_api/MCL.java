package net.minecraftplus._api;

import java.io.PrintStream;

import net.minecraftforge.fml.common.ModContainer;
import net.minecraftplus._api.base._Mod;
import net.minecraftplus._api.util.StringUtil;

/**Mincraft Logger*/
public final class MCL
{
	private static PrintStream stream = System.out;
	private static boolean toSystemStream = false;

	private MCL() {}

	public static final void setStream(PrintStream parStream)
	{
		stream = parStream;
	}

	public static final void info(Object... parObjects)
	{
		log("[INFO]", StringUtil.joinWith(StringUtil.toStrings(parObjects), " "));
	}

	public static final void debug(Object... parObjects)
	{
		log("[DEBUG]", StringUtil.joinWith(StringUtil.toStrings(parObjects), " "));
	}

	public static final void warning(Object... parObjects)
	{
		log("[WARNING]", StringUtil.joinWith(StringUtil.toStrings(parObjects), " "));
	}

	public static final void error(Object... parObjects)
	{
		toSystemStream = true;
		log("[ERROR]", StringUtil.joinWith(StringUtil.toStrings(parObjects), " "));
		toSystemStream = false;
	}

	protected static final void log(String parType, String parBody)
	{
		if (toSystemStream)
		{
			log(parType, parBody, System.err);
			if (stream != System.err && stream != System.out)
			{
				log(parType, parBody, stream);
			}
		}
		else
		{
			log(parType, parBody, stream);
		}
	}

	private static final void log(String parType, String parBody, PrintStream stream)
	{
		String mod = null;
		ModContainer container = MCP.mod();
		if (container.getMod() instanceof _Mod) mod = container.getName().toUpperCase();

		if (isImportant(parType))
		{
			stream.print('\n');
			stream.println("----------------------------------------------------------------------------------------------------");
			stream.println("----------------------------------------------------------------------------------------------------");
		}

		stream.println("[MC+]" + (mod != null ? "[" + mod + "]" : "") + " " + parType + " " + parBody);

		if (isImportant(parType))
		{
			stream.println("----------------------------------------------------------------------------------------------------");
			stream.println("----------------------------------------------------------------------------------------------------");
			stream.print('\n');
		}

		if (Munge.isRunning() && (parType.equals("[INFO]") || parType.equals("[ERROR]")))
		{
			Munge.setDirty();
		}
	}

	private static boolean isImportant(String parType)
	{
		return parType.equals("[ERROR]") || parType.equals("[WARNING]");
	}
}
