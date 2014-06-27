package com.minecraftplus._base.handler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import com.minecraftplus._common.render.RenderBlock;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RenderBlockHandler implements ISimpleBlockRenderingHandler
{
	private List<RenderBlock> renderBlocks = new ArrayList<RenderBlock>();

	public boolean add(RenderBlock par1RenderBlock)
	{
		RenderingRegistry.registerBlockHandler(par1RenderBlock.getRenderID(), this);
		return this.renderBlocks.add(par1RenderBlock);
	}

	@Override
	public void renderInventoryBlock(Block par1Block, int par2Metadata, int par3RenderID, RenderBlocks par4RenderBlocks)
	{
		for(RenderBlock renderBlock : this.renderBlocks)
		{
			if (par3RenderID == renderBlock.getRenderID())
			{
				renderBlock.renderInInventory(par1Block, par2Metadata);
			}
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, Block par5Block, int par6RenderID, RenderBlocks par7RenderBlocks)
	{
		if (par5Block == null) return false;

		boolean flag = false;
		for(RenderBlock renderBlock : this.renderBlocks)
		{
			if (par6RenderID == renderBlock.getRenderID())
			{
				renderBlock.setIBlockAccess(par1IBlockAccess);
				flag |= renderBlock.renderInWorld(par1IBlockAccess, par2, par3, par4, par5Block);
				renderBlock.resetIBlockAccess();
			}
		}

		return flag;
	}

	@Override
	public boolean shouldRender3DInInventory(int par1RenderID)
	{
		for(RenderBlock renderBlock : this.renderBlocks)
		{
			if (par1RenderID == renderBlock.getRenderID())
			{
				return renderBlock.shouldRender3DInInventory();
			}
		}

		return false;
	}

	@Override
	public int getRenderId()
	{
		return -1;
	}
}
