package com.minecraftplus.modBamboo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBamboo extends BlockRotatedPillar
{
	public BlockBamboo()
	{
		super(Material.grass);

		this.setHardness(0.5F);
		this.setStepSound(Block.soundTypeGrass);

		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getSideIcon(int p_150163_1_)
	{
		return this.blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.field_150164_N = IconRegistry.add(par1IIconRegister, this, "_top");
		this.blockIcon = IconRegistry.add(par1IIconRegister, this, "_side");
	}
}