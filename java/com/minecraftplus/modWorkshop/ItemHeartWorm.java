package com.minecraftplus.modWorkshop;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;
import com.minecraftplus._base.registry.LanguageRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHeartWorm extends ItemFood
{
	public ItemHeartWorm()
	{
		super(0, 0.0F, false);
		this.setCreativeTab(MCP_Workshop.tabWorkshop);
		this.setAlwaysEdible();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.attackEntityFrom(DamageSource.starve, 2F);
		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}
}
