package com.minecraftplus.modLock;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIronLock extends Item
{
	public ItemIronLock()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
		{
			return false;
		}
		else
		{
			EntityLock entitylock = new EntityLock(par3World, par4, par5, par6, par7, par2EntityPlayer.getCommandSenderName());

			if (entitylock != null && entitylock.onValidSurface())
			{
				if (!par3World.isRemote)
				{
					par3World.spawnEntityInWorld(entitylock);
					if (par1ItemStack.hasDisplayName())
					{
						entitylock.setCustomName(par1ItemStack.getDisplayName());
					}
				}
				--par1ItemStack.stackSize;
			}

			return true;
		}
	}
}