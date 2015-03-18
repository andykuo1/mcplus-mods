package net.minecraftplus.quartz;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public abstract class ItemToolQuartz extends ItemTool
{
	/** An array of the blocks this tool is effective against */
	protected final Set blocksEffectiveAgainst;

	protected ItemToolQuartz(float par1, ToolMaterial par2ToolMaterial, Set par3ArrayOfBlock)
	{
		super(par1, par2ToolMaterial, par3ArrayOfBlock);
		this.blocksEffectiveAgainst = par3ArrayOfBlock;
	}

	@Override
	public boolean canHarvestBlock(Block parBlock, ItemStack parItemStack)
	{
		return parBlock.getMaterial() != Material.snow && parBlock.getMaterial() != Material.craftedSnow && parBlock != Blocks.snow;
	}

	@Override
	public float func_150893_a(ItemStack par1ItemStack, Block par2Block)
	{
		float f = par2Block != null && this.isBlockEffective(par2Block) ? this.efficiencyOnProperMaterial : 1F;
		f -= isInNether() ? -1.0F : 0.5F;
		return f <= 0.25 ? 0.25F : f;
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
	{
		par1ItemStack.damageItem(isInNether(par3EntityLivingBase) ? 1 : 2, par3EntityLivingBase);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3Block, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
	{
		if ((double) par3Block.getBlockHardness(par2World, par4, par5, par6) != 0.0D)
		{
			par1ItemStack.damageItem(isInNether(par7EntityLivingBase) ? 1 : 2, par7EntityLivingBase);
		}

		return true;
	}

	public boolean isBlockEffective(Block par1Block)
	{
		for(Object block : this.blocksEffectiveAgainst)
		{
			if (par1Block == (Block) block)
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isInNether()
	{
		return Minecraft.getMinecraft().thePlayer.dimension == -1;
	}

	public static boolean isInNether(Entity par1Entity)
	{
		return par1Entity.dimension == -1;
	}
}
