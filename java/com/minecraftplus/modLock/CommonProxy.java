package com.minecraftplus.modLock;

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
		ModRegistry.addCustomEntity(EntityLock.class, "iron_lock", MCP_Lock.INSTANCE, 160, Integer.MAX_VALUE, false);

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Lock.ironLock, 1), new Object[] {
			"###", "# #", "XXX",
			Character.valueOf('#'), Items.iron_ingot,
			Character.valueOf('X'), Blocks.iron_block});

		ModRegistry.addShapedRecipe(new ItemStack(MCP_Lock.lockpick, 1), new Object[] {
			"#", "#",
			Character.valueOf('#'), Items.iron_ingot});
	}
}
