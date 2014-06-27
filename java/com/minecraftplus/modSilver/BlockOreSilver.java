package com.minecraftplus.modSilver;

import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOreSilver extends BlockOre
{
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = IconRegistry.register(par1IIconRegister, this.getUnlocalizedName());
	}
}
