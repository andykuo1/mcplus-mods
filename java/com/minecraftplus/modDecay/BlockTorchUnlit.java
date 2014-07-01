package com.minecraftplus.modDecay;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTorchUnlit extends net.minecraft.block.BlockTorch
{
	public BlockTorchUnlit()
	{
		super();
		this.setHardness(0.0F);
		this.setLightLevel(0F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(null);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = IconRegistry.register(par1IIconRegister, this);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {}
}
