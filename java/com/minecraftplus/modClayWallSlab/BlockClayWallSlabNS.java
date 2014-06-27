package com.minecraftplus.modClayWallSlab;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockClayWallSlabNS extends BlockClayWallSlab
{
	public BlockClayWallSlabNS(boolean par1, Material par2Material)
	{
		super(par1, par2Material, true);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return Blocks.hardened_clay.getIcon(par1, par2);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase parEntityLivingBase, ItemStack parItemStack)
	{
		int l = MathHelper.floor_double(parEntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;

		if (l == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
		}

		if (l == 1)
		{
			par1World.setBlock(par2, par3, par4, MCP_ClayWallSlab.clayWallHalfSlabWE);
			MCP_ClayWallSlab.clayWallHalfSlabWE.onBlockPlacedBy(par1World, par2, par3, par4, parEntityLivingBase, parItemStack);
		}

		if (l == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
		}

		if (l == 3)
		{
			par1World.setBlock(par2, par3, par4, MCP_ClayWallSlab.clayWallHalfSlabWE);
			MCP_ClayWallSlab.clayWallHalfSlabWE.onBlockPlacedBy(par1World, par2, par3, par4, parEntityLivingBase, parItemStack);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		if (this.isDoubleSlab)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		else
		{
			boolean flag = par1IBlockAccess.getBlockMetadata(par2, par3, par4) != 0;

			if (flag)
			{
				this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
			}
			else
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
			}
		}
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.0F, 0.0F, this.isDoubleSlab ? 0.0F : 0.25F, 1.0F, 1.0F, this.isDoubleSlab ? 1.0F : 0.75F);
	}

	public String func_150002_b(int par1)
	{
		return this.getUnlocalizedName();
	}

	@SideOnly(Side.CLIENT)
	private static boolean func_150003_a(Block block)
	{
		return block == MCP_ClayWallSlab.clayWallHalfSlabNS;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getItem(World world, int x, int y, int z)
	{
		return Item.getItemFromBlock(MCP_ClayWallSlab.clayWallHalfSlabNS); 
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1Item, CreativeTabs par2CreativeTabs, List par3List)
	{
		if (par1Item != Item.getItemFromBlock(MCP_ClayWallSlab.clayWallDoubleSlabNS))
		{
			par3List.add(new ItemStack(par1Item, 1, 0));
		}
	}

	public int getColorRange()
	{
		return 0;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition par1MovingObjectPosition, World par2World, int par3, int par4, int par5)
	{
		return new ItemStack(MCP_ClayWallSlab.clayWallHalfSlabNS, 1);
	}
}