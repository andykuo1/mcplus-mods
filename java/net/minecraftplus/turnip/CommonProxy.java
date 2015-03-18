package net.minecraftplus.turnip;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapelessRecipe(new ItemStack(ModTurnip.turnipSoup), new Object[] {ModTurnip.turnip, ModTurnip.turnip, ModTurnip.turnip, Items.bowl});
	}
}
