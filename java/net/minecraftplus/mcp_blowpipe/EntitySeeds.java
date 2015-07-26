package net.minecraftplus.mcp_blowpipe;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftplus._api.dictionary.Sounds;
import net.minecraftplus._api.util.ArrayUtil;

public class EntitySeeds extends EntityThrowable
{
	private Item seedItem = ItemBlowpipe.PROJECTILES[0];
	private float speed = 1F;

	//Compare to: @EntityArrow
	private double damage = 1.0D;
	/**The amount of knockback the seeds apply when it hits a mob.*/
	private int knockbackStrength;

	public EntitySeeds(World parWorld)
	{
		super(parWorld);

		//Compare to: @EntityArrow
		this.renderDistanceWeight = 10.0D;
	}

	public EntitySeeds(World parWorld, double parX, double parY, double parZ)
	{
		super(parWorld, parX, parY, parZ);

		//Compare to: @EntityArrow
		this.renderDistanceWeight = 10.0D;
		this.setPosition(parX, parY, parZ);
	}

	public EntitySeeds(World parWorld, EntityLivingBase parThrowerEntity, float parSpeed, Item parItem)
	{
		super(parWorld, parThrowerEntity);

		//Compare to: @EntityArrow
		this.renderDistanceWeight = 10.0D;

		this.speed = parSpeed * 1.3F;
		this.seedItem = parItem;

		//Compare To: @EntityThrowable
		this.setLocationAndAngles(parThrowerEntity.posX, parThrowerEntity.posY + (double)parThrowerEntity.getEyeHeight(), parThrowerEntity.posZ, parThrowerEntity.rotationYaw, parThrowerEntity.rotationPitch);
		float f = 0.4F;
		this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
		this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
		this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.getInaccuracy()) / 180.0F * (float)Math.PI) * f);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.getVelocity(), 1.0F);
	}

	@Override
	protected void entityInit()
	{
		//Compare to: @EntityArrow
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	@Override
	public float getVelocity()
	{
		return this.speed;
	}

	@Override
	protected void onImpact(MovingObjectPosition parMovingObject)
	{
		if (parMovingObject.entityHit == null)
		{
			if (!this.worldObj.isRemote)
			{
				BlockPos pos = parMovingObject.getBlockPos();

				if (this.isBurning())
				{
					pos = pos.add(parMovingObject.sideHit.getDirectionVec());

					//Compare to: @ItemFlintAndSteel
					if (this.worldObj.isAirBlock(pos))
					{
						this.worldObj.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, Sounds.FIRE_IGNITE, 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
						this.worldObj.setBlockState(pos, Blocks.fire.getDefaultState());
						this.setDead();
					}
				}
				else if (this.getIsCritical() ? rand.nextInt(8) == 0 : rand.nextInt(16) == 0)
				{
					//Compare to: @ItemSeedFood
					if (this.worldObj.isAirBlock(pos.up()))
					{
						if (this.worldObj.getBlockState(pos).getBlock().canSustainPlant(this.worldObj, pos, EnumFacing.UP, Blocks.tallgrass))
						{
							this.worldObj.setBlockState(pos.up(), Blocks.tallgrass.getStateFromMeta(1));
							this.playSound(Sounds.DIG_GRASS, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
							this.setDead();
						}
						else if (this.worldObj.getBlockState(pos).getBlock().canSustainPlant(this.worldObj, pos, EnumFacing.UP, Blocks.deadbush))
						{
							this.worldObj.setBlockState(pos.up(), Blocks.deadbush.getDefaultState());
							this.playSound(Sounds.DIG_GRASS, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
							this.setDead();
						}
					}
				}
			}

			if (!this.worldObj.isRemote)
			{
				EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(this.seedItem, 1));
				entityitem.setPickupDelay(5);
				this.worldObj.spawnEntityInWorld(entityitem);
			}

			this.setDead();
		}
		else if (parMovingObject.entityHit.getEntityId() != this.getThrower().getEntityId())
		{
			//Hit an entity that is not the player
			float f0 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			int i0 = MathHelper.ceiling_double_int(f0 * this.damage);

			if (this.getIsCritical())
			{
				i0 += this.rand.nextInt(i0 / 2 + 2);
			}

			if (this.isBurning() && !(parMovingObject.entityHit instanceof EntityEnderman))
			{
				parMovingObject.entityHit.setFire(3);
			}

			if (this.knockbackStrength > 0)
			{
				double var0 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

				if (var0 > 0.0F)
				{
					parMovingObject.entityHit.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)var0, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)var0);
				}
			}

			parMovingObject.entityHit.attackEntityFrom(causeSeedDamage(this, this.getThrower()), i0);
			this.worldObj.playSoundAtEntity(this, Sounds.RANODM_BOWHIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
			this.setDead();
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.worldObj.isRemote && !this.isDead)
		{
			if (this.rand.nextBoolean())
			{
				float f = 0.25F;
				if (this.isInWater())
				{
					this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * f, this.posY - this.motionY * f, this.posZ - this.motionZ * f, this.motionX, this.motionY, this.motionZ);
				}

				if (this.isBurning())
				{
					this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX - this.motionX * f, this.posY - this.motionY * f, this.posZ - this.motionZ * f, this.motionX, this.motionY, this.motionZ);
				}

				this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);

				if (this.getIsCritical())
				{
					int i = this.rand.nextInt(3);
					this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * (double) i / 4.0D, this.posY + this.motionY * (double) i / 4.0D, this.posZ + this.motionZ * (double) i / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
				}
			}
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound parNBTTagCompound)
	{
		parNBTTagCompound.setByte("seedtype", (byte) ArrayUtil.indexOf(ItemBlowpipe.PROJECTILES, this.seedItem));

		//Compare to: @EntityArrow
		parNBTTagCompound.setDouble("damage", this.damage);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound parNBTTagCompound)
	{
		this.seedItem = ItemBlowpipe.PROJECTILES[parNBTTagCompound.getByte("seedtype")];

		//Compare to: @EntityArrow
		if (parNBTTagCompound.hasKey("damage", 99))
		{
			this.damage = parNBTTagCompound.getDouble("damage");
		}
	}

	public void setDamage(double parDamage)
	{
		//Compare to: @EntityArrow
		this.damage = parDamage;
	}

	public double getDamage()
	{
		//Compare to: @EntityArrow
		return this.damage;
	}

	/**Sets the amount of knockback the seeds applies when it hits a mob.*/
	public void setKnockbackStrength(int parKnockback)
	{
		//Compare to: @EntityArrow
		this.knockbackStrength = parKnockback;
	}

	/**Whether the seeds has a stream of critical hit particles flying behind it.*/
	public void setIsCritical(boolean parCrit)
	{
		//Compare to: @EntityArrow
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (parCrit)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
		}
	}

	/**Whether the seeds has a stream of critical hit particles flying behind it.*/
	public boolean getIsCritical()
	{
		//Compare to: @EntityArrow
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		return (b0 & 1) != 0;
	}

	//Compare to: @DamageSource
	public static DamageSource causeSeedDamage(EntitySeeds parEntitySeeds, Entity parEntity)
	{
		return new EntityDamageSourceIndirect("arrow", parEntitySeeds, parEntity).setProjectile();
	}

	public Item getSeedItem()
	{
		return this.seedItem;
	}
}
