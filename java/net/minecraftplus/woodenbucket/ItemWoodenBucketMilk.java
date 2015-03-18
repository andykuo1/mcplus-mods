package net.minecraftplus.woodenbucket;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWoodenBucketMilk extends Item
{
	public ItemWoodenBucketMilk()
	{
		this.setMaxStackSize(1);
		this.setMaxDamage(42);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par3EntityPlayer.capabilities.isCreativeMode)
		{
			--par1ItemStack.stackSize;
		}

		if (!par2World.isRemote)
		{
			par3EntityPlayer.curePotionEffects(par1ItemStack);
		}

		ItemStack itemstack = null;
		if (par1ItemStack.stackSize <= 0)
		{
			itemstack = new ItemStack(ModWoodenBucket.woodenBucketEmpty);
			itemstack.setItemDamage(par1ItemStack.getItemDamage());
			itemstack.setStackDisplayName(par1ItemStack.getDisplayName());
		}

		return itemstack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.drink;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@Override
	public ItemStack getContainerItem(ItemStack par1ItemStack)
	{
		ItemStack itemstack = new ItemStack(ModWoodenBucket.woodenBucketEmpty);
		itemstack.setItemDamage(par1ItemStack.getItemDamage());
		itemstack.setStackDisplayName(par1ItemStack.getDisplayName());
		return itemstack;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.getItem() == Items.stick || par2ItemStack.getItem() == Items.bowl || par2ItemStack.getItem() == ModWoodenBucket.woodenBucketEmpty ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}
}
