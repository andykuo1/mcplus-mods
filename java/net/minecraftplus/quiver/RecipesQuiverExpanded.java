package net.minecraftplus.quiver;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipesQuiverExpanded implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		ItemStack itemstack = null;

		for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i)
		{
			ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);

			if (itemstack1 != null)
			{
				if (i == 4)
				{
					if (itemstack1.getItem() instanceof ItemQuiver)
					{
						if (itemstack != null)
						{
							return false;
						}
						itemstack = itemstack1;
					}
				}
				else if (itemstack1.getItem() != Items.leather)
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}

		return itemstack != null;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		ItemStack itemstack = null;
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		ItemQuiver itemsatchel = null;
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
				if (itemstack1.getItem() instanceof ItemQuiver)
				{
					itemsatchel = (ItemQuiver) itemstack1.getItem();

					if (itemstack != null)
					{
						return null;
					}

					itemstack = itemstack1.copy();
					itemstack.stackSize = 1;

					if (itemsatchel.hasColor(itemstack1))
					{
						l = itemsatchel.getColor(itemstack);
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
			}
		}

		if (itemsatchel == null)
		{
			return null;
		}
		else
		{
			NBTTagCompound nbttag = itemstack.hasTagCompound() ? itemstack.getTagCompound() : new NBTTagCompound();
			int inventorysize = nbttag.hasKey("Size") ? itemstack.getTagCompound().getInteger("Size") + 2 : 3;

			if (inventorysize > 9)
			{
				return null;
			}

			nbttag.setInteger("Size", inventorysize);
			itemstack.setTagCompound(nbttag);
			//itemstack.setItemName(this.getStringName(itemstack, inventorysize));

			return itemstack;
		}
	}

	private String getStringName(ItemStack par1ItemStack, int par2)
	{
		String name = getStringWithoutPrefix(par1ItemStack.getDisplayName());
		if (par2 > 3 && par2 <= 6)
		{
			name = "Scout's " + name;
		}
		else if (par2 > 6)
		{
			name = "Archer's " + name;
		}

		return name;
	}

	private String getStringWithoutPrefix(String par1String)
	{
		par1String = par1String.contains("Scout's ") ? par1String.replace("Scout's ", "") : par1String;
		par1String = par1String.contains("Archer's ") ? par1String.replace("Archer's ", "") : par1String;
		return par1String;
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
}

