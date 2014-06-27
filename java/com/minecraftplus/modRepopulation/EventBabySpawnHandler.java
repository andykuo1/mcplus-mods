package com.minecraftplus.modRepopulation;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventBabySpawnHandler
{
	@SubscribeEvent
	public void onEntityItemDeath(ItemExpireEvent par1Event)
	{
		if (!par1Event.entity.worldObj.isRemote && par1Event.entityItem.getEntityItem().getItem() == Items.egg)
		{
			if (par1Event.entity.worldObj.rand.nextInt(4) == 0)
			{
				EntityChicken entitychicken = new EntityChicken(par1Event.entity.worldObj);
				entitychicken.setGrowingAge(-24000);
				entitychicken.setLocationAndAngles(par1Event.entity.posX, par1Event.entity.posY, par1Event.entity.posZ, par1Event.entity.rotationYaw, 0.0F);
				par1Event.entity.worldObj.spawnEntityInWorld(entitychicken);
			}
		}
	}
}
