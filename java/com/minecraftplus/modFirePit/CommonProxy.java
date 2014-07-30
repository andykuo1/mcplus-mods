package com.minecraftplus.modFirePit;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;
import com.minecraftplus.modHandDigging.MCP_HandDigging;

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
		par1Registry.addTileEntity(TileEntityFirePit.class, "fire_pit");
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addShapedRecipe(new ItemStack(MCP_FirePit.firePit, 1, 0), new Object[] {
			"##","XX",
			Character.valueOf('#'), Items.stick,
			Character.valueOf('X'), MCP_HandDigging.rock});
	}
}
