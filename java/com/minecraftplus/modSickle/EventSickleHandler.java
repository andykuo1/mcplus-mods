package com.minecraftplus.modSickle;

import net.minecraft.block.BlockCrops;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventSickleHandler
{
	@SubscribeEvent
	public void onBreakSpeed(BreakSpeed par1Event)
	{
		if (par1Event.block instanceof BlockCrops)
		{
			if (par1Event.entityPlayer.getCurrentEquippedItem() != null && par1Event.entityPlayer.getCurrentEquippedItem().getItem() == MCP_Sickle.sickle)
			{
				if (((BlockCrops) par1Event.block).func_149851_a(par1Event.entity.worldObj, par1Event.x, par1Event.y, par1Event.z, par1Event.entity.worldObj.isRemote))
				{
					par1Event.newSpeed = 0F;
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BreakEvent par1Event)
	{
		if (par1Event.block instanceof BlockCrops)
		{
			if (par1Event.getPlayer().getCurrentEquippedItem() != null && par1Event.getPlayer().getCurrentEquippedItem().getItem() == MCP_Sickle.sickle)
			{
				if (((BlockCrops) par1Event.block).func_149851_a(par1Event.world, par1Event.x, par1Event.y, par1Event.z, par1Event.world.isRemote))
				{
					par1Event.setCanceled(true);
				}
			}
		}
	}
}
