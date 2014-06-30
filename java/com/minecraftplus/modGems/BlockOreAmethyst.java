package com.minecraftplus.modGems;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOreAmethyst extends BlockOre
{
	public BlockOreAmethyst()
	{
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypePiston);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = IconRegistry.register(par1IIconRegister, this.getUnlocalizedName());
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return MCP_Gems.ruby;
	}
	
	private Random rand = new Random();
	@Override
    public int getExpDrop(IBlockAccess par1IBlockAccess, int par2, int par3)
    {
        if (this.getItemDropped(par2, rand, par3) != Item.getItemFromBlock(this))
        {
            return MathHelper.getRandomIntegerInRange(rand, 2, 5);
        }
        
        return 0;
    }
}
