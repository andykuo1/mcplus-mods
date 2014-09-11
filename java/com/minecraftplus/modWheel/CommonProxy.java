package com.minecraftplus.modWheel;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Wheel.wheel, 1, 0), new Object[] {
			"###","#X#","###",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), Items.slime_ball});
	}
}
