package net.minecraftplus.mcp_gift_box;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftplus._api.minecraft.base.RecipesShaped;

public class RecipesGiftBox extends RecipesShaped
{
	public RecipesGiftBox(int width, int height, Item output, char... inputs)
	{
		super(width, height, true, new ItemStack((ItemGiftBox)output), inputs);
	}

	@Override
	public boolean checkItems(char itemtype, ItemStack itemstack1)
	{
		if (itemstack1 == null) return itemtype == ' ';

		switch(itemtype)
		{
		case 'X':
			return itemstack1.getItem() == Items.paper;
		case '#':
			return true;
		}

		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting)
	{
		ItemStack itemstack = inventorycrafting.getStackInSlot(4);
		ItemStack output = this.getRecipeOutput();

		NBTTagCompound nbttagcompound = output.hasTagCompound() ? output.getTagCompound() : new NBTTagCompound();
		NBTTagList nbttaglist = new NBTTagList();

		if (itemstack != null)
		{
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setByte("Slot", (byte) 0);
			itemstack.writeToNBT(nbttagcompound1);
			nbttaglist.appendTag(nbttagcompound1);
		}

		NBTTagCompound nbttagcompound2 = new NBTTagCompound();
		nbttagcompound2.setTag("StackItems", nbttaglist);

		nbttagcompound.setTag("Inventory", nbttagcompound2);
		output.setTagCompound(nbttagcompound);

		return output;
	}
}