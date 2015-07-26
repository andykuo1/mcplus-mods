package net.minecraftplus._api.minecraft.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLeftOverBase extends ItemFood
{
	private final ItemStack bowlItem;

	public ItemLeftOverBase(int parAmount, float parSaturation, Item parItem)
	{
		this(parAmount, parSaturation, new ItemStack(parItem));
	}

	public ItemLeftOverBase(int amount, float parSaturation, ItemStack parBowlItem)
	{
		super(amount, parSaturation, false);
		this.setMaxStackSize(1);

		this.bowlItem = parBowlItem;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		//Compare to: @ItemSoup
		super.onItemUseFinish(stack, worldIn, playerIn);
		return ItemStack.copyItemStack(this.bowlItem);
	}
}
