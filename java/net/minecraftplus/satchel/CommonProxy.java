package net.minecraftplus.satchel;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._common.RecipesDyeable;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapedRecipe(new ItemStack(ModSatchel.satchel, 1), new Object[] {
			"###", "YXY", "###",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Blocks.chest,
			Character.valueOf('Y'), Items.string});

		this.addRecipe(new RecipesDyeable(ModSatchel.satchel));
		this.addRecipe(new RecipesDyeable(ModSatchel.enderSatchel));
	}
}
