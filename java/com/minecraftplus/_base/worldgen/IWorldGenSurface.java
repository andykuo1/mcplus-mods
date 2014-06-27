package com.minecraftplus._base.worldgen;

import java.util.Random;

import net.minecraft.world.World;

public interface IWorldGenSurface
{
	public void generateSurface(World par1World, Random par2Random, int par3, int par4);
}
