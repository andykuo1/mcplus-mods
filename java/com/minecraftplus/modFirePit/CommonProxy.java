package com.minecraftplus.modFirePit;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.minecraftplus._base.ICommonProxy;
import com.minecraftplus._base.registry.Registry;

public class CommonProxy implements ICommonProxy
{
	public static boolean CUSTOM_RECIPE = false;

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
		if (!CUSTOM_RECIPE)
		{
			par1Registry.addShapedRecipe(new ItemStack(MCP_FirePit.firePit, 1, 0), new Object[] {
				"##","XX",
				Character.valueOf('#'), Items.stick,
				Character.valueOf('X'), Blocks.gravel});
		}
	}
}
