package net.minecraftplus.mcp_saw;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Sounds;

public class BlockSaw extends Block
{
	//Compare To: @BlockDispenser
	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	protected BlockSaw()
	{
		super(Material.wood);

		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public int tickRate(World worldIn)
	{
		//Compare To: @BlockDispenser
		return 4;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		//Compare To: @BlockDispenser
		super.onBlockAdded(worldIn, pos, state);
		this.setDefaultDirection(worldIn, pos, state);
	}

	private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state)
	{
		//Compare To: @BlockDispenser
		if (!worldIn.isRemote)
		{
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
			boolean flag = worldIn.getBlockState(pos.north()).getBlock().isFullBlock();
			boolean flag1 = worldIn.getBlockState(pos.south()).getBlock().isFullBlock();

			//Inverted default facing direction if placed facing block
			if (enumfacing == EnumFacing.NORTH && !flag && flag1)
			{
				enumfacing = EnumFacing.SOUTH;
			}
			else if (enumfacing == EnumFacing.SOUTH && !flag1 && flag)
			{
				enumfacing = EnumFacing.NORTH;
			}
			else
			{
				boolean flag2 = worldIn.getBlockState(pos.west()).getBlock().isFullBlock();
				boolean flag3 = worldIn.getBlockState(pos.east()).getBlock().isFullBlock();

				if (enumfacing == EnumFacing.WEST && !flag2 && flag3)
				{
					enumfacing = EnumFacing.EAST;
				}
				else if (enumfacing == EnumFacing.EAST && !flag3 && flag2)
				{
					enumfacing = EnumFacing.WEST;
				}
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote) return true;

		BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
		EnumFacing facing = getFacing(this.getMetaFromState(state));
		BlockPos outpos = pos.offset(facing);
		IBlockState outblock = worldIn.getBlockState(outpos);

		if (outblock.getBlock() != Blocks.air)
		{
			if (outblock.getBlock() instanceof BlockLog && worldIn.rand.nextBoolean())
			{
				worldIn.playAuxSFX(2001, outpos, Block.getIdFromBlock(outblock.getBlock()));

				if (worldIn.rand.nextInt(7) == 0)
				{
					worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
				}
			}

			worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, Sounds.RANDOM_WOOD_CLICK, 0.8F, worldIn.rand.nextFloat() * 0.15F + 0.6F);

			return true;
		}
		else
		{
			ItemStack equipItem = playerIn.getCurrentEquippedItem();
			if (equipItem != null)
			{
				if (Block.getBlockFromItem(equipItem.getItem()) instanceof BlockLog)
				{
					if (side == facing)
					{
						//Return false to allow player to put wood log on block
						return false;
					}
				}

				if (!worldIn.isRemote)
				{
					worldIn.playAuxSFX(1001, pos, 0);
				}
			}

			return true;
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (worldIn.isRemote) return;

		BlockPos outpos = pos.offset(getFacing(this.getMetaFromState(state)));
		IBlockState outblock = worldIn.getBlockState(outpos);
		if (outblock.getBlock() instanceof BlockLog)
		{
			worldIn.playAuxSFX(2001, outpos, Block.getIdFromBlock(state.getBlock()));
			this.breakWood(worldIn, outpos, outblock, rand);
		}
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
		if (!flag) return;
		if (worldIn.isRemote) return;

		BlockPos outpos = pos.offset(getFacing(this.getMetaFromState(state)));
		IBlockState outblock = worldIn.getBlockState(outpos);

		if (outblock.getBlock() == Blocks.air)
		{
			worldIn.playAuxSFX(1001, pos, 0);
			return;
		}
		else
		{
			if (outblock.getBlock() instanceof BlockLog && worldIn.rand.nextBoolean())
			{
				worldIn.playAuxSFX(2001, outpos, Block.getIdFromBlock(outblock.getBlock()));

				if (worldIn.rand.nextInt(7) == 0)
				{
					worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
				}
			}

			worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, Sounds.RANDOM_WOOD_CLICK, 0.8F, worldIn.rand.nextFloat() * 0.15F + 0.6F);
		}
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		//Compare To: @BlockDispenser
		return this.getDefaultState().withProperty(FACING, BlockPistonBase.getFacingFromEntity(worldIn, pos, placer));
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		//Compare To: @BlockDispenser
		worldIn.setBlockState(pos, state.withProperty(FACING, BlockPistonBase.getFacingFromEntity(worldIn, pos, placer)), 2);
	}

	/**Get the facing of a dispenser with the given metadata*/
	public static EnumFacing getFacing(int meta)
	{
		//Compare To: @BlockDispenser
		return EnumFacing.getFront(meta & 7);
	}

	@Override
	public int getRenderType()
	{
		//Compare To: @BlockDispenser
		return 3;
	}

	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state)
	{
		//Compare To: @BlockDispenser
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		//Compare To: @BlockDispenser
		return this.getDefaultState().withProperty(FACING, getFacing(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		//Compare To: @BlockDispenser
		byte b0 = 0;
		int i = b0 | ((EnumFacing)state.getValue(FACING)).getIndex();

		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		//Compare To: @BlockDispenser
		return new BlockState(this, new IProperty[] {FACING});
	}

	private void breakWood(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		List<ItemStack> drops = state.getBlock().getDrops(worldIn, pos, state, 0);

		for(ItemStack dropstack : drops)
		{
			if (dropstack != null)
			{
				ItemStack result = null;
				Block block = Block.getBlockFromItem(dropstack.getItem());
				if (block != null)
				{
					ItemStack itemstack = WoodRegistry.INSTANCE.get(block.getStateFromMeta(dropstack.getItemDamage()));
					if (itemstack != null)
					{
						result = ItemStack.copyItemStack(itemstack);
						result.stackSize = rand.nextBoolean() ? 4 : rand.nextInt(3) + 1;
					}
				}

				if (result == null)
				{
					result = ItemStack.copyItemStack(dropstack);
				}

				worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, result));
			}
		}

		worldIn.setBlockToAir(pos);
	}
}
