package net.minecraftplus._common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabsBase extends CreativeTabs
{
	private final Item item;

	public CreativeTabsBase(int p_i1853_1_, String parName, Item parItem)
	{
		super(p_i1853_1_, parName);
		this.item = parItem;
	}

	public CreativeTabsBase(String parName, Item parItem)
	{
		super(parName);
		this.item = parItem;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getTabIconItem()
	{
		return this.item;
	}
}
