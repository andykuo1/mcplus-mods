package net.minecraftplus._api.dictionary;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftplus._api.MCP;
import net.minecraftplus._api.util.StringUtil;

public final class Resources
{
	private Resources() {}

	public static String of(Item parItem, String... parAttributes)
	{
		return Names.strip(parItem.getUnlocalizedName()) + (parAttributes.length != 0 ? "." + StringUtil.joinWith(parAttributes, ".") : "");
	}

	public static String of(Block parBlock, String... parAttributes)
	{
		return Names.strip(parBlock.getUnlocalizedName()) + (parAttributes.length != 0 ? "." + StringUtil.joinWith(parAttributes, ".") : "");
	}

	public static final String ofTexture(Item parItem, String... parAttributes)
	{
		ModContainer mod = MCP.mod();
		return mod.getModId() + ":items/" + of(parItem, parAttributes);
	}

	public static final String ofTexture(Block parBlock, String... parAttributes)
	{
		ModContainer mod = MCP.mod();
		return mod.getModId() + ":blocks/" + of(parBlock, parAttributes);
	}

	public static final String ofModel(Item parItem, String... parAttributes)
	{
		ModContainer mod = MCP.mod();
		return mod.getModId() + ":" + of(parItem, parAttributes);
	}

	public static final String ofModel(Block parBlock, String... parAttributes)
	{
		ModContainer mod = MCP.mod();
		return mod.getModId() + ":" + of(parBlock, parAttributes);
	}

	public static final String ofModelParent(Item parItem, String... parAttributes)
	{
		ModContainer mod = MCP.mod();
		return mod.getModId() + ":item/" + of(parItem, parAttributes);
	}

	public static final String ofModelParent(Block parBlock, String... parAttributes)
	{
		ModContainer mod = MCP.mod();
		return mod.getModId() + ":block/" + of(parBlock, parAttributes);
	}
}
