package com.minecraftplus.modRuby;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;

import com.minecraftplus._base.registry.IconRegistry;
import com.minecraftplus._common.item.ItemBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRuby extends ItemBase
{
	public ItemRuby()
	{
		super(CreativeTabs.tabMaterials);
	}
}
