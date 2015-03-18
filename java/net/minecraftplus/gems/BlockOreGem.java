package net.minecraftplus.gems;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public abstract class BlockOreGem extends BlockOre
{
	public BlockOreGem()
	{
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypePiston);
	}

	@Override
	public abstract Item getItemDropped(int par1, Random par2Random, int par3);

	private Random rand = new Random();
	@Override
	public int getExpDrop(IBlockAccess par1IBlockAccess, int par2, int par3)
	{
		if (this.getItemDropped(par2, this.rand, par3) != Item.getItemFromBlock(this))
		{
			return MathHelper.getRandomIntegerInRange(this.rand, 2, 5);
		}

		return 0;
	}
}
