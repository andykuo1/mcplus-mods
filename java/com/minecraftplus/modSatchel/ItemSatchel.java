package com.minecraftplus.modSatchel;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;
import com.minecraftplus._common.dye.Dyeable;
import com.minecraftplus._common.dye.IDyeable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSatchel extends Item implements IDyeable.Item
{
	public ItemSatchel()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		return Dyeable.Item.getColorFromItemStack(par1ItemStack, par2, 10511680);
	}

	@Override
	public boolean hasColor(ItemStack par1ItemStack)
	{
		return Dyeable.Item.hasColor(par1ItemStack);
	}

	@Override
	public int getColor(ItemStack par1ItemStack)
	{
		return Dyeable.Item.getColor(par1ItemStack, 10511680);
	}

	@Override
	public void setColor(ItemStack par1ItemStack, int par2)
	{
		Dyeable.Item.setColor(par1ItemStack, par2);
	}

	@Override
	public void removeColor(ItemStack par1ItemStack)
	{
		Dyeable.Item.removeColor(par1ItemStack);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		super.onPlayerStoppedUsing(par1ItemStack, par2World, par3EntityPlayer, par4);
		par3EntityPlayer.openGui(MCP_Satchel.INSTANCE, 0, par2World, (int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY, (int) par3EntityPlayer.posZ);
		par2World.playSoundAtEntity(par3EntityPlayer, "mob.horse.leather", 0.15F, 1.0F);
	}

	public static InventorySatchel getSatchelInv(EntityPlayer par1EntityPlayer)
	{
		InventorySatchel inventorySatchel = null;

		if (par1EntityPlayer.getCurrentEquippedItem() != null && par1EntityPlayer.getCurrentEquippedItem().getItem() instanceof ItemSatchel)
		{
			ItemStack itemstack = par1EntityPlayer.getCurrentEquippedItem();
			inventorySatchel = new InventorySatchel(par1EntityPlayer, itemstack);
		}

		return inventorySatchel;
	}
}
