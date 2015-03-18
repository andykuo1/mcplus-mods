package net.minecraftplus.cart;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus.wheel.ModWheel;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.ENTITIES().add(EntityCart.class, "WoodenCart", 256, 15, false);

		this.addShapedRecipe(new ItemStack(ModCart.cart),
				"X#Y", "XZY",
				Character.valueOf('#'), Items.boat,
				Character.valueOf('X'), ModWheel.wheel,
				Character.valueOf('Y'), Items.stick,
				Character.valueOf('Z'), Items.slime_ball);

	}
}
