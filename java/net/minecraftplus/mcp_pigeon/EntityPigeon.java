package net.minecraftplus.mcp_pigeon;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPigeon extends EntityPigeo
{
	public EntityPigeon(World worldIn)
	{
		super(worldIn);
		this.tasks.addTask(4, new EntityAIFollowFriend(this, 0.8D, 3.0F, 2.0F));
		this.setupSentAI();
	}

	public void send(EntityPlayer player)
	{
		this.setSent(true);
		this.navigator.clearPathEntity();
		this.setAttackTarget((EntityLivingBase)null);
		this.aiSit.setSitting(true);
		this.setFriendId(player.getUniqueID().toString());
	}

	public void send(String parPlayerName)
	{
		EntityPlayer player = this.worldObj.getPlayerEntityByName(parPlayerName);
		if (player != null)
		{
			System.out.println("Sending to " + parPlayerName);
			this.send(player);
		}
	}

	public void unsend()
	{
		this.setSent(false);
		this.navigator.clearPathEntity();
		this.setAttackTarget((EntityLivingBase)null);
		this.aiSit.setSitting(false);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(22, Byte.valueOf((byte)0));
		this.dataWatcher.addObject(23, "");
	}

	public boolean isSent()
	{
		return (this.dataWatcher.getWatchableObjectByte(22) & 1) != 0;
	}

	public void setSent(boolean sent)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(22);

		if (sent)
		{
			this.dataWatcher.updateObject(22, Byte.valueOf((byte)(b0 | 1)));
		}
		else
		{
			this.dataWatcher.updateObject(22, Byte.valueOf((byte)(b0 & -2)));
		}

		this.setupSentAI();
	}

	public String getFriendId()
	{
		return this.dataWatcher.getWatchableObjectString(23);
	}

	public void setFriendId(String ownerUuid)
	{
		this.dataWatcher.updateObject(23, ownerUuid);
	}

	public EntityLivingBase getFriendEntity()
	{
		try
		{
			UUID uuid = UUID.fromString(this.getFriendId());
			return uuid == null ? null : this.worldObj.getPlayerEntityByUUID(uuid);
		}
		catch (IllegalArgumentException illegalargumentexception)
		{
			return null;
		}
	}

	public boolean isFriend(EntityLivingBase entityIn)
	{
		return entityIn == this.getFriendEntity();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);

		if (this.getFriendId() == null)
		{
			tagCompound.setString("FriendUUID", "");
		}
		else
		{
			tagCompound.setString("FriendUUID", this.getFriendId());
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund)
	{
		super.readEntityFromNBT(tagCompund);
		String s = "";

		if (tagCompund.hasKey("FriendUUID", 8))
		{
			s = tagCompund.getString("FriendUUID");
		}
		else
		{
			String s1 = tagCompund.getString("Friend");
			s = PreYggdrasilConverter.func_152719_a(s1);
		}

		if (s.length() > 0)
		{
			this.setFriendId(s);
			this.setSent(true);
		}
	}

	protected void setupSentAI() {}

	public Team getTeam()
	{
		if (this.isSent())
		{
			EntityLivingBase entitylivingbase = this.getFriendEntity();

			if (entitylivingbase != null)
			{
				return entitylivingbase.getTeam();
			}
		}

		return super.getTeam();
	}

	public boolean isOnSameTeam(EntityLivingBase otherEntity)
	{
		if (this.isSent())
		{
			EntityLivingBase entitylivingbase1 = this.getFriendEntity();

			if (otherEntity == entitylivingbase1)
			{
				return true;
			}

			if (entitylivingbase1 != null)
			{
				return entitylivingbase1.isOnSameTeam(otherEntity);
			}
		}

		return super.isOnSameTeam(otherEntity);
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.isSent() && (this.getFriend() == null || !this.getFriend().isEntityAlive()))
		{
			this.unsend();
		}

		super.onLivingUpdate();
	}

	@Override
	public void onDeath(DamageSource cause)
	{
		if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("showDeathMessages") && this.hasCustomName() && this.getFriendEntity() instanceof EntityPlayerMP)
		{
			((EntityPlayerMP)this.getFriendEntity()).addChatMessage(this.getCombatTracker().getDeathMessage());
		}

		super.onDeath(cause);
	}

	public Entity getFriend()
	{
		return this.getFriendEntity();
	}
}
