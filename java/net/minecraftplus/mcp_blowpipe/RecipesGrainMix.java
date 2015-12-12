package net.minecraftplus.mcp_blowpipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesGrainMix implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World worldIn)
	{
		boolean bowl = false;
		int seedCount = 0;
		for (int i = 0; i < p_77569_1_.getSizeInventory(); ++i)
		{
			ItemStack itemstack = p_77569_1_.getStackInSlot(i);

			if (itemstack != null)
			{
				if (itemstack.getItem() instanceof ItemSeeds && ++seedCount > 3)
				{
					return false;
				}
				else if (itemstack.getItem() == Items.bowl)
				{
					if (bowl) return false;
					bowl = true;
				}
			}
		}
		
		return bowl && seedCount == 3;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		return this.getRecipeOutput();
	}

	@Override
	public int getRecipeSize()
	{
		return 4;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return new ItemStack(_Blowpipe.grainMix);
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
