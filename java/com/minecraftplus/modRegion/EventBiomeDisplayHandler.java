package com.minecraftplus.modRegion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventBiomeDisplayHandler
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent parEvent)
	{
		if (!(parEvent.entity instanceof EntityPlayer)) return;
		if (parEvent.world.isRemote && parEvent.entity == Minecraft.getMinecraft().thePlayer)
		{
			ClientProxy.guiBiome.reset(((EntityPlayer) parEvent.entity).dimension);
		}
	}
}
