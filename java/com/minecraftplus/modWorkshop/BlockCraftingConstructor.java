package com.minecraftplus.modWorkshop;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCraftingConstructor extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon field_150035_a;
	@SideOnly(Side.CLIENT)
	private IIcon field_150034_b;

	protected BlockCraftingConstructor()
	{
		super(Material.wood);
		this.setCreativeTab(MCP_Workshop.tabWorkshop);
		this.setBlockTextureName("crafting_table");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return par1 == 1 ? this.field_150035_a : (par1 == 0 ? Blocks.planks.getBlockTextureFromSide(par1) : (par1 != 2 && par1 != 4 ? this.blockIcon : this.field_150034_b));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
		this.field_150035_a = p_149651_1_.registerIcon(this.getTextureName() + "_top");
		this.field_150034_b = p_149651_1_.registerIcon(this.getTextureName() + "_front");
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (par1World.isRemote)
		{
			return true;
		}
		else
		{
			par5EntityPlayer.openGui(MCP_Workshop.INSTANCE, 0, par1World, par2, par3, par4);
			return true;
		}
	}
}
