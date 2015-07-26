package net.minecraftplus._api.minecraft.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class BlockCropsBase extends BlockCrops
{
	@Override
	protected abstract Item getSeed();

	@Override
	protected abstract Item getCrop();

	@Override
	public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
	{
		return net.minecraftforge.common.EnumPlantType.Crop;
	}	
}
