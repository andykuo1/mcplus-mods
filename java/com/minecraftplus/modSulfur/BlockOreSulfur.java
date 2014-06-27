package com.minecraftplus.modSulfur;

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

public class BlockOreSulfur extends BlockOre
{
	public BlockOreSulfur()
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

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return MCP_Sulfur.sulfur;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 1 + par1Random.nextInt(3);
	}

	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random)
	{
		if (par1 > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, par2Random, par1))
		{
			int j = par2Random.nextInt(par1 + 2) - 1;

			if (j < 0)
			{
				j = 0;
			}

			return this.quantityDropped(par2Random) * (j + 1);
		}
		else
		{
			return this.quantityDropped(par2Random);
		}
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

		if (this.getItemDropped(par5, par1World.rand, par7) != Item.getItemFromBlock(this))
		{
			int j1 = MathHelper.getRandomIntegerInRange(par1World.rand, 0, 2);
			this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
		}
	}
}
