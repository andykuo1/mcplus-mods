package net.minecraftplus.mcp_bamboo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockBamboo extends BlockRotatedPillar
{
	public static final PropertyEnum LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);

	public BlockBamboo()
	{
		super(Material.grass);

		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y));
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing.Axis axis = EnumFacing.Axis.Y;
		int j = meta & 12;

		if (j == 4)
		{
			axis = EnumFacing.Axis.X;
		}
		else if (j == 8)
		{
			axis = EnumFacing.Axis.Z;
		}

		return this.getDefaultState().withProperty(AXIS, axis);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		EnumFacing.Axis axis = (EnumFacing.Axis)state.getValue(AXIS);

		if (axis == EnumFacing.Axis.X)
		{
			i |= 4;
		}
		else if (axis == EnumFacing.Axis.Z)
		{
			i |= 8;
		}

		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {AXIS});
	}

	@Override
	protected ItemStack createStackedBlock(IBlockState state)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, 0);
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS, facing.getAxis());
	}
}