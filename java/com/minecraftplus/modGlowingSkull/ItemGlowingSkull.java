package com.minecraftplus.modGlowingSkull;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGlowingSkull extends ItemSkull
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
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 == 0)
		{
			return false;
		}
		else if (!par3World.getBlock(par4, par5, par6).getMaterial().isSolid())
		{
			return false;
		}
		else
		{
			if (par7 == 1)
			{
				++par5;
			}

			if (par7 == 2)
			{
				--par6;
			}

			if (par7 == 3)
			{
				++par6;
			}

			if (par7 == 4)
			{
				--par4;
			}

			if (par7 == 5)
			{
				++par4;
			}

			if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
			{
				return false;
			}
			else if (!MCP_GlowingSkull.glowingSkulls.canPlaceBlockAt(par3World, par4, par5, par6))
			{
				return false;
			}
			else
			{
				par3World.setBlock(par4, par5, par6, MCP_GlowingSkull.glowingSkulls, par7, 2);
				int i1 = 0;

				if (par7 == 1)
				{
					i1 = MathHelper.floor_double((double)(par2EntityPlayer.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
				}

				TileEntity tileentity = par3World.getTileEntity(par4, par5, par6);

				if (tileentity != null && tileentity instanceof TileEntityGlowingSkull)
				{
					String s = "";

					if (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("SkullOwner", 8))
					{
						s = par1ItemStack.getTagCompound().getString("SkullOwner");
					}

					((TileEntityGlowingSkull)tileentity).func_145905_a(par1ItemStack.getItemDamage(), s);
					((TileEntityGlowingSkull)tileentity).func_145903_a(i1);
					((BlockGlowingSkull)MCP_GlowingSkull.glowingSkulls).func_149965_a(par3World, par4, par5, par6, (TileEntityGlowingSkull)tileentity);
				}

				--par1ItemStack.stackSize;
				return true;
			}
		}
	}
}