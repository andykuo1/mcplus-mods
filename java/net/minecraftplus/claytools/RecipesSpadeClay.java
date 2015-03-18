package net.minecraftplus.claytools;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesSpadeClay implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		int[] slots = null;
		int i;

		for (i = 0; i < 3; i++)
		{
			ItemStack itemstack = par1InventoryCrafting.getStackInSlot(i);

			if (itemstack != null && Block.getBlockFromItem(itemstack.getItem()) == Blocks.stained_hardened_clay)
			{
				slots = new int[] {i, i + 3, i + 6};
				break;
			}
		}

		if (slots != null)
		{
			for (i = 0; i < par1InventoryCrafting.getSizeInventory(); i++)
			{
				ItemStack itemstack = par1InventoryCrafting.getStackInSlot(i);
				if (i == slots[0])
				{
					if (itemstack == null || Block.getBlockFromItem(itemstack.getItem()) != Blocks.stained_hardened_clay)
					{
						return false;
					}
				}
				else if (i == slots[1])
				{
					if (itemstack == null || itemstack.getItem() != Items.stick)
					{
						return false;
					}
				}
				else if (i == slots[2])
				{
					if (itemstack == null || itemstack.getItem() != Items.stick)
					{
						return false;
					}
				}
				else
				{
					if (par1InventoryCrafting.getStackInSlot(i) != null)
					{
						return false;
					}
				}
			}
		}
		else
		{
			return false;
		}

		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		ItemStack itemstack = new ItemStack(ModClayTools.shovelClay);
		Item item = itemstack.getItem();
		int[] rgb = new int[3];
		int i = 0;
		int j = 0;

		int l = ((IDyeable.Item) item).getColor(itemstack);
		float f = (float)(l >> 16 & 255) / 255.0F;
		float f1 = (float)(l >> 8 & 255) / 255.0F;
		float f2 = (float)(l & 255) / 255.0F;
		i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0F);
		rgb[0] = (int)((float)rgb[0] + f * 255.0F);
		rgb[1] = (int)((float)rgb[1] + f1 * 255.0F);
		rgb[2] = (int)((float)rgb[2] + f2 * 255.0F);
		++j;

		for(int k = 0; k < par1InventoryCrafting.getSizeInventory(); k++)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(k);

			if (itemstack1 != null && Block.getBlockFromItem(itemstack1.getItem()) == Blocks.stained_hardened_clay)
			{
				float[] afloat = EntitySheep.fleeceColorTable[itemstack1.getItemDamage()];
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
		((IDyeable.Item) item).setColor(itemstack, i1);
		return itemstack;
	}

	@Override
	public int getRecipeSize()
	{
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return new ItemStack(ModClayTools.shovelClay);
	}
}
