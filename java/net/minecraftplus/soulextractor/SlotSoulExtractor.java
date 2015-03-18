package net.minecraftplus.soulextractor;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSoulExtractor extends Slot
{
	private int slotType;

	public SlotSoulExtractor(IInventory par2IInventory, int par3, int par4, int par5, int par6)
	{
		super(par2IInventory, par3, par4, par5);
		this.slotType = par6;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return this.slotType == 0 ? par1ItemStack.getItem() == Items.glass_bottle : this.slotType == 1 ? par1ItemStack.getItem() == Item.getItemFromBlock(Blocks.soul_sand) : this.slotType == 2 ? par1ItemStack.getItem() == Items.ender_pearl : par1ItemStack.getItem() == Items.experience_bottle;
	}
}
