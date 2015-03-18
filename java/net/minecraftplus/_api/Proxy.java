package net.minecraftplus._api;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.registry.GameRegistry;

public abstract class Proxy
{
	public abstract void register();

	protected final IRecipe addRecipe(IRecipe parRecipe)
	{
		GameRegistry.addRecipe(parRecipe);
		return parRecipe;
	}

	protected final IRecipe addShapedRecipe(ItemStack parOutput, Object...parInputs)
	{
		return GameRegistry.addShapedRecipe(parOutput, parInputs);
	}

	protected final IRecipe addShapelessRecipe(ItemStack parOutput, Object...parInputs)
	{
		//GameRegistry.addShapelessRecipe(par1ItemStack, par2Object);

		ArrayList arraylist = new ArrayList();
		Object[] aobject = parInputs;
		int i = parInputs.length;

		for (int j = 0; j < i; ++j)
		{
			Object object1 = aobject[j];

			if (object1 instanceof ItemStack)
			{
				arraylist.add(((ItemStack)object1).copy());
			}
			else if (object1 instanceof Item)
			{
				arraylist.add(new ItemStack((Item)object1));
			}
			else
			{
				if (!(object1 instanceof Block))
				{
					throw new RuntimeException("Invalid shapeless recipy!");
				}

				arraylist.add(new ItemStack((Block)object1));
			}
		}

		IRecipe recipe = new ShapelessRecipes(parOutput, arraylist);
		CraftingManager.getInstance().getRecipeList().add(recipe);
		return recipe;
	}
	
	//TODO: Find somewhere to put this!
	protected final void addEventHandler(EventBus parEventBus, Object parObject)
	{
		parEventBus.register(parObject);
	}
}
