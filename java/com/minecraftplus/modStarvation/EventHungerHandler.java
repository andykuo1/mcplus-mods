package com.minecraftplus.modStarvation;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHungerHandler
{
	private long sleepTime;

	@SubscribeEvent
	public void onLivingEntityUpdate(LivingEvent.LivingUpdateEvent par1Event)
	{
		if (par1Event.entity instanceof EntityPlayer && !((EntityPlayer) par1Event.entity).capabilities.isCreativeMode)
		{
			EntityPlayer player = (EntityPlayer) par1Event.entity;

			if (player.isPlayerFullyAsleep())
			{
				this.sleepTime = par1Event.entity.worldObj.getWorldTime();
			}
			else
			{
				if (this.sleepTime != 0)
				{
					for(long deltaTime = par1Event.entity.worldObj.getWorldTime() - this.sleepTime; deltaTime > 0; deltaTime -= 200)
					{
						player.getFoodStats().addExhaustion(1F);
					}

					this.sleepTime = 0;
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent par1Event)
	{
		if (par1Event.entity instanceof EntityPlayer && !((EntityPlayer) par1Event.entity).capabilities.isCreativeMode && par1Event.entity.ticksExisted % 80 == 0)
		{
			((EntityPlayer) par1Event.entity).getFoodStats().addExhaustion(1F);
		}
	}
}
