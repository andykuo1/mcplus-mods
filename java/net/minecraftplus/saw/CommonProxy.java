package net.minecraftplus.saw;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapedRecipe(new ItemStack(ModSaw.saw, 1), new Object[] {
			"XX", "##",
			Character.valueOf('#'), Blocks.log,
			Character.valueOf('X'), Items.flint});

		this.addShapedRecipe(new ItemStack(ModSaw.saw, 1), new Object[] {
			"XX", "##",
			Character.valueOf('#'), Blocks.log2,
			Character.valueOf('X'), Items.flint});
	}
}
