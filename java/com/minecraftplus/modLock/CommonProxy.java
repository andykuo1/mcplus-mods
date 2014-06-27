package com.minecraftplus.modLock;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements ICommonProxy
{
	@Override
	public void register(Registry.RenderMode par1Registry)
	{

	}

	@Override
	public void register(Registry.CustomEntityMode par1Registry)
	{
		par1Registry.addCustomEntity(EntityLock.class, "iron_lock", MCP_Lock.INSTANCE, 160, Integer.MAX_VALUE, false);
	}

	@Override
	public void register(Registry.EntityMode par1Registry)
	{

	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addShapedRecipe(new ItemStack(MCP_Lock.ironLock, 1), new Object[] {
			"###", "# #", "XXX",
			Character.valueOf('#'), Items.iron_ingot,
			Character.valueOf('X'), Blocks.iron_block});

		par1Registry.addShapedRecipe(new ItemStack(MCP_Lock.lockpick, 1), new Object[] {
			"#", "#",
			Character.valueOf('#'), Items.iron_ingot});
	}
}
