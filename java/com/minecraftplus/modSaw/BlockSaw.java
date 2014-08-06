package com.minecraftplus.modSaw;

import java.util.ArrayList;
import java.util.Random;

import javax.vecmath.Point3i;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSaw extends Block
{
	@SideOnly(Side.CLIENT)
	protected IIcon[] blockIcons;

	protected BlockSaw()
	{
		super(Material.wood);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(2.0F);
		this.setStepSound(soundTypeWood);
	}

	@Override
	public int getRenderType()
	{
		return ClientProxy.renderBlockSaw.getRenderID();
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		int k = par2 & 7;
		return par1 == par2 ? this.blockIcons[1] : par1 == ForgeDirection.OPPOSITES[par2] ? this.blockIcons[0] : this.blockIcon;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = IconRegistry.register(par1IIconRegister, this.getUnlocalizedName() + ".side");

		this.blockIcons = new IIcon[2];
		this.blockIcons[0] = IconRegistry.register(par1IIconRegister, this.getUnlocalizedName() + ".bottom");
		this.blockIcons[1] = IconRegistry.register(par1IIconRegister, this.getUnlocalizedName() + ".top");
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.func_149938_m(par1World, par2, par3, par4);
	}

	private void func_149938_m(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			Block block = par1World.getBlock(par2, par3, par4 - 1);
			Block block1 = par1World.getBlock(par2, par3, par4 + 1);
			Block block2 = par1World.getBlock(par2 - 1, par3, par4);
			Block block3 = par1World.getBlock(par2 + 1, par3, par4);
			byte b0 = 3;

			if (block.func_149730_j() && !block1.func_149730_j())
			{
				b0 = 3;
			}

			if (block1.func_149730_j() && !block.func_149730_j())
			{
				b0 = 2;
			}

			if (block2.func_149730_j() && !block3.func_149730_j())
			{
				b0 = 5;
			}

			if (block3.func_149730_j() && !block2.func_149730_j())
			{
				b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
	{
		int l = BlockPistonBase.determineOrientation(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_);
		p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		boolean flag = super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
		if (this.isOccupied(par1World, par2, par3, par4))
		{
			if (!par1World.isRemote)
			{
				Point3i point = this.getOccupiedPoint(par1World, par2, par3, par4);
				if (par1World.rand.nextBoolean())
				{
					par1World.playAuxSFX(2001, point.x, point.y, point.z, Block.getIdFromBlock(this.getOccupied(par1World, par2, par3, par4)));
				}

				if (par1World.rand.nextInt(14) == 0)
				{
					par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
				}

				par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.wood_click", 0.8F, par1World.rand.nextFloat() * 0.15F + 0.6F);
			}

			return true;
		}
		else
		{
			if (par5EntityPlayer.getCurrentEquippedItem() != null)
			{
				if (Block.getBlockFromItem(par5EntityPlayer.getCurrentEquippedItem().getItem()) instanceof BlockLog)
				{
					if (!this.isOccupied(par1World, par2, par3, par4))
					{
						if (par6 == par1World.getBlockMetadata(par2, par3, par4))
						{
							return false;
						}
					}
				}
			}

			if (!par1World.isRemote)
			{
				par1World.playAuxSFX(1001, par2, par3, par4, 0);
			}
			return true;
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		Block block = this.getOccupied(par1World, par2, par3, par4);
		if (block instanceof BlockLog)
		{
			if (!par1World.isRemote)
			{
				Point3i point = this.getOccupiedPoint(par1World, par2, par3, par4);
				par1World.playAuxSFX(2001, point.x, point.y, point.z, Block.getIdFromBlock(this.getOccupied(par1World, par2, par3, par4)));
				this.breakWood(par1World, point.x, point.y, point.z, block, par1World.getBlockMetadata(point.x, point.y, point.z));
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5Block)
	{
		boolean flag = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);
		if (!flag) return;

		if (!this.isOccupied(par1World, par2, par3, par4))
		{
			if (!par1World.isRemote)
			{
				par1World.playAuxSFX(1001, par2, par3, par4, 0);
			}

			return;
		}

		if (!par1World.isRemote)
		{
			Point3i point = this.getOccupiedPoint(par1World, par2, par3, par4);
			if (par1World.rand.nextBoolean())
			{
				par1World.playAuxSFX(2001, point.x, point.y, point.z, Block.getIdFromBlock(this.getOccupied(par1World, par2, par3, par4)));
			}

			if (par1World.rand.nextInt(14) == 0)
			{
				par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
			}
			par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.wood_click", 0.8F, par1World.rand.nextFloat() * 0.15F + 0.6F);
		}
	}

	private boolean isOccupied(World par1World, int par2, int par3, int par4)
	{
		return this.getOccupied(par1World, par2, par3, par4) != null;
	}

	private Block getOccupied(World par1World, int par2, int par3, int par4)
	{
		Point3i point = this.getOccupiedPoint(par1World, par2, par3, par4);
		if (point == null) return null;
		int x = point.x, y = point.y, z = point.z;

		Block block = par1World.getBlock(x, y, z);
		if (block instanceof BlockLog)
		{
			return block;
		}

		return null;
	}

	private Point3i getOccupiedPoint(World par1World, int par2, int par3, int par4)
	{
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		int x = 0, y = 0, z = 0;

		switch(meta)
		{
		case 0: //DOWN
			x = par2;
			y = par3 - 1;
			z = par4;
			return new Point3i(x, y, z);
		case 1: //UP
			x = par2;
			y = par3 + 1;
			z = par4;
			return new Point3i(x, y, z);
		case 2: //SOUTH
			x = par2;
			y = par3;
			z = par4 - 1;
			return new Point3i(x, y, z);
		case 3: //NORTH
			x = par2;
			y = par3;
			z = par4 + 1;
			return new Point3i(x, y, z);
		case 4: //LEFT
			x = par2 - 1;
			y = par3;
			z = par4;
			return new Point3i(x, y, z);
		case 5: //RIGHT
			x = par2 + 1;
			y = par3;
			z = par4;
			return new Point3i(x, y, z);
		}

		return null;
	}

	private void breakWood(World par1World, int par2, int par3, int par4, Block par5Block, int par6)
	{
		ArrayList<ItemStack> itemstacks = par5Block.getDrops(par1World, par2, par3, par4, par6, 0);
		for(ItemStack itemstack : itemstacks)
		{
			if (itemstack != null)
			{
				ItemStack result;
				Block block = Block.getBlockFromItem(itemstack.getItem());
				result = MCP_Saw.woodPlanks.get(new WoodPlank(block, itemstack.getItemDamage()));
				if (result != null)
				{
					result.stackSize = par1World.rand.nextBoolean() ? 4 : par1World.rand.nextInt(3) + 1;
				}
				else
				{
					result = itemstack;
				}

				par1World.spawnEntityInWorld(new EntityItem(par1World, par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, result));
			}
		}

		par1World.setBlockToAir(par2, par3, par4);
	}
}
