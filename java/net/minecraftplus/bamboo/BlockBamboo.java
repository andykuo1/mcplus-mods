package net.minecraftplus.bamboo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBamboo extends BlockRotatedPillar
{
	public BlockBamboo()
	{
		super(Material.grass);

		this.setHardness(0.5F);
		this.setStepSound(Block.soundTypeGrass);

		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getSideIcon(int parSide)
	{
		return this.blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister parIconRegistry)
	{
		super.registerBlockIcons(parIconRegistry);
		
		this.field_150164_N = IconRegistry.add(parIconRegistry, this, "", "top");
	}
}