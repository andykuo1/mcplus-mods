package com.minecraftplus._common.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class RenderBlock extends RenderBlocks
{
	private int renderID;
	private IBlockAccess postIBlockAccess;

	public RenderBlock(int par1)
	{
		this.renderID = par1;
	}

	public RenderBlock()
	{
		this(RenderingRegistry.getNextAvailableRenderId());
	}

	public void renderInInventory(Block par1Block, int par2Metadata) {}

	public abstract boolean renderInWorld(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, Block par5Block);

	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	public void setIBlockAccess(IBlockAccess par1IBlockAccess)
	{
		if (par1IBlockAccess == this.blockAccess) return;
		this.postIBlockAccess = this.blockAccess;
		this.blockAccess = par1IBlockAccess;
	}

	public void resetIBlockAccess()
	{
		this.blockAccess = this.postIBlockAccess;
	}

	public int getRenderID()
	{
		return this.renderID;
	}
}
