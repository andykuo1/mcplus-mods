package com.minecraftplus.modSittable;

import net.minecraft.block.BlockStairs;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventSitHandler
{
	@SubscribeEvent
	public void onBlockInteract(PlayerInteractEvent par1Event)
	{
		if (par1Event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
		{
			if (par1Event.entity.worldObj.getBlock(par1Event.x, par1Event.y, par1Event.z) instanceof BlockStairs)
			{
				Entity entity;
				par1Event.entity.worldObj.spawnEntityInWorld(entity = new EntitySittable(par1Event.entity.worldObj, par1Event.x, par1Event.y, par1Event.z));
				entity.rotationYaw = par1Event.entityPlayer.rotationYaw - 180;
				par1Event.entityPlayer.mountEntity(entity);
			}
		}
	}
}
