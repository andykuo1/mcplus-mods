package com.minecraftplus.modSilver;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSword;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSilverSword extends ItemSword
{
	public ItemSilverSword(ToolMaterial par1ToolMaterial)
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
