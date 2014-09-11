package com.minecraftplus.modWorkshop;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHungerWorm extends ItemFood
{
	public ItemHungerWorm()
	{
		super(-2, 0.0F, false);
		this.setCreativeTab(MCP_Workshop.tabWorkshop);
		this.setAlwaysEdible();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.add(par1IIconRegister, this);
	}
}
