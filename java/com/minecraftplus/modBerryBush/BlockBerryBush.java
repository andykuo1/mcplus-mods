package com.minecraftplus.modBerryBush;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBerryBush extends BlockBush implements IGrowable, IShearable
{
	private Item berry;

	@SideOnly(Side.CLIENT)
	private IIcon[] itemIcons;
	private IIcon itemBaseIcon;

	public BlockBerryBush(Item par1Item)
	{
		super();
		this.setHardness(0.0F);
		this.setStepSound(soundTypeGrass);
		this.disableStats();

		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);

		this.berry = par1Item;
	}

	@Override
	public int getRenderType()
	{
		return MCP_BerryBush.renderBerryBush.getRenderID();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getBlockColor()
	{
		double d0 = 0.5D;
		double d1 = 1.0D;
		return ColorizerGrass.getGrassColor(d0, d1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderColor(int par1)
	{
		return ColorizerGrass.getGrassColor(0.5D, 1.0D);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor(par2, par3, par4);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1)
	{
		if (par1 == -1)
		{
			return this.itemBaseIcon;
		}

		if (par1 < 7)
		{
			if (par1 == 6)
			{
				par1 = 5;
			}

			return this.itemIcons[par1 >> 1];
		}
		else
		{
			return this.itemIcons[3];
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcons = new IIcon[4];

		for (int i = 0; i < this.itemIcons.length; ++i)
		{
			this.itemIcons[i] = IconRegistry.register(par1IIconRegister, this, "_stage_" + i);
		}

		this.blockIcon = IconRegistry.register(par1IIconRegister, this.getUnlocalizedName());
		this.itemBaseIcon = IconRegistry.register(par1IIconRegister, "tile.berry_bush");
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);
		if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
		{
			int l = par1World.getBlockMetadata(par2, par3, par4);

			if (l < 7)
			{
				if (par5Random.nextInt(26 + 1) == 0)
				{
					++l;
					par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
				}
			}
		}
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return par1 > 1 ? this.berry : null;
	}

	@Override
	public int quantityDropped(int par1, int par2, Random par3Random)
	{
		return par3Random.nextInt((par1 / 2) + par2 + 1) + par1 > 3 ? 1 : 0;
	}

	@Override
	public boolean func_149851_a(World par1World, int par2, int par3, int par4, boolean par5)
	{
		return par1World.getBlockMetadata(par2, par3, par4) != 7;
	}

	@Override
	public boolean func_149852_a(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		return true;
	}

	@Override
	public void func_149853_b(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int l = par1World.getBlockMetadata(par3, par4, par5) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

		if (l > 7)
		{
			l = 7;
		}

		par1World.setBlockMetadataWithNotify(par3, par4, par5, l, 2);
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		Random rand = new Random();
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this));
		int meta = world.getBlockMetadata(x, y, z);
		ret.add(new ItemStack(this.berry, this.quantityDropped(meta, fortune, rand)));
		return ret;
	}
}
