package com.minecraftplus.modCart;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;
import com.minecraftplus.modWheel.MCP_Wheel;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addCustomEntity(EntityCart.class, "wooden_cart", MCP_Cart.INSTANCE, 256, 15, false);

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Cart.cart),
				"X#Y", "XZY",
				Character.valueOf('#'), Items.boat,
				Character.valueOf('X'), MCP_Wheel.wheel,
				Character.valueOf('Y'), Items.stick,
				Character.valueOf('Z'), Items.slime_ball);
	}
}
