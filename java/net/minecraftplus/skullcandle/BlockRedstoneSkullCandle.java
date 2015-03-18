package net.minecraftplus.skullcandle;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRedstoneSkullCandle extends BlockSkull
{
	protected BlockRedstoneSkullCandle()
	{
		super();
		this.setLightLevel(0.5F);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundTypePiston);
	}

	@Override
	public boolean canProvidePower()
	{
		return true;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return 15;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
	{
		int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
		double d0 = (double)((float)p_149734_2_ + 0.5F) + (double)(p_149734_5_.nextFloat() - 0.5F) * 0.2D;
		double d1 = (double)((float)p_149734_3_ + 0.7F) + (double)(p_149734_5_.nextFloat() - 0.5F) * 0.2D;
		double d2 = (double)((float)p_149734_4_ + 0.5F) + (double)(p_149734_5_.nextFloat() - 0.5F) * 0.2D;
		double d3 = 0.2199999988079071D;
		double d4 = 0.27000001072883606D;

		if (l == 1)
		{
			p_149734_1_.spawnParticle("reddust", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		}
		else if (l == 2)
		{
			p_149734_1_.spawnParticle("reddust", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		}
		else if (l == 3)
		{
			p_149734_1_.spawnParticle("reddust", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
		}
		else if (l == 4)
		{
			p_149734_1_.spawnParticle("reddust", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
		}
		else
		{
			p_149734_1_.spawnParticle("reddust", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityRedstoneSkullCandle();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return ModSkullCandle.redstoneSkullCandle;
	}

	@Override
	public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
	{
		TileEntity tileentity = p_149643_1_.getTileEntity(p_149643_2_, p_149643_3_, p_149643_4_);
		return tileentity != null && tileentity instanceof TileEntityRedstoneSkullCandle ? ((TileEntityRedstoneSkullCandle)tileentity).func_145904_a() : super.getDamageValue(p_149643_1_, p_149643_2_, p_149643_3_, p_149643_4_);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, int p_149749_6_, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		{
			if ((p_149749_6_ & 8) == 0)
			{
				ItemStack itemstack = new ItemStack(Items.skull, 1, this.getDamageValue(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_));
				TileEntityRedstoneSkullCandle tileentityskull = (TileEntityRedstoneSkullCandle)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

				if (tileentityskull == null) return ret;

				if (tileentityskull.func_145904_a() == 3 && tileentityskull.func_152108_a() != null)
				{
					itemstack.setTagCompound(new NBTTagCompound());
					NBTTagCompound nbttagcompound = new NBTTagCompound();
					NBTUtil.func_152460_a(nbttagcompound, tileentityskull.func_152108_a());
					itemstack.getTagCompound().setTag("SkullOwner", nbttagcompound);
				}

				ret.add(itemstack);
				ret.add(new ItemStack(Blocks.redstone_torch));
			}
		}
		return ret;
	}

	@Override
	public void func_149965_a(World p_149965_1_, int p_149965_2_, int p_149965_3_, int p_149965_4_, TileEntitySkull p_149965_5_) {}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemIconName()
	{
		return null;
	}
}