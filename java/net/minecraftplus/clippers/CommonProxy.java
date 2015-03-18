package net.minecraftplus.clippers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.ENTITIES().add(EntityChickenNaked.class, "NakedChicken");

		this.addShapedRecipe(new ItemStack(ModClippers.clippers, 1),
				" #", "# ",
				Character.valueOf('#'), new ItemStack(Items.gold_ingot));
		this.addShapedRecipe(new ItemStack(ModClippers.clippers, 1),
				"# ", " #",
				Character.valueOf('#'), new ItemStack(Items.gold_ingot));
	}
}
