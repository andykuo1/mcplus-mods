package com.minecraftplus.modSickle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.minecraftplus._common.item.ItemBase;

public class ItemSickle extends ItemBase
{
	public ItemSickle()
	{
		super(CreativeTabs.tabTools);

		this.setMaxStackSize(1);
		this.setMaxDamage(324);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase par7EntityLivingBase)
	{
		par1ItemStack.damageItem(1, par7EntityLivingBase);
		return super.onBlockDestroyed(par1ItemStack, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, par7EntityLivingBase);
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int metadata)
	{
		return block instanceof BlockCrops || block.getMaterial() == Material.gourd || block.getMaterial() == Material.plants || block.getMaterial() == Material.grass || block.getMaterial() == Material.vine ? super.getDigSpeed(itemstack, block, metadata) : 0F;
	}
}
