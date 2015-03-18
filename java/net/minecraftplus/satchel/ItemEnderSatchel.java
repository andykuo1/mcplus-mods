package net.minecraftplus.satchel;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftplus._api.registry.IconRegistry;
import net.minecraftplus._common.ItemDyeable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEnderSatchel extends ItemDyeable
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
		super.registerIcons(par1IIconRegister);
		this.itemIcon1 = IconRegistry.add(par1IIconRegister, this, "", "overlay");
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

	@Override
	public int getDefaultColor()
	{
		return 0x378066;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack parItemStack)
	{
		return EnumAction.block;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack parItemStack)
	{
		return 72000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer)
	{
		parEntityPlayer.setItemInUse(parItemStack, this.getMaxItemUseDuration(parItemStack));
		return parItemStack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer, int par4)
	{
		super.onPlayerStoppedUsing(parItemStack, parWorld, parEntityPlayer, par4);

		parEntityPlayer.openGui(ModSatchel.INSTANCE, 1, parWorld, (int) parEntityPlayer.posX, (int) parEntityPlayer.posY, (int) parEntityPlayer.posZ);
		parWorld.playSoundAtEntity(parEntityPlayer, "mob.horse.leather", 0.15F, 1.0F);
	}

	public static IInventory getItemChest(ItemStack parItemStack, EntityPlayer parEntityPlayer)
	{
		return new InventoryEnderSatchel(parItemStack, parEntityPlayer, 18);
	}
}
