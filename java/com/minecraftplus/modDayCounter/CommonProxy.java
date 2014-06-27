package com.minecraftplus.modDayCounter;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

public class CommonProxy implements ICommonProxy
{
	@Override
	public void register(Registry.RenderMode par1Registry)
	{

	}

	@Override
	public void register(Registry.CustomEntityMode par1Registry)
	{
		par1Registry.addCustomEntity(EntityDayCounter.class, "day_counter", MCP_DayCounter.INSTANCE, 160, Integer.MAX_VALUE, false);
	}

	@Override
	public void register(Registry.EntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addShapedRecipe(new ItemStack(MCP_DayCounter.dayCounter, 1), new Object[] {
			"XXX", "YYY", "YYY",
			Character.valueOf('X'), Blocks.cobblestone,
			Character.valueOf('Y'), Items.paper});
	}
}
