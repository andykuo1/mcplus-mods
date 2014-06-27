package com.minecraftplus.modSweetPotato;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSweetPotato extends ItemSeedFood
{
	public ItemSweetPotato(int par1, float par2)
	{
		super(par1, par2, MCP_SweetPotato.sweetPotatoes, Blocks.farmland);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
	}
}
