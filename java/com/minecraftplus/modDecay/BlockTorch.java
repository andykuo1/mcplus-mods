package com.minecraftplus.modDecay;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockTorch extends net.minecraft.block.BlockTorch
{
	public BlockTorch()
	{
		super();
		this.setHardness(0.0F);
		this.setLightLevel(0.9375F);
		this.setStepSound(soundTypeWood);
		this.setBlockTextureName("torch_on");
		this.setCreativeTab(null);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);
		System.out.println("WHAT");
		par1World.setBlock(par2, par3, par4, MCP_Decay.torchUnlit);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition par1MovingObjectPosition, World par2World, int par3, int par4, int par5)
	{
		return new ItemStack(Blocks.torch, 1);
	}
}
