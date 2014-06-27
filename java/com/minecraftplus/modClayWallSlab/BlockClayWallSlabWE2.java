package com.minecraftplus.modClayWallSlab;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockClayWallSlabWE2 extends BlockClayWallSlab2
{
	public BlockClayWallSlabWE2(boolean par1, Material par2Material)
	{
		super(par1, par2Material, false);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase parEntityLivingBase, ItemStack parItemStack)
	{
		int l = MathHelper.floor_double(parEntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
		int i = parItemStack.getItemDamage();

		if (l == 0)
		{
			par1World.setBlock(par2, par3, par4, MCP_ClayWallSlab.clayWallHalfSlabNS2);
			MCP_ClayWallSlab.clayWallHalfSlabNS2.onBlockPlacedBy(par1World, par2, par3, par4, parEntityLivingBase, parItemStack);
		}

		if (l == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, i & 7, 2);
		}

		if (l == 2)
		{
			par1World.setBlock(par2, par3, par4, MCP_ClayWallSlab.clayWallHalfSlabNS2);
			MCP_ClayWallSlab.clayWallHalfSlabNS2.onBlockPlacedBy(par1World, par2, par3, par4, parEntityLivingBase, parItemStack);
		}

		if (l == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, i | 8, 2);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess oar1IBlockAccess, int par2, int par3, int par4)
	{
		if (this.isDoubleSlab)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		else
		{
			boolean flag = (oar1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) != 0;

			if (flag)
			{
				this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
			}
		}
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(this.isDoubleSlab ? 0.0F : 0.25F, 0.0F, 0.0F, this.isDoubleSlab ? 1.0F : 0.75F, 1.0F, 1.0F);
	}

	@SideOnly(Side.CLIENT)
	private static boolean func_150003_a(Block block)
	{
		return block == MCP_ClayWallSlab.clayWallHalfSlabWE2;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getItem(World world, int x, int y, int z)
	{
		return Item.getItemFromBlock(MCP_ClayWallSlab.clayWallHalfSlabWE2); 
	}
}