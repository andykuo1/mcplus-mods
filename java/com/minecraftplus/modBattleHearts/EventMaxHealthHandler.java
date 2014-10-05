package com.minecraftplus.modBattleHearts;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import com.minecraftplus._base.registry.PacketRegistry;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class EventMaxHealthHandler
{
	public static final int HEALTH_BASE = 8;
	public static final int HEALTH_INCR = 2;
	public static final int HEALTH_PER_LEVEL = 5;

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent par1Event)
	{
		if (par1Event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1Event.entity;

			if (player.isPlayerFullyAsleep())
			{
				int level = player.experienceLevel;
				int amount = calcMaxHealthByExp(player);
				if (amount < getMaxHealthData(player)) return;
				
				setMaxHealthData(player, amount);
				updateMaxHealthFromData(player);

				player.setHealth(player.getMaxHealth());

				if (level != player.experienceLevel && player.worldObj.isRemote)
				{
					float f = player.experienceLevel > 30 ? 1.0F : (float)player.experienceLevel / 30.0F;
					player.worldObj.playSoundAtEntity(player, "random.levelup", f * 0.4F, 0.4F);
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent par1Event)
	{
		if (par1Event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1Event.entity;

			if (!par1Event.world.isRemote)
			{
				PacketRegistry.getPacketHandler().sendTo(new PacketMaxHealth(player), (EntityPlayerMP) player);
			}

			updateMaxHealthFromData(player);
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerChangedDimensionEvent par1Event)
	{
		PacketRegistry.getPacketHandler().sendTo(new PacketMaxHealth(par1Event.player), (EntityPlayerMP) par1Event.player);
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerLoggedOutEvent par1Event)
	{
		setMaxHealth(par1Event.player, 20);
	}

	public static void updateMaxHealthFromData(EntityPlayer par1EntityPlayer)
	{
		int health = getMaxHealthData(par1EntityPlayer);
		par1EntityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(HEALTH_BASE + health);
	}

	public static int getMaxHealthData(EntityPlayer par1EntityPlayer)
	{
		return par1EntityPlayer.getEntityData().getInteger("maxHealth");
	}
	
	public static void setMaxHealthData(EntityPlayer par1EntityPlayer, int par2)
	{
		par1EntityPlayer.getEntityData().setInteger("maxHealth", par2);
		if (par1EntityPlayer instanceof EntityPlayerMP)
		{
			PacketRegistry.getPacketHandler().sendTo(new PacketMaxHealth(par1EntityPlayer), (EntityPlayerMP) par1EntityPlayer);
		}
	}

	public static void setMaxHealth(EntityPlayer par1EntityPlayer, int par2)
	{
		par1EntityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(par2);
	}

	public static int calcMaxHealthByExp(EntityPlayer par1EntityPlayer)
	{
		return (int) (HEALTH_INCR * (int) (par1EntityPlayer.experienceLevel / HEALTH_PER_LEVEL));
	}
}
