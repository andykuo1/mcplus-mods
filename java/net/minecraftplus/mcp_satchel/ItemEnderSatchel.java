package net.minecraftplus.mcp_satchel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftplus._api.dictionary.Sounds;

public class ItemEnderSatchel extends ItemSatchel
{
	@Override
	public int getDefaultColor()
	{
		return 0x378066;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack parItemStack, World parWorld, EntityPlayer parEntityPlayer, int par4)
	{
		super.onPlayerStoppedUsing(parItemStack, parWorld, parEntityPlayer, par4);

		parEntityPlayer.openGui(_Satchel.INSTANCE, 1, parWorld, (int) parEntityPlayer.posX, (int) parEntityPlayer.posY, (int) parEntityPlayer.posZ);
		parWorld.playSoundAtEntity(parEntityPlayer, Sounds.MOB_HORSE_LEATHER, 0.15F, 1.0F);
	}

	public static IInventory getItemChest(ItemStack parItemStack, EntityPlayer parEntityPlayer)
	{
		return new InventoryEnderSatchel(parItemStack, parEntityPlayer, 18);
	}
}
