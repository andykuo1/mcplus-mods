package com.minecraftplus.modCopper;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOreCopper extends BlockOre
{
	public BlockOreCopper()
	{
		this.setHardness(3.0F);
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
