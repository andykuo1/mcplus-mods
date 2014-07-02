package com.minecraftplus.modCollision;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventMinecartCollisionHandler
{
	@SubscribeEvent
	public void onMinecartCollide(MinecartCollisionEvent par1Event)
	{
		float f = (float) (par1Event.minecart.motionX + par1Event.minecart.motionY + par1Event.minecart.motionZ) / 3F;//MAX is 16
		if (par1Event.collider instanceof EntityLivingBase)
		{
			((EntityLivingBase) par1Event.collider).attackEntityFrom(DamageSource.anvil, (int) (f / 2));
			par1Event.collider.addVelocity(par1Event.minecart.motionX * (f / 3), par1Event.minecart.motionY * (f / 3), par1Event.minecart.motionZ * (f / 3));
		}
	}
}
