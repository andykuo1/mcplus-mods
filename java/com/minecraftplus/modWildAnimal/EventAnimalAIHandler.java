package com.minecraftplus.modWildAnimal;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventAnimalAIHandler
{
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent par1Event)
	{
		double high = 0D, low = 0D;
		int taskid = 4;
		if (par1Event.entity instanceof EntityPig || par1Event.entity instanceof EntitySheep)
		{
			high = 1.33D;
			low = 0.8D;
			taskid = par1Event.entity instanceof EntityPig ? 5 : taskid;
		}
		else if (par1Event.entity instanceof EntityCow || par1Event.entity instanceof EntityMooshroom)
		{
			high = 2.13D;
			low = 1.2D;
		}
		else if (par1Event.entity instanceof EntityChicken)
		{
			high = 1.53D;
			low = 1.0D;
		}

		if (high != 0 && low != 0)
		{
			((EntityLiving) par1Event.entity).tasks.addTask(taskid, new EntityAIAvoidEntity((EntityCreature) par1Event.entity, EntityPlayer.class, 16.0F, low, high));
		}
	}
}
