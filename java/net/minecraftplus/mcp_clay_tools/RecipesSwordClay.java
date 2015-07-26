package net.minecraftplus.mcp_clay_tools;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.minecraft.base.RecipesShaped;

public class RecipesSwordClay extends RecipesShaped
{
	public RecipesSwordClay(int width, int height, Item output, char... inputs)
	{
		super(width, height, true, new ItemStack((ItemSwordClay) output), inputs);
	}

	@Override
	public boolean checkItems(char itemtype, ItemStack itemstack1)
	{
		if (itemstack1 == null) return itemtype == ' ';

		switch(itemtype)
		{
		case 'X':
			return itemstack1.getItem() == Item.getItemFromBlock(Blocks.stained_hardened_clay);
		case '#':
			return itemstack1.getItem() == Items.stick;
		}

		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting)
	{
		ItemStack itemstack = this.getRecipeOutput();
		ItemSwordClay item = (ItemSwordClay) itemstack.getItem();
		int[] rgb = new int[3];
		int i = 0;
		int j = 0;

		int l = item.getColor(itemstack);
		float f = (float)(l >> 16 & 255) / 255.0F;
		float f1 = (float)(l >> 8 & 255) / 255.0F;
		float f2 = (float)(l & 255) / 255.0F;
		i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0F);
		rgb[0] = (int)((float)rgb[0] + f * 255.0F);
		rgb[1] = (int)((float)rgb[1] + f1 * 255.0F);
		rgb[2] = (int)((float)rgb[2] + f2 * 255.0F);
		++j;

		for(int k = 0; k < inventorycrafting.getSizeInventory(); k++)
		{
			ItemStack itemstack1 = inventorycrafting.getStackInSlot(k);
			if (itemstack1 != null && Block.getBlockFromItem(itemstack1.getItem()) == Blocks.stained_hardened_clay)
			{
				float[] afloat = EntitySheep.func_175513_a(EnumDyeColor.byMetadata(itemstack1.getMetadata()));
				int j1 = (int)(afloat[0] * 255.0F);
				int k1 = (int)(afloat[1] * 255.0F);
				int i1 = (int)(afloat[2] * 255.0F);
				i += Math.max(j1, Math.max(k1, i1));
				rgb[0] += j1;
				rgb[1] += k1;
				rgb[2] += i1;
				++j;
			}
		}

		int k = rgb[0] / j;
		int l1 = rgb[1] / j;
		l = rgb[2] / j;
		f = (float)i / (float)j;
		f1 = (float)Math.max(k, Math.max(l1, l));

		k = (int)((float)k * f / f1);
		l1 = (int)((float)l1 * f / f1);
		l = (int)((float)l * f / f1);
		int i1 = (k << 8) + l1;
		i1 = (i1 << 8) + l;
		item.setColor(itemstack, i1);
		return itemstack;
	}
}