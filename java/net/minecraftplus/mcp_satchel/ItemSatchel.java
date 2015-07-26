package net.minecraftplus.mcp_satchel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftplus._api.dictionary.Sounds;
import net.minecraftplus._api.minecraft.base.ItemDyeable;

public class ItemSatchel extends ItemDyeable
{	
	public ItemSatchel()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack parItemStack)
	{
		return EnumAction.BLOCK;
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

		parEntityPlayer.openGui(_Satchel.INSTANCE, 0, parWorld, (int) parEntityPlayer.posX, (int) parEntityPlayer.posY, (int) parEntityPlayer.posZ);
		parWorld.playSoundAtEntity(parEntityPlayer, Sounds.MOB_HORSE_LEATHER, 0.15F, 1.0F);
	}

	public static IInventory getItemChest(ItemStack parItemStack)
	{
		return new InventoryItem(parItemStack, 18);
	}
}
