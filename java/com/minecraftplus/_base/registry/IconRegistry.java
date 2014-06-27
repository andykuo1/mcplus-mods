package com.minecraftplus._base.registry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class IconRegistry
{
	public static IIcon register(IIconRegister par1IIconRegister, String par2String)
	{
		return par1IIconRegister.registerIcon("minecraftplus:" + par2String);
	}

	public static IIcon register(IIconRegister par1IIconRegister, Item par2Item)
	{
		return register(par1IIconRegister, par2Item.getUnlocalizedName());
	}

	public static IIcon register(IIconRegister par1IIconRegister, Block par2Block)
	{
		return register(par1IIconRegister, par2Block.getUnlocalizedName());
	}

	public static IIcon register(IIconRegister par1IIconRegister, Item par2Item, String par3String)
	{
		return register(par1IIconRegister, par2Item.getUnlocalizedName() + par3String);
	}

	public static IIcon register(IIconRegister par1IIconRegister, Block par2Block, String par3String)
	{
		return register(par1IIconRegister, par2Block.getUnlocalizedName() + par3String);
	}
}