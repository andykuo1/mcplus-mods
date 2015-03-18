package net.minecraftplus.woodenbucket;

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
		MCP.FUELS().add(ModWoodenBucket.woodenBucketEmpty, 200);

		this.addShapedRecipe(new ItemStack(ModWoodenBucket.woodenBucketEmpty, 1), new Object[] {
			"# #", " X ",
			Character.valueOf('#'), Blocks.planks,
			Character.valueOf('X'), Items.bowl});
	}
}
