package net.minecraftplus.cocoa;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapedRecipe(new ItemStack(ModCocoa.cocoaBar, 1), new Object[] {
			"###", "###",
			Character.valueOf('#'), new ItemStack(Items.dye, 1, 3),});
		this.addShapelessRecipe(new ItemStack(Items.dye, 6, 3), new Object[] {ModCocoa.cocoaBar});
		this.addShapelessRecipe(new ItemStack(ModCocoa.cocoaPie), new Object[] {ModCocoa.cocoaBar, ModCocoa.cocoaBar, Items.egg, Items.sugar});
	}
}
