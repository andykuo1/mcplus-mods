package net.minecraftplus.cabbage;

import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapelessRecipe(new ItemStack(ModCabbage.cabbageSeeds, 2), ModCabbage.cabbage);
	}
}
