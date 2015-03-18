package net.minecraftplus._common;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFoodstuff extends ItemFood
{
	private Item returnItem;

	public ItemFoodstuff(int parHunger, float parSaturation, boolean parIsWolfsFavoriteFood)
	{
		super(parHunger, parSaturation, parIsWolfsFavoriteFood);
		this.setMaxStackSize(64);
	}

	public ItemFoodstuff(int parHunger, float parSaturation)
	{
		this(parHunger, parSaturation, false);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister parIconRegistry)
	{
		this.itemIcon = IconRegistry.add(parIconRegistry, this);
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
