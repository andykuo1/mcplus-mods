package com.minecraftplus.modGlowingSkull;

import net.minecraft.init.Blocks;

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
		par1Registry.addTileEntity(TileEntityGlowingSkull.class, "glowing_skulls");
		par1Registry.addTileEntity(TileEntityRedstoneGlowingSkull.class, "glowing_redstone_skulls");
	}

	@Override
	public void register(Registry.RecipeMode par1Registry)
	{
		par1Registry.addRecipe(new RecipesGlowingSkull(Blocks.torch, MCP_GlowingSkull.glowingSkull));
		par1Registry.addRecipe(new RecipesGlowingSkull(Blocks.redstone_torch, MCP_GlowingSkull.redstoneGlowingSkull));
	}
}
