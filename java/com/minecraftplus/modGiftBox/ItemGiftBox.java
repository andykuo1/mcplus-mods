package com.minecraftplus.modGiftBox;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.minecraftplus._base.registry.IconRegistry;
import com.minecraftplus._common.dye.Dyeable;
import com.minecraftplus._common.dye.IDyeable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGiftBox extends Item implements IDyeable.Item
{
	@SideOnly(Side.CLIENT)
	private IIcon itemIcon1;

	public ItemGiftBox()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.register(par1IIconRegister, this);
		this.itemIcon1 = IconRegistry.register(par1IIconRegister, this, ".overlay");
	}

	@SideOnly(Side.CLIENT)
	@Override
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
		return Dyeable.Item.getColorFromItemStack(par1ItemStack, par2, this.getDefaultColor(par1ItemStack));
	}

	@Override
	public boolean hasColor(ItemStack par1ItemStack)
	{
		return Dyeable.Item.hasColor(par1ItemStack);
	}

	@Override
	public int getColor(ItemStack par1ItemStack)
	{
		return Dyeable.Item.getColor(par1ItemStack, 0xFFFFFF);
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
		return EnumAction.eat;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 32;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		--par1ItemStack.stackSize;
		par2World.playSoundAtEntity(par3EntityPlayer, "random.pop", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
		if (!par2World.isRemote && par1ItemStack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			NBTTagList nbttaglist = nbttagcompound.getCompoundTag("Inventory").getTagList("StackItems", 10);
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.getCompoundTagAt(0);
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound1);

			par3EntityPlayer.entityDropItem(itemstack, 1F);
		}

		return par1ItemStack;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	private int getDefaultColor(ItemStack par1ItemStack)
	{
		return (0xFFFFFF ^ this.getColor(par1ItemStack)) * 0x111111 + 0x666666;
	}
}
