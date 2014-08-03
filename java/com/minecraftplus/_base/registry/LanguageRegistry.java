package com.minecraftplus._base.registry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;

public class LanguageRegistry
{
	private static HashMap<String, String> stringMap = new HashMap<String, String>();

	public static void createLangFile(String par1LangPack)
	{
		if (stringMap == null) return;

		File dir = new File(Minecraft.getMinecraft().mcDataDir, "mod_workshop");
		dir.mkdir();
		File file = new File(dir, par1LangPack + ".lang");

		try
		{
			if (file.exists())
			{
				file.delete();
			}

			file.createNewFile();
			Writer writer = new BufferedWriter(new FileWriter(file));
			List list = new ArrayList(stringMap.keySet());
			Collections.sort(list);
			Iterator iter = list.iterator();

			while(iter.hasNext())
			{
				Object obj = iter.next();
				writer.write(obj.toString() + "=" + stringMap.get(obj) + "\n");
			}

			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		stringMap.clear();
		stringMap = null;
	}

	public static void add(String par1String, String par2String)
	{
		stringMap.put(par1String, par2String);
	}

	public static void add(String par1String)
	{
		add(par1String, getNameReadable(par1String));
	}

	public static void add(Item par1Item, String par2String)
	{
		add(par1Item.getUnlocalizedName() + ".name", par2String);
	}

	public static void add(Item par1Item)
	{
		add(par1Item, getNameReadable(par1Item.getUnlocalizedName()));
	}

	public static void add(Block par1Block, String par2String)
	{
		add(par1Block.getUnlocalizedName() + ".name", par2String);
	}

	public static void add(Block par1Block)
	{
		add(par1Block, getNameReadable(par1Block.getUnlocalizedName()));
	}

	public static void add(Class<? extends Entity> par1Class, String par2String)
	{
		add("entity." + ((String) EntityList.classToStringMapping.get(par1Class)) + ".name", par2String);
	}

	public static void add(Class<? extends Entity> par1Class)
	{
		add(par1Class, getNameReadable(((String) EntityList.classToStringMapping.get(par1Class))));
	}

	public static void add(CreativeTabs par1CreativeTabs, String par2String)
	{
		add("itemGroup." + par2String, getNameReadable(par2String));
	}

	public static String getNameTrim(String parString)
	{
		parString = parString.replace("item.", "");
		parString = parString.replace("block.", "");
		parString = parString.replace("entity.", "");
		parString = parString.replace("tile.", "");
		parString = parString.replace("itemGroup.", "");
		parString = parString.replace(".name", "");

		return parString;
	}

	public static String getNameReadable(String par1String)
	{
		par1String = getNameTrim(par1String);

		boolean toUpperCase = true;
		for(int i = 0; i < par1String.length(); i++)
		{
			char c = par1String.charAt(i);

			if (Character.isUpperCase(c) && (i > 0 && par1String.charAt(i - 1) != ' '))
			{
				par1String = par1String.substring(0, i) + " " + par1String.substring(i);
				toUpperCase = false;
			}

			if (toUpperCase)
			{
				par1String = par1String.substring(0, i) + Character.toUpperCase(c) + par1String.substring(i + 1);
				toUpperCase = false;
			}

			if (c == '_')
			{
				par1String = par1String.substring(0, i) + " " + par1String.substring(i + 1);
				toUpperCase = true;
			}
		}

		return par1String;
	}

	public static String getNameUnlocal(String par1String)
	{
		for(int i = 1; i < par1String.length() - 1; i++)
		{
			if (Character.isUpperCase(par1String.charAt(i)) && par1String.charAt(i - 1) != ' ')
			{
				par1String = par1String.substring(0, i) + " " + par1String.substring(i);
				i++;
			}
		}

		return par1String.replaceAll(" ", "_").toLowerCase();
	}
}
