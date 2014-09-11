package com.minecraftplus.modMortar;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addTileEntity(TileEntityMortar.class, "mortar");

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Mortar.mortar, 1, 0), new Object[] {
			"###","#X#", "###",
			Character.valueOf('#'), Blocks.cobblestone,
			Character.valueOf('X'), Items.bowl});
	}
}
