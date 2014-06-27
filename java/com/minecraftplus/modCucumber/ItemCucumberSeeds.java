package com.minecraftplus.modCucumber;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCucumberSeeds extends ItemSeeds
{
	public ItemCucumberSeeds()
	{
		super(MCP_Cucumber.cucumbersBase, Blocks.farmland);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
	}
}
