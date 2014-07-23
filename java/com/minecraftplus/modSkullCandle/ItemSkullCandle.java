package com.minecraftplus.modSkullCandle;

import java.util.UUID;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;
import com.mojang.authlib.GameProfile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSkullCandle extends ItemSkull
{
	private static final String[] skullTypes = new String[] {"skeleton", "wither", "zombie", "char", "creeper"};
	private IIcon[] itemIcons;

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int par1)
	{
		if (par1 < 0 || par1 >= skullTypes.length)
		{
			par1 = 0;
		}

		return this.itemIcons[par1];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcons = new IIcon[field_94587_a.length];

		for (int i = 0; i < itemIcons.length; ++i)
		{
			this.itemIcons[i] = IconRegistry.register(par1IIconRegister, this, "." + this.skullTypes[i]);
		}
	}

	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if(p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_).isReplaceable(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_) && p_77648_7_ != 0)
		{
			p_77648_7_ = 1;
			p_77648_5_--;
		}
		if (p_77648_7_ == 0)
		{
			return false;
		}
		else if (!p_77648_3_.isSideSolid(p_77648_4_, p_77648_5_, p_77648_6_, net.minecraftforge.common.util.ForgeDirection.getOrientation(p_77648_7_)))
		{
			return false;
		}
		else
		{
			if (p_77648_7_ == 1)
			{
				++p_77648_5_;
			}

			if (p_77648_7_ == 2)
			{
				--p_77648_6_;
			}

			if (p_77648_7_ == 3)
			{
				++p_77648_6_;
			}

			if (p_77648_7_ == 4)
			{
				--p_77648_4_;
			}

			if (p_77648_7_ == 5)
			{
				++p_77648_4_;
			}

		}
		{
			if (!p_77648_3_.isRemote)
			{
				if (!Blocks.skull.canPlaceBlockOnSide(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_)) return false;
				p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, MCP_SkullCandle.skullCandles, p_77648_7_, 2);
				int i1 = 0;

				if (p_77648_7_ == 1)
				{
					i1 = MathHelper.floor_double((double)(p_77648_2_.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
				}

				TileEntity tileentity = p_77648_3_.getTileEntity(p_77648_4_, p_77648_5_, p_77648_6_);

				if (tileentity != null && tileentity instanceof TileEntitySkullCandle)
				{
					if (p_77648_1_.getItemDamage() == 3)
					{
						GameProfile gameprofile = null;

						if (p_77648_1_.hasTagCompound())
						{
							NBTTagCompound nbttagcompound = p_77648_1_.getTagCompound();

							if (nbttagcompound.hasKey("SkullOwner", 10))
							{
								gameprofile = NBTUtil.func_152459_a(nbttagcompound.getCompoundTag("SkullOwner"));
							}
							else if (nbttagcompound.hasKey("SkullOwner", 8) && nbttagcompound.getString("SkullOwner").length() > 0)
							{
								gameprofile = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
							}
						}

						((TileEntitySkullCandle)tileentity).func_152106_a(gameprofile);
					}
					else
					{
						((TileEntitySkullCandle)tileentity).func_152107_a(p_77648_1_.getItemDamage());
					}

					((TileEntitySkullCandle)tileentity).func_145903_a(i1);
					((BlockRedstoneSkullCandle)MCP_SkullCandle.skullCandles).func_149965_a(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, (TileEntitySkullCandle)tileentity);
				}

				--p_77648_1_.stackSize;
			}

			return true;
		}
	}
}
