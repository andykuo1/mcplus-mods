package net.minecraftplus.trapped;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapedRecipe(new ItemStack(ModTrapped.ladderTrapped, 1), new Object[] {
			"#X",
			Character.valueOf('#'), Blocks.ladder,
			Character.valueOf('X'), Blocks.tripwire_hook});
		this.addShapedRecipe(new ItemStack(ModTrapped.ladderTrapped, 1), new Object[] {
			"X#",
			Character.valueOf('#'), Blocks.ladder,
			Character.valueOf('X'), Blocks.tripwire_hook});
	}
}
