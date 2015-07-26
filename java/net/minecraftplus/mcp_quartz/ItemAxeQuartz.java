package net.minecraftplus.mcp_quartz;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftplus._api.dictionary.Dimensions;

import com.google.common.collect.Sets;

public class ItemAxeQuartz extends ItemToolQuartz
{
	public ItemAxeQuartz(ToolMaterial par1ToolMaterial)
	{
		super(3.0F, par1ToolMaterial, Sets.newHashSet(new Block[] {
				Blocks.nether_brick_fence}));
	}

	@Override
	public float getStrVsBlock(ItemStack itemstack, Block block)
	{
		float f;
		if (block.getMaterial() == Material.wood)
		{
			f = this.efficiencyOnProperMaterial;

			//I can do this because this is only calculated client side
			f -= Minecraft.getMinecraft().thePlayer.dimension == Dimensions.NETHER ? -1.0F : 0.5F;
			f = f < 0.25F ? 0.25F : f;
		}
		else
		{
			f = super.getStrVsBlock(itemstack, block);
		}

		return f;
	}
}
