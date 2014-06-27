package com.minecraftplus.modLock;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLockpick extends Item
{
	public ItemLockpick()
	{
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister parIIconRegister)
	{
		this.itemIcon = IconRegistry.register(parIIconRegister, this);
	}
}
