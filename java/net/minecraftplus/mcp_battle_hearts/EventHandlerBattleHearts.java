package net.minecraftplus.mcp_battle_hearts;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftplus._api.minecraft.PacketHandler;

public class EventHandlerBattleHearts
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing parEvent)
	{
		if (parEvent.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) parEvent.entity;
			if (ExtendedPropertyHealth.get((EntityPlayer)parEvent.entity) == null)
			{
				ExtendedPropertyHealth.register(player);
			}
		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent parEvent)
	{
		if (parEvent.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) parEvent.entity;

			int amount = getExtendedHealth(player) - ExtendedPropertyHealth.HP_MAX_DOWN;
			if (amount < 0) amount = 0;

			setExtendedHealth(player, amount);
			setMaxHealth(player, getExtendedHealth(player));
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
				int amount = getHealthFromExp(player);

				if (amount < getExtendedHealth(player)) return;
				if (amount > ExtendedPropertyHealth.HP_MAX_MAX) amount = ExtendedPropertyHealth.HP_MAX_MAX;

				boolean flag = amount == getExtendedHealth(player);

				setExtendedHealth(player, amount);
				setMaxHealth(player, getExtendedHealth(player));

				if (ExtendedPropertyHealth.RESET_ON_SLEEP || !flag)
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
				PacketHandler.INSTANCE.getChannel().sendTo(new PacketMaxHealth(player), (EntityPlayerMP) player);
			}

			setMaxHealth(player, getExtendedHealth(player));
		}
	}

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone parEvent)
	{
		if (!ExtendedPropertyHealth.RESET_ON_DEATH)
		{
			ExtendedPropertyHealth.get(parEvent.entityPlayer).copy(ExtendedPropertyHealth.get(parEvent.original));

			setMaxHealth(parEvent.entityPlayer, getExtendedHealth(parEvent.entityPlayer));
			parEvent.entityPlayer.setHealth(parEvent.entityPlayer.getMaxHealth());

			PacketHandler.INSTANCE.getChannel().sendTo(new PacketMaxHealth(parEvent.entityPlayer), (EntityPlayerMP) parEvent.entityPlayer);
		}
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerLoggedOutEvent par1Event)
	{
		setMaxHealth(par1Event.player, 20);
	}

	public static int getExtendedHealth(EntityPlayer parEntityPlayer)
	{
		ExtendedPropertyHealth prop = ExtendedPropertyHealth.get(parEntityPlayer);
		return prop == null ? ExtendedPropertyHealth.HP_MAX_BASE : prop.getHealth();
	}

	public static void setExtendedHealth(EntityPlayer parEntityPlayer, int parHealth)
	{
		ExtendedPropertyHealth prop = ExtendedPropertyHealth.get(parEntityPlayer);
		if (prop == null)
		{
			prop = ExtendedPropertyHealth.register(parEntityPlayer);
		}

		prop.setHealth(parHealth);

		if (parEntityPlayer instanceof EntityPlayerMP)
		{
			PacketHandler.INSTANCE.getChannel().sendTo(new PacketMaxHealth(parEntityPlayer), (EntityPlayerMP) parEntityPlayer);
		}
	}

	public static void setMaxHealth(EntityPlayer parEntityPlayer, int parHealth)
	{
		parEntityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ExtendedPropertyHealth.HP_MAX_BASE + parHealth);
	}

	public static int getHealthFromExp(EntityPlayer parEntityPlayer)
	{
		return (int) (ExtendedPropertyHealth.HP_MAX_UP * (int) (parEntityPlayer.experienceLevel / ExtendedPropertyHealth.EXP_PER_UP));
	}
}
