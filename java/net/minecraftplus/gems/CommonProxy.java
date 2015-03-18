package net.minecraftplus.gems;

import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapedRecipe(new ItemStack(ModGems.rubyBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), ModGems.ruby});
		this.addShapedRecipe(new ItemStack(ModGems.sapphireBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), ModGems.sapphire});
		this.addShapedRecipe(new ItemStack(ModGems.amethystBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), ModGems.amethyst});

		this.addShapelessRecipe(new ItemStack(ModGems.ruby, 9), ModGems.rubyBlock);
		this.addShapelessRecipe(new ItemStack(ModGems.sapphire, 9), ModGems.sapphireBlock);
		this.addShapelessRecipe(new ItemStack(ModGems.amethyst, 9), ModGems.amethystBlock);
	}
}
