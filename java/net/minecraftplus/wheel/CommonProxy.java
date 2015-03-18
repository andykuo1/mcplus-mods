package net.minecraftplus.wheel;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		this.addShapedRecipe(new ItemStack(ModWheel.wheel, 1, 0), new Object[] {
			"###","#X#","###",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), Items.slime_ball});
	}
}
