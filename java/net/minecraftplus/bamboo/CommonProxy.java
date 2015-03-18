package net.minecraftplus.bamboo;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapedRecipe(new ItemStack(ModBamboo.bamboo, 1), "###", "###", "###", Character.valueOf('#'), Items.reeds);
		this.addShapedRecipe(new ItemStack(Items.reeds, 9), "#", Character.valueOf('#'), ModBamboo.bamboo);
	}
}
