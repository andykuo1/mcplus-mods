package net.minecraftplus.loom;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus.wheel.ModWheel;

public class CommonProxy extends Proxy
{
	@Override
	public void register()
	{
		MCP.TILES().add(TileEntityLoom.class, "wooden_loom");

		this.addShapedRecipe(new ItemStack(ModLoom.loom, 1, 0), new Object[] {
			"#X ","###",
			Character.valueOf('#'), Blocks.planks,
			Character.valueOf('X'), ModWheel.wheel});
		this.addShapedRecipe(new ItemStack(ModLoom.loom, 1, 0), new Object[] {
			" X#","###",
			Character.valueOf('#'), Blocks.planks,
			Character.valueOf('X'), ModWheel.wheel});
	}
}
