package com.minecraftplus.modIceBox;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIceBox extends BlockContainer
{
	protected BlockIceBox()
	{
		super(Material.anvil);
		this.setHardness(2.5F);
		this.setStepSound(soundTypeWood);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 22;
	}

	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
		TileEntityIceBox tileentityicebox = (TileEntityIceBox) p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_, p_149695_4_);

		if (tileentityicebox != null)
		{
			tileentityicebox.updateContainingBlockInfo();
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5Block, int par6)
	{
		TileEntityIceBox tileentityicebox = (TileEntityIceBox)par1World.getTileEntity(par2, par3, par4);

		if (tileentityicebox != null)
		{
			for (int i1 = 0; i1 < tileentityicebox.getSizeInventory(); ++i1)
			{
				ItemStack itemstack = tileentityicebox.getStackInSlot(i1);

				if (itemstack != null)
				{
					Random rand = new Random();
					float f = rand.nextFloat() * 0.8F + 0.1F;
					float f1 = rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World.spawnEntityInWorld(entityitem))
					{
						int j1 = rand.nextInt(21) + 10;

						if (j1 > itemstack.stackSize)
						{
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double)((float)rand.nextGaussian() * f3);
						entityitem.motionY = (double)((float)rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)rand.nextGaussian() * f3);

						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
					}
				}
			}

			par1World.func_147453_f(par2, par3, par4, par5Block);
		}

		super.breakBlock(par1World, par2, par3, par4, par5Block, par6);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (par1World.isRemote)
		{
			return true;
		}
		else
		{
			IInventory iinventory = this.getIceBoxInventory(par1World, par2, par3, par4);

			if (iinventory != null)
			{
				par5EntityPlayer.displayGUIChest(iinventory);
			}

			return true;
		}
	}

	public IInventory getIceBoxInventory(World par1World, int par2, int par3, int par4)
	{
		Object object = (TileEntityIceBox)par1World.getTileEntity(par2, par3, par4);

		if (object == null)
		{
			return null;
		}
		else if (par1World.isSideSolid(par2, par3 + 1, par4, DOWN))
		{
			return null;
		}
		else if (isOcelotBlocking(par1World, par2, par3, par4))
		{
			return null;
		}
		else if (par1World.getBlock(par2 - 1, par3, par4) == this && (par1World.isSideSolid(par2 - 1, par3 + 1, par4, DOWN) || isOcelotBlocking(par1World, par2 - 1, par3, par4)))
		{
			return null;
		}
		else if (par1World.getBlock(par2 + 1, par3, par4) == this && (par1World.isSideSolid(par2 + 1, par3 + 1, par4, DOWN) || isOcelotBlocking(par1World, par2 + 1, par3, par4)))
		{
			return null;
		}
		else if (par1World.getBlock(par2, par3, par4 - 1) == this && (par1World.isSideSolid(par2, par3 + 1, par4 - 1, DOWN) || isOcelotBlocking(par1World, par2, par3, par4 - 1)))
		{
			return null;
		}
		else if (par1World.getBlock(par2, par3, par4 + 1) == this && (par1World.isSideSolid(par2, par3 + 1, par4 + 1, DOWN) || isOcelotBlocking(par1World, par2, par3, par4 + 1)))
		{
			return null;
		}
		else
		{
			return (IInventory)object;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		TileEntityIceBox tileentityicebox = new TileEntityIceBox();
		return tileentityicebox;
	}

	private static boolean isOcelotBlocking(World par1World, int par2, int par3, int par4)
	{
		Iterator iterator = par1World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB((double)par2, (double)(par3 + 1), (double)par4, (double)(par2 + 1), (double)(par3 + 2), (double)(par4 + 1))).iterator();
		EntityOcelot entityocelot1;

		do
		{
			if (!iterator.hasNext())
			{
				return false;
			}

			EntityOcelot entityocelot = (EntityOcelot)iterator.next();
			entityocelot1 = (EntityOcelot)entityocelot;
		}
		while (!entityocelot1.isSitting());

		return true;
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
	{
		return Container.calcRedstoneFromInventory(this.getIceBoxInventory(p_149736_1_, p_149736_2_, p_149736_3_, p_149736_4_));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon("planks_oak");
	}
}