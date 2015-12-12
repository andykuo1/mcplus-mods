package net.minecraftplus.mcp_overgrowth;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerOvergrowth
{
	public static boolean REFORESTATION = true;
	private static int REFOREST_AGE = 100;

	@SubscribeEvent
	public void onAttemptBonemeal(BonemealEvent event)
	{
		if (!event.world.isRemote && event.block.getBlock() == Blocks.vine && event.pos.getY() > 0)
		{
			int i = event.pos.getY();
			while(event.world.getBlockState(event.pos.down(++i)) == Blocks.vine) {}
			i++;

			while(event.world.isAirBlock(event.pos.down(++i)) && event.world.rand.nextInt(3) != 0)
			{
				event.world.setBlockState(event.pos.down(i), event.block);
			}
		}
	}

	@SubscribeEvent
	public void onEntityItemUpdate(EntityEvent.EnteringChunk parEvent)
	{
		if (!REFORESTATION) return;
		if (!(parEvent.entity instanceof EntityItem)) return;

		EntityItem entityitem = (EntityItem) parEvent.entity;
		ItemStack itemstack = entityitem.getDataWatcher().getWatchableObjectItemStack(10);
		if (itemstack == null || !(Block.getBlockFromItem(itemstack.getItem()) instanceof BlockSapling)) return;
		entityitem.lifespan = REFOREST_AGE;
	}

	@SubscribeEvent
	public void onEntityItemExpire(ItemExpireEvent parEvent)
	{
		if (!REFORESTATION) return;
		if (!(Block.getBlockFromItem(parEvent.entityItem.getEntityItem().getItem()) instanceof BlockSapling)) return;

		BlockSapling sapling = (BlockSapling) Block.getBlockFromItem(parEvent.entityItem.getEntityItem().getItem());
		int x = (int) (parEvent.entity.posX);
		int y = (int) (parEvent.entity.posY + 0.5);
		int z = (int) (parEvent.entity.posZ);
		BlockPos pos = new BlockPos(x, y, z);

		if (sapling.canBlockStay(parEvent.entity.worldObj, pos, sapling.getDefaultState()))
		{
			parEvent.entity.worldObj.setBlockState(pos, sapling.getStateFromMeta(parEvent.entityItem.getEntityItem().getItemDamage()), 3);
		}
		else
		{
			parEvent.extraLife = parEvent.entityItem.lifespan - REFOREST_AGE;
			parEvent.setCanceled(true);
		}
	}
}