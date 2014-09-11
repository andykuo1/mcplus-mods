package com.minecraftplus.modMortar;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMortar extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon, bottomIcon;

	/**
	 * Is the random generator used by mortar to drop the inventory contents in random directions.
	 */
	private final Random mortarRand = new Random();

	/**
	 * This flag is used to prevent the mortar inventory to be dropped upon block removal, is used internally when the
	 * mortar block changes from idle to active and vice-versa.
	 */
	private static boolean keepDisposerInventory;

	protected BlockMortar()
	{
		super(Material.rock);

		this.setHardness(2.0F);
		this.setStepSound(Block.soundTypePiston);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = IconRegistry.add(par1IIconRegister, this, ".top");
		this.sideIcon = IconRegistry.add(par1IIconRegister, this, ".side");
		this.bottomIcon = IconRegistry.add(par1IIconRegister, this, ".bottom");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return par1 == 1 ? this.blockIcon : (par1 == 0 ? this.bottomIcon : this.sideIcon);
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.setDefaultDirection(par1World, par2, par3, par4);
	}

	protected void setDefaultDirection(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			Block block = par1World.getBlock(par2, par3, par4 - 1);
			Block block1 = par1World.getBlock(par2, par3, par4 + 1);
			Block block2 = par1World.getBlock(par2 - 1, par3, par4);
			Block block3 = par1World.getBlock(par2 + 1, par3, par4);

			byte b0 = 3;

			if (block.func_149730_j() && block1.func_149730_j())
			{
				b0 = 3;
			}

			if (block1.func_149730_j() && block.func_149730_j())
			{
				b0 = 2;
			}

			if (block2.func_149730_j() && block3.func_149730_j())
			{
				b0 = 5;
			}

			if (block3.func_149730_j() && block2.func_149730_j())
			{
				b0 = 4;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
		}
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
			TileEntityMortar tileentitymortar = (TileEntityMortar)par1World.getTileEntity(par2, par3, par4);

			if (tileentitymortar != null)
			{
				par5EntityPlayer.openGui(MCP_Mortar.INSTANCE, 0, par1World, par2, par3, par4);
			}

			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		if (tileentity instanceof TileEntityMortar && ((TileEntityMortar) tileentity).mortarBurnTime > 0)
		{
			int l = par1World.getBlockMetadata(par2, par3, par4);
			float f = (float)par2 + 0.5F;
			float f1 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
			float f2 = (float)par4 + 0.5F;
			float f3 = 0.9F;
			float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

			par1World.spawnParticle("smoke", (double)(f + f4), (double)f1 + f3, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", (double)(f + f4), (double)f1 + f3, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
		}
	}

	public static void updateDisposerBlockState(boolean par0, World par1World, int par2, int par3, int par4)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);

		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		keepDisposerInventory = false;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

		if (tileentity != null)
		{
			tileentity.validate();
			par1World.setTileEntity(par2, par3, par4, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		return new TileEntityMortar();
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}

		if (l == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}

		if (l == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}

		if (l == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
		}

		if (par6ItemStack.hasDisplayName())
		{
			((TileEntityMortar) par1World.getTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5Block, int par6)
	{
		if (!keepDisposerInventory)
		{
			TileEntityMortar tileentitymortar = (TileEntityMortar) par1World.getTileEntity(par2, par3, par4);

			if (tileentitymortar != null)
			{
				for (int j1 = 0; j1 < tileentitymortar.getSizeInventory(); ++j1)
				{
					ItemStack itemstack = tileentitymortar.getStackInSlot(j1);

					if (itemstack != null)
					{
						float f = this.mortarRand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.mortarRand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.mortarRand.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0)
						{
							int k1 = this.mortarRand.nextInt(21) + 10;

							if (k1 > itemstack.stackSize)
							{
								k1 = itemstack.stackSize;
							}

							itemstack.stackSize -= k1;
							EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

							if (itemstack.hasTagCompound())
							{
								entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
							}

							float f3 = 0.05F;
							entityitem.motionX = (double)((float)this.mortarRand.nextGaussian() * f3);
							entityitem.motionY = (double)((float)this.mortarRand.nextGaussian() * f3 + 0.2F);
							entityitem.motionZ = (double)((float)this.mortarRand.nextGaussian() * f3);
							par1World.spawnEntityInWorld(entityitem);
						}
					}
				}

				par1World.func_147453_f(par2, par3, par4, par5Block);
			}
		}

		super.breakBlock(par1World, par2, par3, par4, par5Block, par6);
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
	{
		return Container.calcRedstoneFromInventory((IInventory) par1World.getTileEntity(par2, par3, par4));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getPickBlock(MovingObjectPosition par1MovingObjectPosition, World par2World, int par3, int par4, int par5)
	{
		return new ItemStack(Item.getItemFromBlock(MCP_Mortar.mortar));
	}
}
