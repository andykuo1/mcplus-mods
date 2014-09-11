package com.minecraftplus.modFirePit;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	public static boolean CUSTOM_RECIPE = false;

	@Override
	public void register()
	{
		ModRegistry.addTileEntity(TileEntityFirePit.class, "fire_pit");

		ModRegistry.addShapelessRecipe(new ItemStack(Items.stick, 2), Blocks.sapling);
		if (!CUSTOM_RECIPE)
		{
			ModRegistry.addShapedRecipe(new ItemStack(MCP_FirePit.firePit, 1, 0), new Object[] {
				"##","XX",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Blocks.gravel});
		}
	}
}
