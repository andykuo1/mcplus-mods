package net.minecraftplus.mcp_wild_animal;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.google.common.base.Predicate;

public class EventHandlerWildAnimal
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent parEvent)
	{
		double high = 0D, low = 0D;
		int taskid = 4;
		
		if (parEvent.entity instanceof EntityPig || parEvent.entity instanceof EntitySheep)
		{
			high = 1.33D;
			low = 0.8D;
			taskid = parEvent.entity instanceof EntityPig ? 5 : taskid;
		}
		else if (parEvent.entity instanceof EntityCow || parEvent.entity instanceof EntityMooshroom)
		{
			high = 2.13D;
			low = 1.2D;
		}
		else if (parEvent.entity instanceof EntityChicken || parEvent.entity instanceof EntityRabbit)
		{
			high = 1.53D;
			low = 1.0D;
		}

		if (high != 0 && low != 0)
		{
			((EntityLiving) parEvent.entity).tasks.addTask(taskid, new EntityAIAvoidEntity((EntityCreature) parEvent.entity, new Predicate() {
				@Override
				public boolean apply(Object p_apply_1_)
				{
					return p_apply_1_ instanceof EntityPlayer;
				}
			}, 16.0F, low, high));
		}
	}
}
