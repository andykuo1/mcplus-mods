package net.minecraftplus.sickle;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapedRecipe(new ItemStack(ModSickle.sickle, 1), new Object[] {
			"X ", " X", "# ",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), Items.iron_ingot});
		this.addShapedRecipe(new ItemStack(ModSickle.sickle, 1), new Object[] {
			" X", "X ", " #",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), Items.iron_ingot});
	}
}
