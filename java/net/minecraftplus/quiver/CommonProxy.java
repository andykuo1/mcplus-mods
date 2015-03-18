package net.minecraftplus.quiver;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._common.RecipesDyeable;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapedRecipe(new ItemStack(ModQuiver.quiver, 1), new Object[] {
			"YYX", "##X", "##X",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Items.string,
			Character.valueOf('Y'), Items.feather});

		this.addShapedRecipe(new ItemStack(ModQuiver.quiver, 1), new Object[] {
			"XYY", "X##", "X##",
			Character.valueOf('#'), Items.leather,
			Character.valueOf('X'), Items.string,
			Character.valueOf('Y'), Items.feather});

		this.addRecipe(new RecipesDyeable(ModQuiver.quiver));
		this.addRecipe(new RecipesQuiverExpanded());
	}
}
