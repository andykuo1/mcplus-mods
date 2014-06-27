package com.minecraftplus._common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBase extends Item
{
	public ItemBase(CreativeTabs par1CreativeTabs, int par2)
	{
		super();
		this.setCreativeTab(par1CreativeTabs);
		this.setMaxStackSize(par2);
	}

	public ItemBase(CreativeTabs par1CreativeTabs)
	{
		this(par1CreativeTabs, 64);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
	}
}
