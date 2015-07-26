package net.minecraftplus.mcp_quartz;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftplus._api.dictionary.Dimensions;

public abstract class ItemToolQuartz extends ItemTool
{
	protected ItemToolQuartz(float parAttackDamage, ToolMaterial parToolMaterial, Set parEffectiveBlocks)
	{
		super(parAttackDamage, parToolMaterial, parEffectiveBlocks);
	}

	@Override
	public float getStrVsBlock(ItemStack itemstack, Block block)
	{
		float f = super.getStrVsBlock(itemstack, block);

		//I can do this because this is only calculated client side
		f -= Minecraft.getMinecraft().thePlayer.dimension == Dimensions.NETHER ? -1.0F : 0.5F;
		return f <= 0.25 ? 0.25F : f;
	}

	@Override
	public boolean canHarvestBlock(Block parBlock, ItemStack parItemStack)
	{
		return parBlock.getMaterial() != Material.snow && parBlock.getMaterial() != Material.craftedSnow && parBlock != Blocks.snow && super.canHarvestBlock(parBlock, parItemStack);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase parEntityLiving, EntityLivingBase parEntity)
	{
		stack.damageItem(parEntity.dimension != Dimensions.NETHER ? 1 : 2, parEntity);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
	{
		if ((double) blockIn.getBlockHardness(worldIn, pos) != 0.0D)
		{
			stack.damageItem(playerIn.dimension == Dimensions.NETHER ? 1 : 2, playerIn);
		}

		return true;
	}
}
