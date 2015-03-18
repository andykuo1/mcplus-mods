package net.minecraftplus.firepit;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFirePit extends BlockContainer
{
	private final Random rand = new Random();
	private final boolean isActive;
	private static boolean isUpdating;

	protected BlockFirePit(boolean par1)
	{
		super(Material.fire);
		this.setHardness(0.8F);
		this.setTickRandomly(true);

		this.isActive = par1;
		if (!this.isActive())
		{
			this.setCreativeTab(CreativeTabs.tabDecorations);
		}
		else
		{
			this.setLightLevel(1.0F);
		}

		this.setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 0.1F, 0.9F);
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegiser)
	{
		this.blockIcon = Blocks.cobblestone.getIcon(0, 0);
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return Item.getItemFromBlock(ModFirePit.firePit);
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
		return -1;
	}

	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		return ForgeHooks.canHarvestBlock(Blocks.stone, player, meta);
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (this.isActive() && !par5Entity.isBurning())
		{
			par5Entity.setFire(1);
		}
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.func_149930_e(par1World, par2, par3, par4);
	}

	private void func_149930_e(World par1World, int par2, int par3, int par4)
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
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (par1World.isRemote)
		{
			return true;
		}
		else
		{
			TileEntityFirePit tileentityfirepit = (TileEntityFirePit) par1World.getTileEntity(par2, par3, par4);
			if (!this.isActive())
			{
				if (par5EntityPlayer.getCurrentEquippedItem() != null)
				{
					if (par5EntityPlayer.getCurrentEquippedItem().getItem() == Items.stick)
					{
						if (tileentityfirepit != null && this.rand.nextInt(120) == 0)
						{
							this.igniteFirePit(tileentityfirepit, par5EntityPlayer);
						}
						else if (this.rand.nextInt(4) == 0)
						{
							par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "fire.ignite", 1.0F, par1World.rand.nextFloat() * 0.4F + 0.8F);
						}
					}
					else if (par5EntityPlayer.getCurrentEquippedItem().getItem() == Items.blaze_rod)
					{
						this.igniteFirePit(tileentityfirepit, par5EntityPlayer);
					}
					else if (par5EntityPlayer.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
					{
						if (tileentityfirepit != null && this.rand.nextInt(14) == 0)
						{
							this.igniteFirePit(tileentityfirepit, par5EntityPlayer);
						}
						else if (this.rand.nextInt(4) == 0)
						{
							par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "fire.ignite", 1.0F, par1World.rand.nextFloat() * 0.4F + 0.8F);
						}
					}
				}
			}
			else
			{
				if (tileentityfirepit != null)
				{
					par5EntityPlayer.openGui(ModFirePit.INSTANCE, 0, par1World, par2, par3, par4);
				}
			}

			return true;
		}
	}

	private void igniteFirePit(TileEntityFirePit par1TileEntityFirePit, EntityPlayer par2EntityPlayer)
	{
		if (!par2EntityPlayer.capabilities.isCreativeMode)
		{
			if (par2EntityPlayer.getCurrentEquippedItem() != null && par2EntityPlayer.getCurrentEquippedItem().isItemStackDamageable())
			{
				par2EntityPlayer.getCurrentEquippedItem().damageItem(1, par2EntityPlayer);
			}
			else
			{
				par2EntityPlayer.getCurrentEquippedItem().stackSize--;
			}
		}

		if (par1TileEntityFirePit.getStackInSlot(0) == null)
		{
			par1TileEntityFirePit.setInventorySlotContents(0, new ItemStack(Blocks.log, -1));
		}

		if (par1TileEntityFirePit.getStackInSlot(1) == null)
		{
			par1TileEntityFirePit.setInventorySlotContents(1, new ItemStack(Items.stick));
		}

		par1TileEntityFirePit.markDirty();
	}

	/**
	 * Update which block the fire pit is using depending on whether or not it is burning
	 */
	public static void updateFirePitBlockState(boolean par1, World par2World, int par3, int par4, int par5)
	{
		int l = par2World.getBlockMetadata(par3, par4, par5);
		TileEntity tileentity = par2World.getTileEntity(par3, par4, par5);
		isUpdating = true;

		if (par1)
		{
			par2World.setBlock(par3, par4, par5, ModFirePit.firePitLit);

			TileEntityFirePit tileentityfirepit = (TileEntityFirePit) tileentity;
			if (tileentityfirepit.getStackInSlot(0) != null && tileentityfirepit.getStackInSlot(0).stackSize <= 0)
			{
				tileentityfirepit.setInventorySlotContents(0, null);
			}
		}
		else
		{
			par2World.setBlock(par3, par4, par5, ModFirePit.firePit);
		}

		isUpdating = false;
		par2World.setBlockMetadataWithNotify(par3, par4, par5, l, 2);

		if (tileentity != null)
		{
			tileentity.validate();
			par2World.setTileEntity(par3, par4, par5, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityFirePit();
	}

	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
		return super.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_) && this.canBlockStay(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_);
	}

	@Override
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
	{
		Block block = p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_);
		if (block != null && block != Blocks.air && block.isNormalCube()) return true;

		return false;
	}

	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
		this.checkAndDropBlock(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		this.checkAndDropBlock(par1World, par2, par3, par4);
	}

	protected void checkAndDropBlock(World p_149855_1_, int p_149855_2_, int p_149855_3_, int p_149855_4_)
	{
		if (!this.canBlockStay(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_))
		{
			this.dropBlockAsItem(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_, p_149855_1_.getBlockMetadata(p_149855_2_, p_149855_3_, p_149855_4_), 0);
			p_149855_1_.setBlock(p_149855_2_, p_149855_3_, p_149855_4_, Blocks.air, 0, 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World par1World, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
	{
		int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0)
		{
			par1World.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
		}

		if (l == 1)
		{
			par1World.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
		}

		if (l == 2)
		{
			par1World.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
		}

		if (l == 3)
		{
			par1World.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
		}

		if (p_149689_6_.hasDisplayName())
		{
			((TileEntityFirePit) par1World.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145951_a(p_149689_6_.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World par1World, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
	{
		if (!isUpdating)
		{
			TileEntityFirePit tileentityfirepit = (TileEntityFirePit) par1World.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

			if (tileentityfirepit != null)
			{
				for (int i1 = 0; i1 < tileentityfirepit.getSizeInventory(); ++i1)
				{
					ItemStack itemstack = tileentityfirepit.getStackInSlot(i1);

					if (itemstack != null)
					{
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0)
						{
							int j1 = this.rand.nextInt(21) + 10;

							if (j1 > itemstack.stackSize)
							{
								j1 = itemstack.stackSize;
							}

							itemstack.stackSize -= j1;
							EntityItem entityitem = new EntityItem(par1World, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

							if (itemstack.hasTagCompound())
							{
								entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
							}

							float f3 = 0.05F;
							entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
							entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
							entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
							par1World.spawnEntityInWorld(entityitem);
						}
					}
				}

				par1World.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
			}
		}

		super.breakBlock(par1World, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random par5Random)
	{
		if (this.isActive())
		{
			int l = par1World.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
			float f = (float)p_149734_2_ + 0.5F;
			float f1 = (float)p_149734_3_ + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
			float f2 = (float)p_149734_4_ + 0.5F;

			float f3 = par5Random.nextFloat() * 0.1F;
			float f4 = par5Random.nextFloat() * 0.4F - 0.2F;
			float f5 = par5Random.nextFloat() * 0.4F - 0.2F;

			par1World.spawnParticle("smoke", (double)(f + f4), (double) (f1 + f3), (double)(f2 + f5), 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", (double)(f + f4), (double) (f1 + f3), (double)(f2 + f5), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
	{
		return Container.calcRedstoneFromInventory((IInventory)p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getItem(World par1World, int par2, int par3, int par4)
	{
		return Item.getItemFromBlock(ModFirePit.firePit);
	}

	@Override
	public MapColor getMapColor(int p_149728_1_)
	{
		return MapColor.redColor;
	}

	public boolean isActive()
	{
		return this.isActive;
	}
}