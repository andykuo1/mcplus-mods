package net.minecraftplus.beetroot;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), ModBeetroot.beetroot);
		this.addShapelessRecipe(new ItemStack(ModBeetroot.beetrootSoup), ModBeetroot.beetroot, ModBeetroot.beetroot, ModBeetroot.beetroot, ModBeetroot.beetroot, Items.bowl);
	}
}
