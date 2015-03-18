package net.minecraftplus._api.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraftplus.ToDoList;
import net.minecraftplus._api.mod.MCP;

public class DevTool
{
	public static boolean isDisabled()
	{
		return !MCP.DEBUGMODE;
	}

	public static String getAssetsDir()
	{
		if (DevTool.isDisabled()) return "";

		String s = Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
		s = s.substring(0, s.length() - 9) + "src\\main\\resources\\assets";
		return s;
	}

	public static String getMinecraftDir()
	{
		String s = Minecraft.getMinecraft().mcDataDir.getAbsolutePath();
		return s;
	}

	public static void createModInfo(String parModDir, Map<String, Object> parModInfo)
	{
		if (DevTool.isDisabled()) return;

		String s = DevTool.getAssetsDir() + "\\" + parModDir;

		File dir = new File(s);
		dir.mkdir();
		File file = new File(dir, "mcmod.info");

		try
		{
			if (file.exists())
			{
				file.delete();
			}

			file.createNewFile();
			Writer writer = new BufferedWriter(new FileWriter(file));

			writer.write("[");
			writer.write("{");

			boolean comma = false;
			for(String str : parModInfo.keySet())
			{
				String text = comma ? ",\n" : "\n";
				Object value = parModInfo.get(str);

				if (value instanceof String)
				{
					text += "\"" + str + "\"" + ": " + "\"" + value.toString() + "\"";
				}
				else if (value instanceof String[])
				{
					text += "\"" + str + "\"" + ": " + "[";

					boolean flag = false;
					for(Object obj : ((String[])value))
					{
						String str1 = (String) obj;
						text += "\"" + str1 + "\", ";
						flag = true;
					}
					if (flag) text = text.substring(0, text.length() - 2);

					text += "]";
				}
				comma = true;

				writer.write(text);
			}

			writer.write("\n}");
			writer.write("]");

			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void createLangFile(Map<String, String> parLangMap, String parLangPack)
	{
		if (DevTool.isDisabled()) return;

		String s = DevTool.getAssetsDir() + "\\minecraftplus\\lang";
		File dir = new File(s);
		dir.mkdir();
		File file = new File(dir, parLangPack + ".lang");

		try
		{
			if (file.exists())
			{
				file.delete();
			}

			file.createNewFile();
			Writer writer = new BufferedWriter(new FileWriter(file));
			List list = new ArrayList(parLangMap.keySet());
			Collections.sort(list);
			Iterator iter = list.iterator();

			while(iter.hasNext())
			{
				Object obj = iter.next();
				writer.write(obj.toString() + "=" + parLangMap.get(obj) + "\n");
			}

			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void todo()
	{
		if (DevTool.isDisabled()) return;

		ToDoList.tellTheModAuthor();
	}
}
