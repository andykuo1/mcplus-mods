package net.minecraftplus.battlehearts;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class EventHealthHandler
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing parEvent)
	{
		if (parEvent.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) parEvent.entity;
			if (ExtendedHealth.get((EntityPlayer)parEvent.entity) == null)
			{
				ExtendedHealth.register(player);
			}
		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent parEvent)
	{
		if (parEvent.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) parEvent.entity;

			int amount = EventHealthHandler.getExtendedHealth(player) - ExtendedHealth.HP_MAX_DOWN;
			if (amount < 0) amount = 0;

			EventHealthHandler.setExtendedHealth(player, amount);
			EventHealthHandler.setMaxHealth(player, EventHealthHandler.getExtendedHealth(player));
		}
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent parEvent)
	{
		if (parEvent.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) parEvent.entity;

			if (player.isPlayerFullyAsleep())
			{
				int level = player.experienceLevel;
				int amount = EventHealthHandler.getHealthFromExp(player);

				if (amount < EventHealthHandler.getExtendedHealth(player)) return;
				if (amount > ExtendedHealth.HP_MAX_MAX) amount = ExtendedHealth.HP_MAX_MAX;

				boolean flag = amount == EventHealthHandler.getExtendedHealth(player);

				EventHealthHandler.setExtendedHealth(player, amount);
				EventHealthHandler.setMaxHealth(player, EventHealthHandler.getExtendedHealth(player));

				if (ExtendedHealth.RESET_ON_SLEEP || !flag)
				{
					player.setHealth(player.getMaxHealth());
				}

				if (level != player.experienceLevel && player.worldObj.isRemote)
				{
					float f = player.experienceLevel > 30 ? 1.0F : (float)player.experienceLevel / 30.0F;
					player.worldObj.playSoundAtEntity(player, "random.levelup", f * 0.4F, 0.4F);
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent parEvent)
	{
		if (parEvent.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) parEvent.entity;

			if (!parEvent.world.isRemote)
			{
				MCP.PACKETS().getHandler().sendTo(new PacketMaxHealth(player), (EntityPlayerMP) player);
			}

			EventHealthHandler.setMaxHealth(player, EventHealthHandler.getExtendedHealth(player));
		}
	}

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone parEvent)
	{
		if (!ExtendedHealth.RESET_ON_DEATH)
		{
			ExtendedHealth.get(parEvent.entityPlayer).copy(ExtendedHealth.get(parEvent.original));

			EventHealthHandler.setMaxHealth(parEvent.entityPlayer, EventHealthHandler.getExtendedHealth(parEvent.entityPlayer));
			parEvent.entityPlayer.setHealth(parEvent.entityPlayer.getMaxHealth());

			MCP.PACKETS().getHandler().sendTo(new PacketMaxHealth(parEvent.entityPlayer), (EntityPlayerMP) parEvent.entityPlayer);
		}
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerLoggedOutEvent par1Event)
	{
		EventHealthHandler.setMaxHealth(par1Event.player, 20);
	}

	public static int getExtendedHealth(EntityPlayer parEntityPlayer)
	{
		ExtendedHealth prop = ExtendedHealth.get(parEntityPlayer);
		return prop == null ? ExtendedHealth.HP_MAX_BASE : prop.getHealth();
	}

	public static void setExtendedHealth(EntityPlayer parEntityPlayer, int parHealth)
	{
		ExtendedHealth prop = ExtendedHealth.get(parEntityPlayer);
		if (prop == null)
		{
			prop = ExtendedHealth.register(parEntityPlayer);
		}

		prop.setHealth(parHealth);

		if (parEntityPlayer instanceof EntityPlayerMP)
		{
			MCP.PACKETS().getHandler().sendTo(new PacketMaxHealth(parEntityPlayer), (EntityPlayerMP) parEntityPlayer);
		}
	}

	public static void setMaxHealth(EntityPlayer parEntityPlayer, int parHealth)
	{
		parEntityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ExtendedHealth.HP_MAX_BASE + parHealth);
	}

	public static int getHealthFromExp(EntityPlayer parEntityPlayer)
	{
		return (int) (ExtendedHealth.HP_MAX_UP * (int) (parEntityPlayer.experienceLevel / ExtendedHealth.EXP_PER_UP));
	}
}
