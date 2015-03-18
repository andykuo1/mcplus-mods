package net.minecraftplus.claytools;

import java.util.ArrayList;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesDyeable implements IRecipe
{
	private Item input;

	public RecipesDyeable(Item par1Item)
	{
		this.input = par1Item;
	}

	@Override
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		ItemStack itemstack = null;
		ArrayList arraylist = new ArrayList();

		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);

			if (itemstack1 != null)
			{
				if (itemstack1.getItem() == this.getRecipeInput())
				{
					if (itemstack != null)
					{
						return false;
					}

					itemstack = itemstack1;
				}
				else
				{
					if (itemstack1.getItem() != Items.dye)
					{
						return false;
					}

					arraylist.add(itemstack1);
				}
			}
		}

		return itemstack != null && !arraylist.isEmpty();
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		ItemStack itemstack = null;
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		Item item = null;
		int k;
		int l;
		float f;
		float f1;
		int i1;

		for (k = 0; k < par1InventoryCrafting.getSizeInventory(); ++k)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(k);

			if (itemstack1 != null)
			{
				if (itemstack1.getItem() == getRecipeInput())
				{
					item = itemstack1.getItem();

					if (itemstack != null)
					{
						return null;
					}

					itemstack = itemstack1.copy();
					itemstack.stackSize = 1;

					if (((IDyeable.Item) item).hasColor(itemstack1))
					{
						l = ((IDyeable.Item)item).getColor(itemstack);
						f = (float)(l >> 16 & 255) / 255.0F;
						f1 = (float)(l >> 8 & 255) / 255.0F;
						float f2 = (float)(l & 255) / 255.0F;
						i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0F);
						aint[0] = (int)((float)aint[0] + f * 255.0F);
						aint[1] = (int)((float)aint[1] + f1 * 255.0F);
						aint[2] = (int)((float)aint[2] + f2 * 255.0F);
						++j;
					}
				}
				else
				{
					if (itemstack1.getItem() != Items.dye)
					{
						return null;
					}

					float[] afloat = EntitySheep.fleeceColorTable[BlockColored.func_150031_c(itemstack1.getItemDamage())];
					int j1 = (int)(afloat[0] * 255.0F);
					int k1 = (int)(afloat[1] * 255.0F);
					i1 = (int)(afloat[2] * 255.0F);
					i += Math.max(j1, Math.max(k1, i1));
					aint[0] += j1;
					aint[1] += k1;
					aint[2] += i1;
					++j;
				}
			}
		}

		if (item == null)
		{
			return null;
		}
		else
		{
			k = aint[0] / j;
			int l1 = aint[1] / j;
			l = aint[2] / j;
			f = (float)i / (float)j;
			f1 = (float)Math.max(k, Math.max(l1, l));
			k = (int)((float)k * f / f1);
			l1 = (int)((float)l1 * f / f1);
			l = (int)((float)l * f / f1);
			i1 = (k << 8) + l1;
			i1 = (i1 << 8) + l;
			((IDyeable.Item) item).setColor(itemstack, i1);
			return itemstack;
		}
	}

	@Override
	public int getRecipeSize()
	{
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return null;
	}

	protected Item getRecipeInput()
	{
		return this.input;
	}
}
