package com.minecraftplus.modSatchel;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;
import com.minecraftplus._common.dye.Dyeable;
import com.minecraftplus._common.dye.IDyeable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEnderSatchel extends Item implements IDyeable.Item
{
	@SideOnly(Side.CLIENT)
	private IIcon itemIcon1;

	public ItemEnderSatchel()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
		this.itemIcon1 = IconRegistry.register(par1IIconRegister, this.getUnlocalizedName() + ".overlay");
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamageForRenderPass(int par1, int par2)
	{
		return par2 == 1 ? this.itemIcon1 : super.getIconFromDamageForRenderPass(par1, par2);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		return Dyeable.Item.getColorFromItemStack(par1ItemStack, par2, 16777215);
	}

	@Override
	public boolean hasColor(ItemStack par1ItemStack)
	{
		return Dyeable.Item.hasColor(par1ItemStack);
	}

	@Override
	public int getColor(ItemStack par1ItemStack)
	{
		return Dyeable.Item.getColor(par1ItemStack, 0x378066);
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
		par3EntityPlayer.openGui(MCP_Satchel.INSTANCE, 1, par2World, 0, 0, 0);
		par2World.playSoundAtEntity(par3EntityPlayer, "mob.horse.leather", 0.15F, 1.0F);
	}

	public static InventorySatchel getSatchelInv(EntityPlayer par1EntityPlayer)
	{
		InventorySatchel inventorySatchel = null;

		if (par1EntityPlayer.getCurrentEquippedItem() != null && par1EntityPlayer.getCurrentEquippedItem().getItem() instanceof ItemEnderSatchel)
		{
			ItemStack itemstack = par1EntityPlayer.getCurrentEquippedItem();
			inventorySatchel = new InventorySatchel(par1EntityPlayer, itemstack);
		}

		return inventorySatchel;
	}
}