package net.minecraftplus.soulextractor;

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
		MCP.TILES().add(TileEntitySoulExtractor.class, "soul_extractor");

		this.addShapedRecipe(new ItemStack(ModSoulExtractor.soulExtractor, 1), new Object[] {
			" X ", "Y#Y", "###",
			Character.valueOf('#'), Blocks.obsidian,
			Character.valueOf('X'), Items.ender_pearl,
			Character.valueOf('Y'), Items.gold_ingot});
	}
}
