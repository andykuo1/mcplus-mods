package com.minecraftplus._base.registry;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;

public class LanguageRegistry
{
	private static HashMap<String, String> stringMap = new HashMap<String, String>();

	public static void load(String par1LangPack)
	{
		cpw.mods.fml.common.registry.LanguageRegistry.instance().injectLanguage(par1LangPack, stringMap);
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

	public static void add(CreativeTabs par1CreativeTabs)
	{
		add("itemGroup." + par1CreativeTabs.getTabLabel(), getNameReadable(par1CreativeTabs.getTabLabel()));
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

	public static String getNameReadable(String parString)
	{
		parString = getNameTrim(parString);

		boolean toUpperCase = true;
		for(int i = 0; i < parString.length(); i++)
		{
			char c = parString.charAt(i);
			if (toUpperCase)
			{
				parString = parString.substring(0, i) + Character.toUpperCase(c) + parString.substring(i + 1);
				toUpperCase = false;
			}

			if (c == '_')
			{
				parString = parString.substring(0, i) + " " + parString.substring(i + 1);
				toUpperCase = true;
			}
		}

		return parString;
	}
}
