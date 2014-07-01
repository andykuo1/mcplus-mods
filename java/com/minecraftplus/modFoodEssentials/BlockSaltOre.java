package com.minecraftplus.modFoodEssentials;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSaltOre extends BlockOre
{
	public BlockSaltOre()
	{
		this.setHardness(1.5F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypePiston);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = IconRegistry.register(par1IIconRegister, this);
	}
}
