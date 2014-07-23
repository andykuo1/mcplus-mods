package com.minecraftplus.modClayWallSlab;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockClayWallSlab extends BlockDirectional
{
	protected final boolean isDoubleSlab;

	public BlockClayWallSlab(boolean par1, Material par2Material, boolean par3)
	{
		super(par2Material);
		this.isDoubleSlab = par1;
		if (par3)
		{
			this.setCreativeTab(CreativeTabs.tabBlock);
		}

		if (par1)
		{
			this.opaque = true;
		}
		else
		{
			this.setBlockBoundsForItemRender();
		}

		this.setLightOpacity(0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = Blocks.stained_hardened_clay.getIcon(0, 0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return Blocks.stained_hardened_clay.getIcon(par1, (par2 & 7) + this.getColorRange());
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		ItemStack item = par5EntityPlayer.getCurrentEquippedItem();
		if (item != null && item.getItem() instanceof ItemDye)
		{
			Block block = par1World.getBlock(par2, par3, par4);
			Block slab = getBlockWithColor(15 - item.getItemDamage(), ItemClayWallSlab.isNorthSouth(block), !ItemClayWallSlab.isSingleSlab(block));
			int metadata = par1World.getBlockMetadata(par2, par3, par4);
			int colorrange = ((BlockClayWallSlab) slab).getColorRange();

			par1World.setBlock(par2, par3, par4, slab);
			par1World.setBlockMetadataWithNotify(par2, par3, par4, (metadata & 8) + ((15 - item.getItemDamage()) & 7), 2);

			return true;
		}

		return false;
	}

	@Override
	public abstract void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase parEntityLivingBase, ItemStack parItemStack);

	@Override
	public abstract void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4);

	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, this.isDoubleSlab ? 1.0F : 0.5F);
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return this.isDoubleSlab;
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5Side, float par6HitX, float par7HitY, float par8HitZ, int par9)
	{
		return (par5Side != 0) && ((par5Side == 1) || (par7HitY <= 0.5D)) ? par9 : this.isDoubleSlab ? par9 : par9 | 0x8;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return this.isDoubleSlab ? 2 : 1;
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1 & 7;
	}

	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4)
	{
		return super.getDamageValue(par1World, par2, par3, par4) & 7;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return this.isDoubleSlab;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5Side)
	{
		return this.isDoubleSlab ? super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5Side) : true;
	}

	public String func_150002_b(int par1)
	{
		par1 &= 7;
		par1 += this.getColorRange();
		return this.getUnlocalizedName() + "." + ItemDye.field_150923_a[BlockColored.func_150032_b(par1)];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item par1Item, CreativeTabs par2CreativeTabs, List par3List)
	{
		if (par1Item != Item.getItemFromBlock(MCP_ClayWallSlab.clayWallDoubleSlabNS1) && par1Item != Item.getItemFromBlock(MCP_ClayWallSlab.clayWallDoubleSlabNS2) && par1Item != Item.getItemFromBlock(MCP_ClayWallSlab.clayWallDoubleSlabWE1) && par1Item != Item.getItemFromBlock(MCP_ClayWallSlab.clayWallDoubleSlabWE2))
		{
			for (int i = 0; i < 8; ++i)
			{
				par3List.add(new ItemStack(par1Item, 1, i + this.getColorRange()));
			}
		}
	}

	public abstract int getColorRange();

	public static Block getBlockWithColor(int par1, boolean par2, boolean par3)
	{
		if (par1 >= ((BlockClayWallSlab) MCP_ClayWallSlab.clayWallDoubleSlabNS1).getColorRange() && par1 < ((BlockClayWallSlab) MCP_ClayWallSlab.clayWallDoubleSlabNS1).getColorRange() + 8)
		{
			return par3 ? par2 ? MCP_ClayWallSlab.clayWallDoubleSlabNS1 : MCP_ClayWallSlab.clayWallDoubleSlabWE1 : par2 ? MCP_ClayWallSlab.clayWallHalfSlabNS1 : MCP_ClayWallSlab.clayWallHalfSlabWE1;
		}
		else if (par1 >= ((BlockClayWallSlab) MCP_ClayWallSlab.clayWallDoubleSlabNS2).getColorRange() && par1 < ((BlockClayWallSlab) MCP_ClayWallSlab.clayWallDoubleSlabNS2).getColorRange() + 8)
		{
			return par3 ? par2 ? MCP_ClayWallSlab.clayWallDoubleSlabNS2 : MCP_ClayWallSlab.clayWallDoubleSlabWE2 : par2 ? MCP_ClayWallSlab.clayWallHalfSlabNS2 : MCP_ClayWallSlab.clayWallHalfSlabWE2;
		}
		else
		{
			return par3 ? par2 ? MCP_ClayWallSlab.clayWallDoubleSlabNS : MCP_ClayWallSlab.clayWallDoubleSlabWE : par2 ? MCP_ClayWallSlab.clayWallHalfSlabNS : MCP_ClayWallSlab.clayWallHalfSlabWE;
		}
	}
}