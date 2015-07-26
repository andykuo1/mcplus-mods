package net.minecraftplus._api.dictionary;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public final class Names
{
	private Names() {}

	public static String strip(String parString)
	{
		assert(parString != null);

		return parString.replace("item", "").replace("tile", "").replace(".", "");
	}

	public static String of(Item parItem)
	{
		assert(parItem != null);
		assert(parItem.getUnlocalizedName() != null);
		assert(parItem.getUnlocalizedName().contains("item"));

		String unlocalized = parItem.getUnlocalizedName().toLowerCase();
		String result = strip(unlocalized);

		int first = 0;
		result = Character.toUpperCase(result.charAt(first)) + result.substring(1);

		int underscore = -1;
		while((underscore = result.indexOf('_')) != -1)
		{
			char toUpper = Character.toUpperCase(result.charAt(underscore + 1));
			result = result.substring(0, underscore) + " " + toUpper + result.substring(underscore + 2);
		}

		return result;
	}

	public static String of(Block parBlock)
	{
		assert(parBlock != null);
		assert(parBlock.getUnlocalizedName() != null);
		assert(parBlock.getUnlocalizedName().contains("tile"));

		String unlocalized = parBlock.getUnlocalizedName().toLowerCase();
		String result = strip(unlocalized);

		int first = 0;
		result = Character.toUpperCase(result.charAt(first)) + result.substring(1);

		int underscore = -1;
		while((underscore = result.indexOf('_')) != -1)
		{
			char toUpper = Character.toUpperCase(result.charAt(underscore + 1));
			result = result.substring(0, underscore) + " " + toUpper + result.substring(underscore + 2);
		}

		return result;
	}
}
