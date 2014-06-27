package com.minecraftplus.modSurvivor;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import com.google.common.collect.Sets;
import com.minecraftplus._base.registry.IconRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHatchet extends ItemTool
{
	private static final Set field_150917_c = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});

	protected ItemHatchet(Item.ToolMaterial par1ToolMaterial)
	{
		super(3.0F, par1ToolMaterial, field_150917_c);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
	}

	@Override
	public float func_150893_a(ItemStack par1ItemStack, Block par2Block)
	{
		return par2Block.getMaterial() != Material.wood && par2Block.getMaterial() != Material.plants && par2Block.getMaterial() != Material.vine ? super.func_150893_a(par1ItemStack, par2Block) : this.efficiencyOnProperMaterial;
	}
}