package com.minecraftplus.modSilver;

import net.minecraft.item.ItemStack;

import com.minecraftplus._base.IProxy;
import com.minecraftplus._base.registry.ModRegistry;

public class CommonProxy implements IProxy
{
	@Override
	public void register()
	{
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Silver.silverBlock, 1), new Object[] {
			"###", "###", "###",
			Character.valueOf('#'), MCP_Silver.silverIngot});
		ModRegistry.addShapedRecipe(new ItemStack(MCP_Silver.silverIngot, 1), new Object[] {
			"   ", "###", "###",
			Character.valueOf('#'), MCP_Silver.silverNugget});
		ModRegistry.addShapelessRecipe(new ItemStack(MCP_Silver.silverIngot, 9), new Object[] {
			MCP_Silver.silverBlock
		});
		ModRegistry.addSmelting(MCP_Silver.silverOre, new ItemStack (MCP_Silver.silverIngot), 1);
	};
}

