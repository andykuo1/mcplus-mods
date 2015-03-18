package net.minecraftplus.blowpipe;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.ENTITIES().add(EntitySeeds.class, "Seeds");
		MCP.FUELS().add(ModBlowpipe.blowpipe, 100);

		this.addShapedRecipe(new ItemStack(ModBlowpipe.blowpipe, 1),
				"#Y", "YX",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.gunpowder,
				Character.valueOf('Y'), Items.paper);

		this.addShapedRecipe(new ItemStack(ModBlowpipe.blowpipe, 1),
				"XY", "Y#",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Items.gunpowder,
				Character.valueOf('Y'), Items.paper);
	}
}
