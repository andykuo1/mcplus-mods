package net.minecraftplus.soulextractor;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSoulExtractor extends BlockContainer
{
	/**
	 * Is the random generator used by loom to drop the inventory contents in random directions.
	 */
	private final Random soulExtractorRand = new Random();

	@SideOnly(Side.CLIENT)
	private IIcon blockIcon1;
	@SideOnly(Side.CLIENT)
	private IIcon blockIcon2;

	/**
	 * This flag is used to prevent the soul extractor inventory to be dropped upon block removal, is used internally when the
	 * soul extractor block changes from idle to active and vice-versa.
	 */
	private static boolean keepSoulExtractorInventory;

	protected BlockSoulExtractor()
	{
		super(Material.rock);
		this.setHardness(5.0F);
		this.setResistance(2000.0F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
		this.setLightOpacity(0);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister parIIconRegister)
	{
		this.blockIcon = IconRegistry.add(parIIconRegister, this, "", "side");
		this.blockIcon1 = IconRegistry.add(parIIconRegister, this, "", "top");
		this.blockIcon2 = IconRegistry.add(parIIconRegister, this, "", "bottom");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return par1 == 0 ? this.blockIcon2 : (par1 == 1 ? this.blockIcon1 : this.blockIcon);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);

		for (int l = 0; l < 2; ++l)
		{
			double d0 = (double)((float)par2 + par5Random.nextFloat());
			double d1 = (double)((float)par3 + par5Random.nextFloat());
			d0 = (double)((float)par4 + par5Random.nextFloat());
			double d2 = 0.0D;
			double d3 = 0.0D;
			double d4 = 0.0D;
			int i1 = par5Random.nextInt(2) * 2 - 1;
			int j1 = par5Random.nextInt(2) * 2 - 1;
			d2 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
			d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
			d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
			double d5 = (double)par4 + 0.5D + 0.25D * (double)j1;
			d4 = (double)(par5Random.nextFloat() * 1.0F * (float)j1);
			double d6 = (double)par2 + 0.5D + 0.25D * (double)i1;
			d2 = (double)(par5Random.nextFloat() * 1.0F * (float)i1);
			par1World.spawnParticle("portal", d6, d1, d5, d2, d3, d4);
		}

		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		if (tileentity instanceof TileEntitySoulExtractor)
		{
			for(int i = 0; i < 360; i += TileEntitySoulExtractor.rand.nextInt(8) + 20)
			{
				par1World.spawnParticle("portal", posRotX(3D, i) + par2 + 0.5F, par3 + 1, posRotY(3D, i) + par4 + 0.5F, 0D, -1D, 0D);
			}
		}
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World par1World, int par2)
	{
		return new TileEntitySoulExtractor();
	}

	public static void updateSoulExtractorBlockState(boolean par0, World par1World, int par2, int par3, int par4)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		keepSoulExtractorInventory = false;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

		if (tileentity != null)
		{
			tileentity.validate();
			par1World.setTileEntity(par2, par3, par4, tileentity);
		}
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		if (tileentity instanceof TileEntitySoulExtractor && ((TileEntitySoulExtractor) tileentity).isUseableByPlayer(par5EntityPlayer))
		{
			par5EntityPlayer.openGui(ModSoulExtractor.INSTANCE, 0, par1World, par2, par3, par4);
		}
		return true;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);

		if (par6ItemStack.hasDisplayName())
		{
			((TileEntitySoulExtractor) par1World.getTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block parBlock, int par6)
	{
		if (!keepSoulExtractorInventory)
		{
			TileEntitySoulExtractor tileentitysoulextractor = (TileEntitySoulExtractor)par1World.getTileEntity(par2, par3, par4);

			if (tileentitysoulextractor != null)
			{
				for (int j1 = 0; j1 < tileentitysoulextractor.getSizeInventory(); ++j1)
				{
					ItemStack itemstack = tileentitysoulextractor.getStackInSlot(j1);

					if (itemstack != null)
					{
						float f = this.soulExtractorRand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.soulExtractorRand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.soulExtractorRand.nextFloat() * 0.8F + 0.1F;

						while (itemstack.stackSize > 0)
						{
							int k1 = this.soulExtractorRand.nextInt(21) + 10;

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
							entityitem.motionX = (double)((float)this.soulExtractorRand.nextGaussian() * f3);
							entityitem.motionY = (double)((float)this.soulExtractorRand.nextGaussian() * f3 + 0.2F);
							entityitem.motionZ = (double)((float)this.soulExtractorRand.nextGaussian() * f3);
							par1World.spawnEntityInWorld(entityitem);
						}
					}
				}

				par1World.func_147453_f(par2, par3, par4, parBlock);
			}
		}

		super.breakBlock(par1World, par2, par3, par4, parBlock, par6);
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
	{
		return ((TileEntitySoulExtractor) par1World.getTileEntity(par2, par3, par4)).isExtracting() ? 14 : 0;
	}

	public static double posRotX(double distance, double angle)
	{
		return Math.cos(angle * Math.PI / 180) * distance;
	}

	public static double posRotY(double distance, double angle)
	{
		return -Math.sin(angle * Math.PI / 180) * distance;
	}
}
