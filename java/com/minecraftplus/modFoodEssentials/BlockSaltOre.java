package com.minecraftplus.modFoodEssentials;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

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
		this.blockIcon = IconRegistry.add(par1IIconRegister, this);
	}
	
	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return MCP_FoodEssentials.salt;
	}
}
