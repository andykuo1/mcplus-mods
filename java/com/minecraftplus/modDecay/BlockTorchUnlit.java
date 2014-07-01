package com.minecraftplus.modDecay;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = IconRegistry.register(par1IIconRegister, this);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
		{
			int metadata = par1World.getBlockMetadata(par2, par3, par4);
			par1World.setBlock(par2, par3, par4, MCP_Decay.torchLit);
			par1World.setBlockMetadataWithNotify(par2, par3, par4, metadata, 2);
			return true;
		}
		
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {}
}
