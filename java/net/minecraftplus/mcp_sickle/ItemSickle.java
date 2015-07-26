package net.minecraftplus.mcp_sickle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemSickle extends Item
{
	public ItemSickle()
	{
		this.setCreativeTab(CreativeTabs.tabTools);

		this.setMaxStackSize(1);
		this.setMaxDamage(364);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack parItemStack, World parWorld, Block parBlock, BlockPos parBlockPos, EntityLivingBase parEntity)
	{
		for(int i = -1; i <= 1; i++)
		{
			for(int j = -1; j <= 1; j++)
			{
				if (i == 0 && j == 0) continue;
				BlockPos pos = new BlockPos(parBlockPos.getX() + i, parBlockPos.getY(), parBlockPos.getZ() + j);
				IBlockState block = parWorld.getBlockState(pos);
				if (this.isCrop(block.getBlock()) && EventHandlerSickle.isFullGrownCrop(parWorld, pos, block))
				{
					block.getBlock().dropBlockAsItem(parWorld, pos, block, 1);
					parWorld.setBlockToAir(pos);
					parItemStack.damageItem(1, parEntity);
				}
			}
		}

		parItemStack.damageItem(1, parEntity);
		return super.onBlockDestroyed(parItemStack, parWorld, parBlock, parBlockPos, parEntity);
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, IBlockState state)
	{
		return this.isCrop(state.getBlock()) ? super.getDigSpeed(itemstack, state) : 0F;
	}

	public boolean isCrop(Block block)
	{
		return block instanceof BlockCrops || block.getMaterial() == Material.gourd || block.getMaterial() == Material.plants || block.getMaterial() == Material.vine;
	}
}
