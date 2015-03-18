package net.minecraftplus.lumber;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventReforestationHandler
{
	public static boolean active = true;
	private static int reforestAge = 100;

	@SubscribeEvent
	public void onEntityItemUpdate(EntityEvent.EnteringChunk parEvent)
	{
		if (!EventReforestationHandler.active) return;
		if (!(parEvent.entity instanceof EntityItem)) return;

		EntityItem entityitem = (EntityItem) parEvent.entity;
		ItemStack itemstack = entityitem.getDataWatcher().getWatchableObjectItemStack(10);
		if (itemstack == null || !(Block.getBlockFromItem(itemstack.getItem()) instanceof BlockSapling)) return;
		entityitem.lifespan = reforestAge;
	}

	@SubscribeEvent
	public void onEntityItemExpire(ItemExpireEvent parEvent)
	{
		if (!EventReforestationHandler.active) return;
		if (!(Block.getBlockFromItem(parEvent.entityItem.getEntityItem().getItem()) instanceof BlockSapling)) return;

		BlockSapling sapling = (BlockSapling) Block.getBlockFromItem(parEvent.entityItem.getEntityItem().getItem());
		int x = (int) (parEvent.entity.posX);
		int y = (int) (parEvent.entity.posY + 0.5);
		int z = (int) (parEvent.entity.posZ);

		if (sapling.canBlockStay(parEvent.entity.worldObj, x, y, z))
		{
			parEvent.entity.worldObj.setBlock(x, y, z, sapling, parEvent.entityItem.getEntityItem().getItemDamage(), 3);
		}
		else
		{
			parEvent.extraLife = parEvent.entityItem.lifespan - reforestAge;
			parEvent.setCanceled(true);
		}
	}
}
