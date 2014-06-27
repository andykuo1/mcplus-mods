package com.minecraftplus.modRegion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventBiomeDisplayHandler
{
	private GuiBiomeName guiBiome;
	public EventBiomeDisplayHandler(GuiBiomeName par1GuiBiomeName)
	{
		this.guiBiome = par1GuiBiomeName;
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent parEvent)
	{
		if (!(parEvent.entity instanceof EntityPlayer)) return;
		if (parEvent.world.isRemote && parEvent.entity == Minecraft.getMinecraft().thePlayer)
		{
			this.guiBiome.reset(((EntityPlayer) parEvent.entity).dimension);
		}
	}
}
