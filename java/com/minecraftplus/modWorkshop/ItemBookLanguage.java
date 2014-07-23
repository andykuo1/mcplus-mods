package com.minecraftplus.modWorkshop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.LanguageRegistry;

public class ItemBookLanguage extends ItemFood
{
	public ItemBookLanguage()
	{
		super(0, 0F, false);
		this.setCreativeTab(MCP_Workshop.tabWorkshop);
		this.setTextureName("book_normal");
		this.setAlwaysEdible();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
	{
		p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
		return p_77659_1_;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		LanguageRegistry.createLangFile("en_US");
		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}
}
