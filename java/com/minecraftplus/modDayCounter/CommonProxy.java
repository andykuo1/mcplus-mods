package com.minecraftplus.modDayCounter;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addCustomEntity(EntityDayCounter.class, "day_counter", MCP_DayCounter.INSTANCE, 160, Integer.MAX_VALUE, false);

		ModRegistry.addShapedRecipe(new ItemStack(MCP_DayCounter.dayCounter, 1), new Object[] {
			"XXX", "YYY", "YYY",
			Character.valueOf('X'), Blocks.cobblestone,
			Character.valueOf('Y'), Items.paper});
	}
}
