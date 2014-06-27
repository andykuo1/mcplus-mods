package com.minecraftplus.modCart;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus.modWheel.MCP_Wheel;

public class CommonProxy implements ICommonProxy
{
	@Override
	public void register(Registry.RenderMode par1Registry)
	{

	}

	@Override
	public void register(Registry.CustomEntityMode par1Registry)
	{
		par1Registry.addCustomEntity(EntityCart.class, "wooden_cart", MCP_Cart.INSTANCE, 256, 15, false);
	}

	@Override
	public void register(Registry.EntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addShapedRecipe(new ItemStack(MCP_Cart.cart), new Object[] {
			"X#Y", "XZY",
			Character.valueOf('#'), Items.boat,
			Character.valueOf('X'), MCP_Wheel.wheel,
			Character.valueOf('Y'), Items.stick,
			Character.valueOf('Z'), Items.slime_ball});
	}
}
