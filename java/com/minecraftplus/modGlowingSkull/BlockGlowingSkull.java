package com.minecraftplus.modGlowingSkull;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGlowingSkull extends BlockSkull
{
	protected BlockGlowingSkull()
	{
		super();
		this.setLightLevel(1.0F);
		this.setHardness(1.0F);
		this.setStepSound(Block.soundTypePiston);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityGlowingSkull();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return MCP_GlowingSkull.glowingSkull;
	}

	@Override
	public int getDamageValue(World p_149643_1_, int p_149643_2_, int p_149643_3_, int p_149643_4_)
	{
		TileEntity tileentity = p_149643_1_.getTileEntity(p_149643_2_, p_149643_3_, p_149643_4_);
		return tileentity != null && tileentity instanceof TileEntityGlowingSkull ? ((TileEntityGlowingSkull)tileentity).func_145904_a() : super.getDamageValue(p_149643_1_, p_149643_2_, p_149643_3_, p_149643_4_);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, int p_149749_6_, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		{
			if ((p_149749_6_ & 8) == 0)
			{
				ItemStack itemstack = new ItemStack(Items.skull, 1, this.getDamageValue(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_));
				TileEntityGlowingSkull tileentityskull = (TileEntityGlowingSkull)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

				if (tileentityskull == null) return ret;

				if (tileentityskull.func_145904_a() == 3 && tileentityskull.func_145907_c() != null && tileentityskull.func_145907_c().length() > 0)
				{
					itemstack.setTagCompound(new NBTTagCompound());
					itemstack.getTagCompound().setString("SkullOwner", tileentityskull.func_145907_c());
				}

				ret.add(itemstack);
				ret.add(new ItemStack(Blocks.torch));
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