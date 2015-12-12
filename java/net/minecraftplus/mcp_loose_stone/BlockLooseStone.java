package net.minecraftplus.mcp_loose_stone;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockLooseStone extends BlockFalling
{
	public BlockLooseStone()
	{
		this.setTickRandomly(false);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Blocks.cobblestone);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
	{
		if (!this.isSupported(worldIn, pos))
		{
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
		}
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		if (!this.isSupported(worldIn, pos))
		{
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
		if (!this.isSupported(worldIn, pos))
		{
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
		}
	}

	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
	{
		if (!this.isSupported(worldIn, pos))
		{
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
		}
	}

	private static void loosenNeighbors(World worldIn, BlockPos pos)
	{
		int radius = worldIn.rand.nextInt(4) + 1;
		for(int i = -radius; i <= radius; i++)
		{
			for(int j = -radius; j <= radius; j++)
			{
				for(int k = -radius; k <= radius; k++)
				{
					BlockPos offset = pos.west(i).up(j).north(k);
					if (worldIn.getBlockState(offset).getBlock() == Blocks.stone)
					{
						worldIn.setBlockState(offset, _Loose_Stone.looseStone.getDefaultState());
						worldIn.scheduleUpdate(offset, _Loose_Stone.looseStone, _Loose_Stone.looseStone.tickRate(worldIn));
					}
				}
			}
		}
	}

	private static boolean isSupported(World worldIn, BlockPos pos)
	{
		if (isSupportBlock(worldIn.getBlockState(pos.down()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.down().north()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.down().south()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.down().west()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.down().east()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.down().north().west()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.down().north().east()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.down().south().west()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.down().south().east()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.north()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.south()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.west()))) return true;
		if (isSupportBlock(worldIn.getBlockState(pos.east()))) return true;

		return false;
	}

	private static boolean isSupportBlock(IBlockState parBlockState)
	{
		Block block = parBlockState.getBlock();
		return block instanceof BlockFence || block instanceof BlockFenceGate || block instanceof BlockWall;
	}
}
