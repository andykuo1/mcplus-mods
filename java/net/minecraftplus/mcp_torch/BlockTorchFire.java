package net.minecraftplus.mcp_torch;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftplus._api.dictionary.Sounds;

public class BlockTorchFire extends BlockTorch
{
	private final boolean isOn;

	public BlockTorchFire(boolean isOn)
	{
		this.isOn = isOn;

		if (!this.isOn)
		{
			this.setLightLevel(0F);
		}
		else
		{
			this.setLightLevel(0.9375F);
			this.setTickRandomly(true);
		}
		this.setCreativeTab((CreativeTabs)null);

		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeWood);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		ItemStack playerStack = playerIn.getCurrentEquippedItem();
		if (playerStack != null && playerStack.getItem() == Items.coal)
		{
			if (state.getBlock() == _Torch.unlitTorch)
			{
				if (!worldIn.isRemote)
				{
					worldIn.setBlockState(pos, _Torch.litTorch.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
					worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), Sounds.RANDOM_FIZZ, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
					worldIn.scheduleUpdate(pos, worldIn.getBlockState(pos).getBlock(), 160);
				}

				if (!playerIn.capabilities.isCreativeMode)
				{
					playerStack.stackSize--;
				}
			}
		}
		else
		{
			if (!worldIn.isRemote)
			{
				worldIn.setBlockState(pos, _Torch.unlitTorch.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
				worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), Sounds.RANDOM_FIZZ, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
				worldIn.scheduleUpdate(pos, worldIn.getBlockState(pos).getBlock(), 160);
			}

			for (int i = 0; i < 5; ++i)
			{
				double d0 = (double)pos.getX() + worldIn.rand.nextDouble() * 0.6D + 0.2D;
				double d1 = (double)pos.getY() + worldIn.rand.nextDouble() * 0.6D + 0.2D;
				double d2 = (double)pos.getZ() + worldIn.rand.nextDouble() * 0.6D + 0.2D;
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}

		return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.randomTick(worldIn, pos, state, rand);

		if (this.isOn)
		{
			if (!worldIn.isRemote && (rand.nextInt(400) == 0 || worldIn.isRaining()))
			{
				worldIn.setBlockState(pos, _Torch.unlitTorch.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
				worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), Sounds.RANDOM_FIZZ, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
				worldIn.scheduleUpdate(pos, worldIn.getBlockState(pos).getBlock(), 160);
			}

			for (int i = 0; i < 5; ++i)
			{
				double d0 = (double)pos.getX() + rand.nextDouble() * 0.6D + 0.2D;
				double d1 = (double)pos.getY() + rand.nextDouble() * 0.6D + 0.2D;
				double d2 = (double)pos.getZ() + rand.nextDouble() * 0.6D + 0.2D;
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!this.isOn) return;
		super.randomDisplayTick(worldIn, pos, state, rand);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return this.isOn ? Item.getItemFromBlock(Blocks.torch) : Items.stick;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getItem(World worldIn, BlockPos pos)
	{
		return Item.getItemFromBlock(Blocks.torch);
	}

	@Override
	public boolean isAssociatedBlock(Block other)
	{
		return other == _Torch.unlitTorch || other == _Torch.litTorch || other == Blocks.torch;
	}
}
