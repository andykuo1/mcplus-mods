package net.minecraftplus.mcp_fossil;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockFossilStone extends Block
{
	public BlockFossilStone()
	{
		super(Material.rock);

		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		//Compare To: @BlockClay
		return Item.getItemFromBlock(Blocks.cobblestone);
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		//Compare To: @BlockOre
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
	}

	@Override
	public java.util.List<net.minecraft.item.ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		//Compare To: @BlockOre
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		java.util.Random rand = world instanceof World ? ((World)world).rand : new java.util.Random();
		ret.add(new ItemStack(Items.bone, rand.nextInt(4)));
		return ret;
	}
}
