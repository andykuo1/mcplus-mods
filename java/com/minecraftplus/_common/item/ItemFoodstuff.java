package com.minecraftplus._common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFoodstuff extends ItemFood
{
	private Item returnItem;

	public ItemFoodstuff(int par1, float par2, boolean par3)
	{
		super(par1, par2, par3);
		this.setMaxStackSize(64);
	}

	public ItemFoodstuff(int par1, float par2)
	{
		this(par1, par2, false);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.add(par1IIconRegister, this);
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
		return this.returnItem != null ? new ItemStack(this.returnItem) : par1ItemStack;
	}

	public ItemFoodstuff setReturnItem(Item par1Item)
	{
		this.returnItem = par1Item;
		return this;
	}
}
