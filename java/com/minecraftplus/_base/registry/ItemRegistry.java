package com.minecraftplus._base.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry
{
	public static Item add(Item par1Item, String par2String)
	{
		GameRegistry.registerItem(par1Item, par2String);
		LanguageRegistry.add(par1Item);
		return par1Item;
	}

	public static Item add(Item par1Item)
	{
		return add(par1Item, LanguageRegistry.getNameTrim(par1Item.getUnlocalizedName()));
	}

	public static Block add(Block par1Block, String par2String)
	{
		GameRegistry.registerBlock(par1Block, par2String);
		LanguageRegistry.add(par1Block);
		return par1Block;
	}

	public static Block add(Block par1Block)
	{
		return add(par1Block, LanguageRegistry.getNameTrim(par1Block.getUnlocalizedName()));
	}

	public static Block add(Block par1Block, Class<? extends ItemBlock> par2Class, String par3String)
	{
		GameRegistry.registerBlock(par1Block, par2Class, par3String);
		LanguageRegistry.add(par1Block);
		return par1Block;
	}

	public static Item addUnLocal(Item par1Item, String par2String)
	{
		GameRegistry.registerItem(par1Item, par2String);
		return par1Item;
	}

	public static Item addUnLocal(Item par1Item)
	{
		return addUnLocal(par1Item, LanguageRegistry.getNameTrim(par1Item.getUnlocalizedName()));
	}

	public static Block addUnLocal(Block par1Block, String par2String)
	{
		GameRegistry.registerBlock(par1Block, par2String);
		return par1Block;
	}

	public static Block addUnLocal(Block par1Block)
	{
		return addUnLocal(par1Block, LanguageRegistry.getNameTrim(par1Block.getUnlocalizedName()));
	}

	public static Block addUnLocal(Block par1Block, Class<? extends ItemBlock> par2Class, String par3String)
	{
		GameRegistry.registerBlock(par1Block, par2Class, par3String);
		return par1Block;
	}
}
