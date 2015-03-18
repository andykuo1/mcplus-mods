package net.minecraftplus.pigeon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityPigeonFlyable extends EntityPigeonBase
{
	public double targetX;
	public double targetY;
	public double targetZ;

	/** Force selecting a new flight target at next tick if set to true. */
	public boolean forceNewTarget;
	protected Entity target;

	public EntityPigeonFlyable(World parWorld)
	{
		super(parWorld);
		this.targetY = -1;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.isInWater())
		{
			this.motionY += 0.01F;
		}
		
		if (!this.getIsFlying())
		{
			super.onLivingUpdate();
			this.onWalkingUpdate();
		}
		else
		{
			if (this.isSitting())
			{
				this.setIsFlying(false);
				return;
			}

			this.onFlyingUpdate();
		}
	}

	private void onWalkingUpdate()
	{
		if (!this.isTamed())
		{
			EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 5);
			if (player != null && player.getDistanceSqToEntity(this) < 25.0D)
			{
				ItemStack itemstack = player.getCurrentEquippedItem();
				if (!this.isBreedingItem(itemstack) && (itemstack == null || itemstack.getItem() == null || itemstack.getItem() != Items.melon_seeds))
				{
					this.fly();
					return;
				}
			}
			else if (this.rand.nextInt(28) == 0)
			{
				this.fly();
			}
		}
		else
		{
			if (this.getOwner() != null && this.getOwner().posY - this.posY > 5 && this.rand.nextInt(8) == 0)
			{
				this.fly();
			}
		}
	}

	private void onFlyingUpdate()
	{
		if (this.targetY < 0)
		{
			this.targetY = this.getSurfaceHeight(this.worldObj, this.posX, this.posY, this.posZ, 10) + 10;
		}

		this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);

		double d0;
		double d1;
		double d2;
		double d10;
		float f12;

		if (this.worldObj.isRemote)
		{
			if (this.newPosRotationIncrements > 0)
			{
				d10 = this.posX + (this.newPosX - this.posX) / (double)this.newPosRotationIncrements;
				d0 = this.posY + (this.newPosY - this.posY) / (double)this.newPosRotationIncrements;
				d1 = this.posZ + (this.newPosZ - this.posZ) / (double)this.newPosRotationIncrements;
				d2 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - (double)this.rotationYaw);
				this.rotationYaw = (float)((double)this.rotationYaw + d2 / (double)this.newPosRotationIncrements);
				this.rotationPitch = (float)((double)this.rotationPitch + (this.newRotationPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
				--this.newPosRotationIncrements;
				this.setPosition(d10, d0, d1);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			}
		}
		else
		{
			d10 = this.targetX - this.posX;
			d0 = this.targetY - this.posY;
			d1 = this.targetZ - this.posZ;
			d2 = d10 * d10 + d0 * d0 + d1 * d1;

			if (this.target != null)
			{
				this.targetX = this.target.posX;
				this.targetZ = this.target.posZ;
				double d3 = this.targetX - this.posX;
				double d5 = this.targetZ - this.posZ;
				double d7 = Math.sqrt(d3 * d3 + d5 * d5);
				double d8 = 0.4000000059604645D + d7 / 80.0D - 1.0D;

				if (d8 > 10.0D)
				{
					d8 = 10.0D;
				}

				this.targetY = this.target.boundingBox.minY + d8;
			}
			else
			{
				this.targetX += this.rand.nextGaussian() * 2.0D;
				this.targetZ += this.rand.nextGaussian() * 2.0D;
			}

			//Keep level with Surface
			if (this.rand.nextBoolean())
			{
				double height = this.getSurfaceHeight(this.worldObj, this.posX, this.posY, this.posZ, 10);

				if (this.posY - height < 2)
				{
					this.setIsFlying(false);
				}

				if (this.posY - height < 4 && this.rand.nextBoolean())
				{
					this.setIsFlying(false);
					return;
				}

				if (this.posY < height + 8)
				{
					this.targetY = height + 10;
				}
				else if (this.posY > height + 15)
				{
					this.targetY = height + 12;
				}
			}

			if (this.forceNewTarget || (!this.isTamed() && d2 < 100.0D) || d2 > 22500.0D || this.isCollidedHorizontally || this.isCollidedVertically)
			{
				this.setNewTarget();
			}

			if (this.isCollidedVertically && this.onGround)
			{
				this.land();
				return;
			}

			d0 /= (double)MathHelper.sqrt_double(d10 * d10 + d1 * d1);
			f12 = 0.6F;

			if (d0 < (double)(-f12))
			{
				d0 = (double)(-f12);
			}

			if (d0 > (double)f12)
			{
				d0 = (double)f12;
			}

			this.motionY += d0 * 0.10000000149011612D;
			this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
			double d4 = 180.0D - Math.atan2(d10, d1) * 180.0D / Math.PI;
			double d6 = MathHelper.wrapAngleTo180_double(d4 - (double)this.rotationYaw);

			if (d6 > 50.0D)
			{
				d6 = 50.0D;
			}

			if (d6 < -50.0D)
			{
				d6 = -50.0D;
			}

			Vec3 vec3 = Vec3.createVectorHelper(this.targetX - this.posX, this.targetY - this.posY, this.targetZ - this.posZ).normalize();
			Vec3 vec32 = Vec3.createVectorHelper((double)MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F), this.motionY, (double)(-MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F))).normalize();
			float f5 = (float)(vec32.dotProduct(vec3) + 0.5D) / 1.5F;

			if (f5 < 0.0F)
			{
				f5 = 0.0F;
			}

			this.randomYawVelocity *= 0.8F;
			float f6 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F;
			double d9 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0D + 1.0D;

			if (d9 > 40.0D)
			{
				d9 = 40.0D;
			}

			this.randomYawVelocity = (float)((double)this.randomYawVelocity + d6 * (0.699999988079071D / d9 / (double)f6));
			this.rotationYaw += this.randomYawVelocity * 0.1F;
			float f7 = (float)(2.0D / (d9 + 1.0D));
			float f8 = 0.015F;//Fly Speed
			this.moveFlying(0.0F, -1F, f8 * (f5 * f7 + (1.0F - f7)));

			this.moveEntity(this.motionX, this.motionY, this.motionZ);

			Vec3 vec31 = Vec3.createVectorHelper(this.motionX, this.motionY, this.motionZ).normalize();
			float f9 = (float)(vec31.dotProduct(vec32) + 1.0D) / 2.0F;
			f9 = 0.8F + 0.15F * f9;
			this.motionX *= (double)f9;
			this.motionZ *= (double)f9;
			this.motionY *= 0.9100000262260437D;
		}
	}

	private double getSurfaceHeight(World parWorld, double parPosX, double parPosY, double parPosZ, int parMaxDistance)
	{
		for(int i = 0; i < parMaxDistance; i++)
		{
			if (!parWorld.isAirBlock((int)parPosX, (int)parPosY - i, (int)parPosZ))
			{
				return parPosY - i;
			}
		}

		return parPosY - parMaxDistance;
	}

	private void fly()
	{
		this.setIsFlying(true);
		this.setSitting(false);
		this.jump();
	}

	private void land()
	{
		this.setIsFlying(false);
	}

	/**
	 * Sets a new target for the flight AI. It can be a random coordinate or a nearby player.
	 */
	private void setNewTarget()
	{
		this.forceNewTarget = false;

		if (this.rand.nextInt(2) == 0 && !this.worldObj.playerEntities.isEmpty())
		{
			if (this.isTamed())
			{
				this.target = this.getOwner();
				return;
			}
			else
			{
				EntityPlayer player = (EntityPlayer) this.worldObj.playerEntities.get(this.rand.nextInt(this.worldObj.playerEntities.size()));

				ItemStack itemstack = player.getCurrentEquippedItem();
				if (itemstack != null)
				{
					if (itemstack.getItem() == Items.melon || itemstack.getItem() == Items.melon_seeds)
					{
						this.target = player;
						return;
					}
				}
			}
		}

		boolean flag = false;

		do
		{
			this.targetX = this.posX;
			double newheight = this.getSurfaceHeight(this.worldObj, this.posX, this.posY, this.posZ, 10);
			this.targetY = (double)(newheight + 10F + this.rand.nextFloat() * 50.0F);
			this.targetZ = this.posZ;
			this.targetX += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
			this.targetZ += (double)(this.rand.nextFloat() * 120.0F - 60.0F);
			double d0 = this.posX - this.targetX;
			double d1 = this.posY - this.targetY;
			double d2 = this.posZ - this.targetZ;
			flag = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
		}
		while (!flag);

		this.target = null;
	}
}