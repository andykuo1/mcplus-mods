package com.minecraftplus.modFoodEssentials;

import java.util.Random;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCabbageBase extends BlockCrops
{
	@SideOnly(Side.CLIENT)
	private IIcon[] itemIcons;

	public BlockCabbageBase()
	{
		super();
		this.setBlockBounds(0.25F, 0F, 0.25F, 0.75F, 1F, 0.75F);
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
		return MCP_FoodEssentials.renderCabbagePlant.getRenderID();
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);

		if (par1World.isAirBlock(par2, par3 + 1, par4) && par1World.getBlockMetadata(par2, par3, par4) == 7)
		{
			par1World.setBlock(par2, par3 + 1, par4, MCP_FoodEssentials.cabbages, 0, 2);
		}
	}

	@Override
	public void func_149863_m(World par1World, int par2, int par3, int par4)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);

		if (l >= 7)
		{
			if (par1World.isAirBlock(par2, par3 + 1, par4))
			{
				if (par1World.rand.nextInt(3) == 0)
				{
					par1World.setBlock(par2, par3 + 1, par4, MCP_FoodEssentials.cabbages, 0, 2);
				}
			}
			else if (par1World.getBlock(par2, par3 + 1, par4) == MCP_FoodEssentials.cabbages)
			{
				l += MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

				if (l > 7)
				{
					l = 7;
				}

				par1World.setBlockMetadataWithNotify(par2, par3 + 1, par4, l, 2);
			}
		}
		else
		{
			l += MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

			if (l > 7)
			{
				l = 7;
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
		}
	}

	@Override
	public boolean func_149851_a(World par1World, int par2, int par3, int par4, boolean par5)
	{
		return par1World.getBlockMetadata(par2, par3, par4) != 7 || (par1World.isAirBlock(par2, par3 + 1, par4) ? true : (par1World.getBlock(par2, par3 + 1, par4) == MCP_FoodEssentials.cabbages ? par1World.getBlockMetadata(par2, par3 + 1, par4) != 7 : false));
	}

	@Override
	protected Item func_149866_i()
	{
		return MCP_FoodEssentials.cabbageSeeds;
	}

	@Override
	protected Item func_149865_P()
	{
		return MCP_FoodEssentials.cabbageSeeds;
	}
}
