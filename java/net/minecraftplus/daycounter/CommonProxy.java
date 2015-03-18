package net.minecraftplus.daycounter;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.ENTITIES().add(EntityDayCounter.class, "DayCounter", 160, Integer.MAX_VALUE, false);

		this.addShapedRecipe(new ItemStack(ModDayCounter.dayCounter, 1), new Object[] {
			"XXX", "YYY", "YYY",
			Character.valueOf('X'), Blocks.cobblestone,
			Character.valueOf('Y'), Items.paper});
	}
}
