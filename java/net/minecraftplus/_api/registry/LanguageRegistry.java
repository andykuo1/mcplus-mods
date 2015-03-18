package net.minecraftplus._api.registry;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftplus._api.handler.Packet;
import net.minecraftplus._api.tool.DevTool;
import net.minecraftplus._api.tool.LangTool;

/**
 * Registry for localization of {@link String}
 * */
public class LanguageRegistry extends RegistryMap<String, String>
{
	public static final LanguageRegistry INSTANCE = new LanguageRegistry();

	//**************** Registry Setup ****************//
	
	@Override
	public void initialize()
	{
		DevTool.createLangFile(this.registry, "en_US");
	}

	public Map<String, String> getMapping()
	{
		return this.registry;
	}

	//**************** Simplified Functions ****************//

	public String add(String parString)
	{
		return this.add(parString, LangTool.translate(parString));
	}

	public String add(Item parItem)
	{
		return this.add(parItem, LangTool.translate(parItem.getUnlocalizedName()));
	}

	public String add(Block parBlock)
	{
		return this.add(parBlock, LangTool.translate(parBlock.getUnlocalizedName()));
	}

	public String add(CreativeTabs parCreativeTabs)
	{
		return this.add(parCreativeTabs, LangTool.translate(parCreativeTabs.getTranslatedTabLabel()));
	}

	//**************** Base Functions ****************//

	@Override
	public String add(String parString, String parTranslation)
	{
		return super.add(parString, parTranslation);
	}

	public String add(Item parItem, String parTranslation)
	{
		String str = parItem.getUnlocalizedName() + ".name";
		this.add(str, parTranslation);
		return str;
	}

	public String add(Block parBlock, String parTranslation)
	{
		String str = parBlock.getUnlocalizedName() + ".name";
		this.add(str, parTranslation);
		return str;
	}

	public String add(CreativeTabs parCreativeTabs, String parTranslation)
	{
		String str = parCreativeTabs.getTranslatedTabLabel();
		this.add(str, parTranslation);
		return str;
	}
}
