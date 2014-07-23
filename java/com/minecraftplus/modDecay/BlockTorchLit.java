package com.minecraftplus.modDecay;

import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockTorchLit extends BlockTorch
{
	public BlockTorchLit()
	{
		super();
		this.setHardness(0.0F);
		this.setLightLevel(0.9375F);
		this.setStepSound(soundTypeWood);
		this.setBlockTextureName("torch_on");
		this.setCreativeTab(null);
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return Item.getItemFromBlock(Blocks.torch);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);
		
		if (par1World.getBlockMetadata(par2, par3, par4) == 0)
        {
            this.onBlockAdded(par1World, par2, par3, par4);
        }
		
		if (!par1World.isRemote && (par1World.rand.nextInt(24) == 0 || (par1World.isRaining() && par1World.canBlockSeeTheSky(par2, par3, par4))))
		{
			int metadata = par1World.getBlockMetadata(par2, par3, par4);
			par1World.setBlock(par2, par3, par4, MCP_Decay.torchUnlit);
			par1World.setBlockMetadataWithNotify(par2, par3, par4, metadata, 2);
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition par1MovingObjectPosition, World par2World, int par3, int par4, int par5)
	{
		return super.getPickBlock(par1MovingObjectPosition, par2World, par3, par4, par5);//new ItemStack(Blocks.torch, 1);
	}
}
