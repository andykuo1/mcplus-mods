package net.minecraftplus._api.handler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public abstract class RenderSimpleBlockHandler implements ISimpleBlockRenderingHandler
{
	private int renderType = -1;

	@Override
	public abstract void renderInventoryBlock(Block parBlock, int parMetadata, int parRenderID, RenderBlocks parRenderBlock);

	@Override
	public abstract boolean renderWorldBlock(IBlockAccess parBlockAccess, int parBlockX, int parBlockY, int parBlockZ, Block parBlock, int parRenderID, RenderBlocks parRenderBlock);

	@Override
	public abstract boolean shouldRender3DInInventory(int parRenderID);

	@Override
	public int getRenderId()
	{
		return this.renderType;
	}

	public void setRenderType(int parRenderID)
	{
		this.renderType = parRenderID;
	}
}
