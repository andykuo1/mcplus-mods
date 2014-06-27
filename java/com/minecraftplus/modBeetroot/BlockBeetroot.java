package com.minecraftplus.modBeetroot;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBeetroot extends BlockCrops
{
	@SideOnly(Side.CLIENT)
	private IIcon[] itemIcons;

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
	protected Item func_149866_i()
	{
		return MCP_Beetroot.beetrootSeeds;
	}

	@Override
	protected Item func_149865_P()
	{
		return MCP_Beetroot.beetroot;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcons = new IIcon[4];

		for (int i = 0; i < this.itemIcons.length; ++i)
		{
			this.itemIcons[i] = par1IIconRegister.registerIcon("minecraftplus:" + this.getUnlocalizedName() + "_stage_" + i);
		}
	}
}
