package com.minecraftplus.modCucumber;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCucumber extends BlockCrops
{
	@SideOnly(Side.CLIENT)
	private IIcon[] itemIcons;

	public BlockCucumber()
	{
		super();
		this.setBlockBounds(0.25F, 0F, 0.25F, 0.75F, 0.75F, 0.75F);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcons = new IIcon[4];

		for (int i = 0; i < this.itemIcons.length; ++i)
		{
			this.itemIcons[i] = IconRegistry.register(par1IIconRegister, this, "_stage_" + i);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		if (par2 < 7)
		{
			if (par2 == 6)
			{
				par2 = 5;
			}

			return this.itemIcons[par2 >> 1];
		}
		else
		{
			return this.itemIcons[3];
		}
	}

	@Override
	public int getRenderType()
	{
		return MCP_Cucumber.renderCucumberPlant.getRenderID();
	}

	@Override
	protected Item func_149866_i()
	{
		return MCP_Cucumber.cucumber;
	}

	@Override
	protected Item func_149865_P()
	{
		return MCP_Cucumber.cucumber;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5Block, int par6)
	{
		super.breakBlock(par1World, par2, par3, par4, par5Block, par6);

		if (par1World.getBlock(par2, par3 - 1, par4) == MCP_Cucumber.cucumbersBase)
		{
			par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, 5, 2);
		}
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		return par1World.getBlock(par2, par3 - 1, par4) == MCP_Cucumber.cucumbersBase;
	}
}