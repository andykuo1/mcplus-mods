package com.minecraftplus._base.handler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.minecraftplus._common.dye.IDyeable;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventDyeRemoveHandler
{
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent parEvent)
	{
		if (parEvent.entity instanceof EntityPlayer && parEvent.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK))
		{
			EntityPlayer entityplayer = (EntityPlayer) parEvent.entity;
			ItemStack itemstack = entityplayer.getHeldItem();
			if (itemstack != null)
			{
				if (itemstack.getItem() instanceof IDyeable.Item)
				{
					World world = entityplayer.getEntityWorld();
					Block block = world.getBlock(parEvent.x, parEvent.y, parEvent.z);
					if (block instanceof BlockCauldron)
					{
						int metadata = world.getBlockMetadata(parEvent.x, parEvent.y, parEvent.z);

						if (metadata != 0)
						{
							((IDyeable.Item) itemstack.getItem()).removeColor(itemstack);
						}
					}
				}
			}
		}
	}
}
