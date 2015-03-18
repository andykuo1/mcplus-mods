package net.minecraftplus.firepit;

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
		MCP.TILES().add(TileEntityFirePit.class, "fire_pit");

		this.addShapelessRecipe(new ItemStack(Items.stick, 2), Blocks.sapling);

		this.addShapedRecipe(new ItemStack(ModFirePit.firePit, 1, 0), new Object[] {
			"##","XX",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), Blocks.gravel});
	}
}
