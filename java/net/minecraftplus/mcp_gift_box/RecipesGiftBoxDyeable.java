package net.minecraftplus.mcp_gift_box;

import java.util.ArrayList;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.google.common.collect.Lists;

public class RecipesGiftBoxDyeable implements IRecipe
{
	public final ItemGiftBox recipeItem;

	public RecipesGiftBoxDyeable(ItemGiftBox input)
	{
		this.recipeItem = input;
	}

	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World worldIn)
	{
		//Compare To: @RecipesArmorDyes
		ItemStack itemstack = null;
		ArrayList arraylist = Lists.newArrayList();

		for (int i = 0; i < p_77569_1_.getSizeInventory(); ++i)
		{
			ItemStack itemstack1 = p_77569_1_.getStackInSlot(i);

			if (itemstack1 != null)
			{
				if (itemstack1.getItem() == this.recipeItem)
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
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		//Compare To: @RecipesArmorDyes
		ItemStack itemstack = null;
		int[] aint = new int[3];
		int[] bint = new int[3];
		int i = 0;
		int j = 0;
		int ai = 0;
		int bi = 0;

		Item item = null;
		int k;
		int l;
		float f;
		float f1;
		int l1;

		for (k = 0; k < p_77572_1_.getSizeInventory(); ++k)
		{
			ItemStack itemstack1 = p_77572_1_.getStackInSlot(k);

			if (itemstack1 != null)
			{
				if (itemstack1.getItem() == this.recipeItem)
				{
					item = this.recipeItem;

					if (itemstack != null)
					{
						return null;
					}

					itemstack = itemstack1.copy();
					itemstack.stackSize = 1;

					if (this.recipeItem.hasColor(itemstack1))
					{
						float r, g, b;

						int color = this.recipeItem.getColor(itemstack);
						r = (float)(color >> 16 & 255) / 255.0F;
						g = (float)(color >> 8 & 255) / 255.0F;
						b = (float)(color & 255) / 255.0F;
						i = (int)((float)i + Math.max(r, Math.max(g, b)) * 255.0F);
						aint[0] = (int)((float)aint[0] + r * 255.0F);
						aint[1] = (int)((float)aint[1] + g * 255.0F);
						aint[2] = (int)((float)aint[2] + b * 255.0F);
						++ai;

						int coloralt = this.recipeItem.getColorAlt(itemstack);
						r = (float)(coloralt >> 16 & 255) / 255.0F;
						g = (float)(coloralt >> 8 & 255) / 255.0F;
						b = (float)(coloralt & 255) / 255.0F;
						j = (int)((float)j + Math.max(r, Math.max(g, b)) * 255.0F);
						bint[0] = (int)((float)bint[0] + r * 255.0F);
						bint[1] = (int)((float)bint[1] + g * 255.0F);
						bint[2] = (int)((float)bint[2] + b * 255.0F);
						++bi;
					}
				}
				else
				{
					if (itemstack1.getItem() != Items.dye) return null;

					float[] afloat = EntitySheep.func_175513_a(EnumDyeColor.byDyeDamage(itemstack1.getMetadata()));
					int j1 = (int)(afloat[0] * 255.0F);
					int k1 = (int)(afloat[1] * 255.0F);
					l1 = (int)(afloat[2] * 255.0F);

					if (item == null)
					{
						i += Math.max(j1, Math.max(k1, l1));
						aint[0] += j1;
						aint[1] += k1;
						aint[2] += l1;
						++ai;
					}
					else
					{
						j += Math.max(j1, Math.max(k1, l1));
						bint[0] += j1;
						bint[1] += k1;
						bint[2] += l1;
						++bi;
					}
				}
			}
		}

		if (item == null)
		{
			return null;
		}
		else
		{
			float avg, max;
			int color = this.recipeItem.getDefaultColor(), coloralt = this.recipeItem.getDefaultColorAlt();

			if (ai > 0)
			{
				int ar = aint[0] / ai;
				int ag = aint[1] / ai;
				int ab = aint[2] / ai;

				avg = (float) i / (float) ai;
				max = (float) Math.max(ar, Math.max(ag, ab));

				ar = (int)((float)ar * avg / max);
				ag = (int)((float)ag * avg / max);
				ab= (int)((float)ab * avg / max);

				color = (ar << 8) + ag;
				color = (color << 8) + ab;
			}

			if (bi > 0)
			{
				int br = bint[0] / bi;
				int bg = bint[1] / bi;
				int bb = bint[2] / bi;

				avg = (float) j / (float) bi;
				max = (float) Math.max(br, Math.max(bg, bb));

				br = (int)((float)br * avg / max);
				bg = (int)((float)bg * avg / max);
				bb = (int)((float)bb * avg / max);

				coloralt = (br << 8) + bg;
				coloralt = (coloralt << 8) + bb;
			}

			this.recipeItem.setColor(itemstack, color, coloralt);
			return itemstack;
		}
	}

	@Override
	public int getRecipeSize()
	{
		//Compare To: @RecipesArmorDyes
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return new ItemStack(this.recipeItem);
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting p_179532_1_)
	{
		//Compare To: @RecipesArmorDyes
		ItemStack[] aitemstack = new ItemStack[p_179532_1_.getSizeInventory()];

		for (int i = 0; i < aitemstack.length; ++i)
		{
			ItemStack itemstack = p_179532_1_.getStackInSlot(i);
			aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
		}

		return aitemstack;
	}
}
