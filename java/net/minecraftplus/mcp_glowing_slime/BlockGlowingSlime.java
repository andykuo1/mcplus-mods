package net.minecraftplus.mcp_glowing_slime;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Sounds;

//@BlockButton
public class BlockGlowingSlime extends Block
{
	public static boolean ENABLE_DECAY = true;

	//@BlockRedstoneTorch
	private final boolean isOn;

	public BlockGlowingSlime(boolean isOn)
	{
		super(Material.gourd);

		//@BlockRedstoneTorch
		this.isOn = isOn;

		//@BlockButton
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		if (this.isOn)
		{
			if (ENABLE_DECAY)
			{
				this.setTickRandomly(true);
			}
			
			this.setLightLevel(0.8375F);
		}
		else
		{
			this.setLightLevel(0.4F);
		}

		this.setHardness(0F);
	}

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
	{
		return worldIn.isSideSolid(pos.offset(side.getOpposite()), side, true);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		EnumFacing[] aenumfacing = EnumFacing.values();
		int i = aenumfacing.length;

		for (int j = 0; j < i; ++j)
		{
			EnumFacing enumfacing = aenumfacing[j];

			if (worldIn.isSideSolid(pos.offset(enumfacing), enumfacing.getOpposite(), true))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return _Glowing_Slime.glowingSlimeball;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
	{
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);

		if (!(entityIn instanceof EntityLiving)) return;

		if (entityIn instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entityIn;
			if (player.capabilities.isCreativeMode)
			{
				return;
			}

			player.inventory.addItemStackToInventory(new ItemStack(this.getItemDropped(state, null, 0)));
			worldIn.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, Sounds.RANDOM_POP, 0.05F, 0.6F);

		}
		else
		{
			this.dropBlockAsItem(worldIn, pos, state, 0);
		}

		worldIn.setBlockToAir(pos);
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		if (this.checkForDrop(worldIn, pos, state))
		{
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

			if (!worldIn.isSideSolid(pos.offset(enumfacing.getOpposite()), enumfacing, true))
			{
				this.dropBlockAsItem(worldIn, pos, state, 0);
				worldIn.setBlockToAir(pos);
			}
		}
	}

	private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!this.canPlaceBlockAt(worldIn, pos))
		{
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
	{
		this.updateBlockBounds(worldIn.getBlockState(pos));
	}

	private void updateBlockBounds(IBlockState state)
	{
		EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
		float f = 0.25F;
		float f1 = 0.25F;
		float f2 = (float)3 / 16.0F;
		float f3 = 0.125F;
		float f4 = 0.125F;

		float f5 = 0.275F;
		float f6 = 1 - f5;

		switch (BlockGlowingSlime.SwitchEnumFacing.FACING_LOOKUP[enumfacing.ordinal()])
		{
		case 1:
			this.setBlockBounds(0.0F, f5, f5, f2, f6, f6);
			break;
		case 2:
			this.setBlockBounds(1.0F - f2, f5, f5, 1.0F, f6, f6);
			break;
		case 3:
			this.setBlockBounds(f5, f5, 0.0F, f6, f6, f2);
			break;
		case 4:
			this.setBlockBounds(f5, f5, 1.0F - f2, f6, f6, 1.0F);
			break;
		case 5:
			this.setBlockBounds(f5, 0.0F, f5, f6, 0.0F + f2, f6);
			break;
		case 6:
			this.setBlockBounds(f5, 1.0F - f2, f5, f6, 1.0F, f6);
		}
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
	{
		if (!worldIn.isRemote)
		{
			if (this.isOn)
			{
				worldIn.setBlockState(pos, _Glowing_Slime.dimmingSlime.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
			}
		}
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		float f = 0.1875F;
		float f1 = 0.125F;
		float f2 = 0.125F;
		this.setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
	}


	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos)
	{
		return _Glowing_Slime.glowingSlimeball;
	}

	@Override
	public boolean isAssociatedBlock(Block other)
	{
		return other == _Glowing_Slime.glowingSlime || other == _Glowing_Slime.dimmingSlime;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing;

		switch (meta & 7)
		{
		case 0:
			enumfacing = EnumFacing.DOWN;
			break;
		case 1:
			enumfacing = EnumFacing.EAST;
			break;
		case 2:
			enumfacing = EnumFacing.WEST;
			break;
		case 3:
			enumfacing = EnumFacing.SOUTH;
			break;
		case 4:
			enumfacing = EnumFacing.NORTH;
			break;
		case 5:
		default:
			enumfacing = EnumFacing.UP;
		}

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i;

		switch (BlockGlowingSlime.SwitchEnumFacing.FACING_LOOKUP[((EnumFacing)state.getValue(FACING)).ordinal()])
		{
		case 1:
			i = 1;
			break;
		case 2:
			i = 2;
			break;
		case 3:
			i = 3;
			break;
		case 4:
			i = 4;
			break;
		case 5:
		default:
			i = 5;
			break;
		case 6:
			i = 0;
		}

		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] {FACING});
	}

	static final class SwitchEnumFacing
	{
		static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];

		static
		{
			try
			{
				FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 1;
			}
			catch (NoSuchFieldError var6)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 2;
			}
			catch (NoSuchFieldError var5)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 3;
			}
			catch (NoSuchFieldError var4)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 4;
			}
			catch (NoSuchFieldError var3)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.UP.ordinal()] = 5;
			}
			catch (NoSuchFieldError var2)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.DOWN.ordinal()] = 6;
			}
			catch (NoSuchFieldError var1)
			{
				;
			}
		}
	}
}
