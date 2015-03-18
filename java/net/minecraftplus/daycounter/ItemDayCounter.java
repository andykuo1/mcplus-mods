package net.minecraftplus.daycounter;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftplus._api.registry.IconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDayCounter extends Item
{
	private IIcon itemIcon1;

	public ItemDayCounter()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
		this.itemIcon = IconRegistry.add(par1IIconRegister, this);
		this.itemIcon1 = IconRegistry.add(par1IIconRegister, this, "", "blank");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int par1)
	{
		return par1 == 1 ? this.itemIcon1 : this.itemIcon;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (par7 == 0)
		{
			return false;
		}
		else if (par7 == 1)
		{
			return false;
		}
		else
		{
			int i1 = Direction.facingToDirection[par7];
			EntityHanging entityhanging = this.createHangingEntity(par1ItemStack, par3World, par4, par5, par6, i1);

			if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
			{
				return false;
			}
			else
			{
				if (entityhanging != null && entityhanging.onValidSurface())
				{
					if (!par3World.isRemote)
					{
						par3World.spawnEntityInWorld(entityhanging);
					}

					--par1ItemStack.stackSize;
				}

				return true;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if (par1ItemStack.hasTagCompound())
		{
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			int day = nbttagcompound.getInteger("Days");
			int era = nbttagcompound.getInteger("Eras");
			if (day != 0 || era != 0)
			{
				String nth = era == 3 ? "rd" : era == 2 ? "nd" : era == 1 ? "st" : "th";
				par3List.add(EnumChatFormatting.GRAY + "Day " + day + (era > 0 ? (" of " + era + nth + " Era") : ""));
			}
		}
	}

	public static ItemStack setItemStackTime(EntityDayCounter par1EntityDayCounter, ItemStack par2ItemStack)
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		nbttagcompound.setInteger("Days", par1EntityDayCounter.getTimeDay());
		nbttagcompound.setInteger("Eras", par1EntityDayCounter.getTimeEra());

		par2ItemStack.setTagCompound(nbttagcompound);
		return par2ItemStack;
	}

	private EntityHanging createHangingEntity(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6)
	{
		EntityDayCounter entity = new EntityDayCounter(par2World, par3, par4, par5, par6);
		EntityDayCounter.setEntityTime(entity, par1ItemStack);

		return (EntityHanging) entity;
	}
}
