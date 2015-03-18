package net.minecraftplus.saw;

import net.minecraft.block.Block;

public class WoodPlank
{
	private final Block block;
	private final int metadata;

	public WoodPlank(Block par1Block, int par2)
	{
		this.block = par1Block;
		this.metadata = par2;
	}

	public Block getBlock()
	{
		return this.block;
	}

	public int getBlockMetadata()
	{
		return this.metadata;
	}

	@Override
	public int hashCode()
	{
		return block.hashCode();
	}

	@Override
	public boolean equals(Object par1Object)
	{
		if (par1Object instanceof WoodPlank)
		{
			WoodPlank woodplank = (WoodPlank) par1Object;
			return this.block == woodplank.block && this.metadata == woodplank.metadata;
		}

		return false;
	}
}