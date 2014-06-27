package com.minecraftplus.modSurvivor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDagger extends ItemSword
{
	public ItemDagger(Item.ToolMaterial par1ToolMaterial)
	{
		super(par1ToolMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
	}
}