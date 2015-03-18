package net.minecraftplus.sickle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3Block, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
	{
		for(int i = -1; i <= 1; i++)
		{
			for(int j = -1; j <= 1; j++)
			{
				if (i == 0 && j == 0) continue;
				int x = par4 + i;
				int y = par5;
				int z = par6 + j;
				Block block = par2World.getBlock(x, y, z);
				int metadata = par2World.getBlockMetadata(x, y, z);
				if (this.isCrop(block) && EventSickleHandler.isFullGrownCrop(par2World, x, y, z, block))
				{
					block.dropBlockAsItem(par2World, x, y, z, metadata, 1);
					par2World.setBlockToAir(x, y, z);
					par1ItemStack.damageItem(1, par7EntityLivingBase);
				}
			}
		}
		
		par1ItemStack.damageItem(1, par7EntityLivingBase);
		return super.onBlockDestroyed(par1ItemStack, par2World, par3Block, par4, par5, par6, par7EntityLivingBase);
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int metadata)
	{
		return this.isCrop(block) ? super.getDigSpeed(itemstack, block, metadata) : 0F;
	}
	
	public boolean isCrop(Block par1Block)
	{
		return par1Block instanceof BlockCrops || par1Block.getMaterial() == Material.gourd || par1Block.getMaterial() == Material.plants || par1Block.getMaterial() == Material.vine;
	}
}
