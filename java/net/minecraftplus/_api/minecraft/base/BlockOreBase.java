package net.minecraftplus._api.minecraft.base;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOreBase extends BlockOre
{
	private final Item dropItem;
	private final int dropAmount;
	private final int dropAmountRand;

	private int expMin = 2;
	private int expMax = 5;

	public BlockOreBase(Item parItem)
	{
		this(parItem, 1, 0);
	}

	public BlockOreBase(Item parItem, int parAmount, int parAmountRandom)
	{
		this.dropItem = parItem;
		this.dropAmount = parAmount;
		this.dropAmountRand = parAmountRandom;
	}

	public Block setDropExp(int parMinExp, int parMaxExp)
	{
		this.expMin = parMinExp;
		this.expMax = parMaxExp;
		return this;
	}

	@Override
	public Item getItemDropped(IBlockState parState, Random parRand, int parFortune)
	{
		return this.dropItem;
	}

	@Override
	public int quantityDropped(Random parRandom)
	{
		return this.dropAmount + parRandom.nextInt(this.dropAmountRand);
	}

	@Override
	public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune)
	{
		//Compare to: @BlockOre
		IBlockState state = world.getBlockState(pos);
		Random rand = world instanceof World ? ((World)world).rand : new Random();
		if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
		{
			return MathHelper.getRandomIntegerInRange(rand, this.expMin, this.expMax);
		}
		return 0;
	}
}
