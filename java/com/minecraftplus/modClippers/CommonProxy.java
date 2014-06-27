package com.minecraftplus.modClippers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

public class CommonProxy implements ICommonProxy
{
	@Override
	public void register(Registry.RenderMode par1Registry)
	{

	}

	@Override
	public void register(Registry.CustomEntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.EntityMode par1Registry)
	{
		par1Registry.addEntity(EntityChickenNaked.class, "naked_chicken");
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addShapedRecipe(new ItemStack(MCP_Clippers.clippers, 1), new Object[] {
			" #", "# ",
			Character.valueOf('#'), new ItemStack(Items.gold_ingot)});
		par1Registry.addShapedRecipe(new ItemStack(MCP_Clippers.clippers, 1), new Object[] {
			"# ", " #",
			Character.valueOf('#'), new ItemStack(Items.gold_ingot)});
	}
}
