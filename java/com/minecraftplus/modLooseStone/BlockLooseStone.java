package com.minecraftplus.modLooseStone;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockLooseStone extends BlockFalling
{
	public BlockLooseStone()
	{
		this.setTickRandomly(false);
		this.setBlockTextureName("stone");
		this.setHardness(1.5F);
		this.setResistance(6.0F);
		this.setStepSound(soundTypePiston);
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return Item.getItemFromBlock(Blocks.stone);
	}

	@Override
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {}

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer p_149699_5_)
	{
		if (!this.isSupported(par1World, par2, par3, par4))
		{
			par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block)
	{
		if (!this.isSupported(par1World, par2, par3, par4))
		{
			par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
		}
	}

	@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (!this.isSupported(par1World, par2, par3, par4))
		{
			par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
		}
	}

	private boolean isSupported(World par1World, int par2, int par3, int par4)
	{
		for(int i = -1; i <=1 ; i++)
		{
			for(int j = -1; j <= 1; j++)
			{
				if (this.isSupportBlock(par1World.getBlock(par2 + i, par3 - 1, par4 + j))) return true;
			}
		}
		
		if (this.isSupportBlock(par1World.getBlock(par2, par3 - 1, par4))) return true;
		if (this.isSupportBlock(par1World.getBlock(par2 - 1, par3, par4))) return true;
		if (this.isSupportBlock(par1World.getBlock(par2 + 1, par3, par4))) return true;
		if (this.isSupportBlock(par1World.getBlock(par2, par3, par4 - 1))) return true;
		if (this.isSupportBlock(par1World.getBlock(par2, par3, par4 + 1))) return true;

		return false;
	}

	private boolean isSupportBlock(Block par1Block)
	{
		return par1Block instanceof BlockFence || par1Block instanceof BlockFenceGate || par1Block instanceof BlockWall;
	}
}
