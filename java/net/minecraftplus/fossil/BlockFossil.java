package net.minecraftplus.fossil;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFossil extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon sideIcon, bottomIcon;

	public BlockFossil()
	{
		super(Material.rock);

		this.setHardness(3.5F);
		this.setResistance(6.0F);
		this.setStepSound(Block.soundTypeStone);

		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = IconRegistry.add(par1IIconRegister, this, "", "top");
		this.sideIcon = IconRegistry.add(par1IIconRegister, this, "", "side");
		this.bottomIcon = IconRegistry.add(par1IIconRegister, this, "", "bottom");
	}


	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return par1 == 1 ? this.blockIcon : (par1 == 0 ? this.bottomIcon : this.sideIcon);
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return Item.getItemFromBlock(Blocks.cobblestone);
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

		if (!par1World.isRemote)
		{
			for (int i = par1World.rand.nextInt(4); i >= 0; i--)
			{
				this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(Items.bone));
			}
		}
	}
}
