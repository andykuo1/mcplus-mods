package net.minecraftplus.mcp_collision;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerCollision
{
	@SubscribeEvent
	public void onMinecartCollide(MinecartCollisionEvent par1Event)
	{
		float f = (float) (par1Event.minecart.motionX + par1Event.minecart.motionY + par1Event.minecart.motionZ) / 3F;//MAX is 16
		if (par1Event.collider instanceof EntityLivingBase && !(par1Event.collider instanceof EntityPlayer))
		{
			if (f > 0.3F)
			{
				((EntityLivingBase) par1Event.collider).attackEntityFrom(_Collision.crashMinecart, (int) (f * 8));
				par1Event.collider.addVelocity(par1Event.minecart.motionX * 2 * (par1Event.entity.worldObj.rand.nextFloat() + 1F), par1Event.minecart.motionY * 2 * (par1Event.entity.worldObj.rand.nextFloat() + 1F), par1Event.minecart.motionZ * 2 * (par1Event.entity.worldObj.rand.nextFloat() + 1F));
			}
		}
	}

	@SubscribeEvent
	public void onMinecartUpdate(MinecartUpdateEvent par1Event)
	{
		AxisAlignedBB box;
		if (par1Event.minecart.getCollisionHandler() != null)
		{
			box = par1Event.minecart.getCollisionHandler().getMinecartCollisionBox(par1Event.minecart);
		}
		else
		{
			box = par1Event.minecart.getBoundingBox().expand(0.6D, 0.0D, 0.6D);
		}

		List list = par1Event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(par1Event.minecart, box);

		if (list != null && !list.isEmpty())
		{
			for (int k = 0; k < list.size(); ++k)
			{
				Entity entity = (Entity)list.get(k);
				if (entity instanceof EntityPlayer && par1Event.minecart.riddenByEntity != entity)
				{
					float f = (float) (par1Event.minecart.motionX + par1Event.minecart.motionY + par1Event.minecart.motionZ) / 3F;//MAX is 16

					if (f > 0.3F)
					{
						System.out.println("P:" + f);
						((EntityLivingBase) entity).attackEntityFrom(_Collision.crashMinecart, (int) (f * 8));
						entity.addVelocity(par1Event.minecart.motionX * 2 * (par1Event.entity.worldObj.rand.nextFloat() + 1F), Math.abs(par1Event.minecart.motionY) * 2 + 1F * (par1Event.entity.worldObj.rand.nextFloat() + 1F), par1Event.minecart.motionZ * 2 * (par1Event.entity.worldObj.rand.nextFloat() + 1F));
					}
				}
			}
		}
	}
}
