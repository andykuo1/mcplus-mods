package com.minecraftplus.modBattleHearts;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventMaxHealthHandler
{
	private static final double HEALTH_BASE = 8D;
	private static final double HEALTH_INCR = 2D;
	private static final double HEALTH_PER_LEVEL = 5D;

	@SubscribeEvent
	public void onLivingEntityUpdate(LivingEvent.LivingUpdateEvent parEvent)
	{
		if (parEvent.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) parEvent.entity;

			if (player.isPlayerFullyAsleep())
			{
				int amount = (int) (HEALTH_INCR * (int) (player.experienceLevel / HEALTH_PER_LEVEL));
				this.setMaxHealth(player, amount);
				//this.setMaxDamage(player, amount / 2F);
				this.updateMaxHealth(player);

				if (player.equals(Minecraft.getMinecraft().thePlayer))
				{
					float f = player.experienceLevel > 30 ? 1.0F : (float)player.experienceLevel / 30.0F;
					player.worldObj.playSoundAtEntity(player, "random.levelup", f * 0.4F, 0.4F);
				}
			}
			else if (player.worldObj.getTotalWorldTime() % 100 == 0)
			{
				int amount = (int) (HEALTH_INCR * (int) (player.experienceLevel / HEALTH_PER_LEVEL));
				if (amount + 8 < player.getMaxHealth())
				{
					this.setMaxHealth(player, amount);
					//this.setMaxDamage(player, amount / 2F);
					this.updateMaxHealth(player);

					if (player.equals(Minecraft.getMinecraft().thePlayer))
					{
						float f = player.experienceLevel > 30 ? 1.0F : (float)player.experienceLevel / 30.0F;
						player.worldObj.playSoundAtEntity(player, "random.break", f * 0.6F, 0.6F);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent parEvent)
	{
		parEvent.target.attackEntityFrom(DamageSource.causePlayerDamage(parEvent.entityPlayer), parEvent.entityPlayer.getEntityData().getFloat("maxDamage"));
	}

	public void setMaxHealth(EntityPlayer parEntityPlayer, int parAmount)
	{
		parEntityPlayer.getEntityData().setInteger("maxHealth", parAmount);
	}

	public void addMaxHealth(EntityPlayer parEntityPlayer, int parAmount)
	{
		int max = parEntityPlayer.getEntityData().getInteger("maxHealth");
		this.setMaxHealth(parEntityPlayer, max + parAmount);
	}

	public void updateMaxHealth(EntityPlayer parEntityPlayer)
	{
		int health = parEntityPlayer.getEntityData().getInteger("maxHealth");
		parEntityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(HEALTH_BASE + health);
	}

	public void setMaxDamage(EntityPlayer parEntityPlayer, float parAmount)
	{
		parEntityPlayer.getEntityData().setFloat("maxDamage", parAmount);
	}

	public void addMaxDamage(EntityPlayer parEntityPlayer, float parAmount)
	{
		float max = parEntityPlayer.getEntityData().getFloat("maxDamage");
		this.setMaxDamage(parEntityPlayer, max + parAmount);
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent parEvent)
	{
		if (!(parEvent.entity instanceof EntityPlayer)) return;
		this.updateMaxHealth((EntityPlayer)parEvent.entity);
	}
}
