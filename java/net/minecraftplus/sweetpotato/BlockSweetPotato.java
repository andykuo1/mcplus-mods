package net.minecraftplus.sweetpotato;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSweetPotato extends BlockCrops
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
	protected boolean canPlaceBlockOn(Block par1Block)
	{
		return par1Block == Blocks.farmland;
	}

	@Override
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
	{
		return this.canPlaceBlockOn(p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_));
	}

	@Override
	protected Item func_149866_i()
	{
		return ModSweetPotato.sweetPotato;
	}

	@Override
	protected Item func_149865_P()
	{
		return ModSweetPotato.sweetPotato;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcons = new IIcon[4];

		for (int i = 0; i < this.itemIcons.length; ++i)
		{
			this.itemIcons[i] = IconRegistry.add(par1IIconRegister, this, "stage_" + i);
		}
	}
}
