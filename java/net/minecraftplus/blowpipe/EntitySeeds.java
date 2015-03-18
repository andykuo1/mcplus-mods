package net.minecraftplus.blowpipe;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySeeds extends EntityThrowable
{
	private double damage = 0.6D;
	private float speed = 1F;
	private int knockbackStrength;
	private Item seedType;

	public EntitySeeds(World par1World)
	{
		super(par1World);
	}

	public EntitySeeds(World par1World, EntityLivingBase par2EntityLivingBase, float par3, Item par4)
	{
		super(par1World, par2EntityLivingBase);
		this.speed = par3 * 1.3F;

		this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + (double)par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
		this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		float f = 0.4F;
		this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
		this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
		this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * f);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.func_70182_d(), 1.0F);

		this.seedType = par4;
	}

	public EntitySeeds(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	@Override
	protected float func_70182_d()
	{
		return this.speed;
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
	{
		if (par1MovingObjectPosition.entityHit == null)
		{
			if (!this.worldObj.isRemote)
			{
				int i = par1MovingObjectPosition.blockX;
				int j = par1MovingObjectPosition.blockY;
				int k = par1MovingObjectPosition.blockZ;

				if (this.isBurning())
				{
					switch(par1MovingObjectPosition.sideHit)
					{
					case 0: j--;
					break;
					case 1: j++;
					break;
					case 2: k--;
					break;
					case 3: k++;
					break;
					case 4: i--;
					break;
					case 5: i++;
					break;
					}

					if (this.worldObj.isAirBlock(i, j, k))
					{
						if (Blocks.fire.canBlockStay(this.worldObj, i, j, k))
						{
							this.worldObj.setBlock(i, j, k, Blocks.fire);
							this.playSound("fire.ignite", 0.3F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.6F));
							this.setDead();
							return;
						}
					}
				}
				else if (this.getIsCritical() ? rand.nextInt(8) == 0 : rand.nextInt(16) == 0)
				{
					if (this.worldObj.getBlockMetadata(i, j, k) == 0 && this.worldObj.isAirBlock(i, j + 1, k))
					{
						if (Blocks.tallgrass.canBlockStay(this.worldObj, i, j + 1, k))
						{
							this.worldObj.setBlock(i, j + 1, k, Blocks.tallgrass, 1, 3);
							this.playSound("dig.grass", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
							this.setDead();
							return;
						}
						else if (Blocks.deadbush.canBlockStay(this.worldObj, i, j + 1, k))
						{
							this.worldObj.setBlock(i, j + 1, k, Blocks.deadbush);
							this.playSound("dig.sand", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
							this.setDead();
							return;
						}
					}
				}
			}

			if (!this.worldObj.isRemote)
			{
				EntityItem entityitem = new EntityItem(this.worldObj, this.posX, this.posY + 0.0D, this.posZ, new ItemStack(this.seedType, 1));
				entityitem.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
				entityitem.delayBeforeCanPickup = 5;
				this.worldObj.spawnEntityInWorld(entityitem);
			}

			this.setDead();
		}
		else if (par1MovingObjectPosition.entityHit.getEntityId() != this.getThrower().getEntityId())
		{
			//Hit an entity that is not the player
			float f0 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			int i0 = MathHelper.ceiling_double_int(f0 * this.damage);

			if (this.getIsCritical())
			{
				i0 += this.rand.nextInt(i0 / 2 + 2);
			}

			if (this.isBurning() && !(par1MovingObjectPosition.entityHit instanceof EntityEnderman))
			{
				par1MovingObjectPosition.entityHit.setFire(3);
			}

			if (this.knockbackStrength > 0)
			{
				double var0 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

				if (var0 > 0.0F)
				{
					par1MovingObjectPosition.entityHit.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)var0, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)var0);
				}
			}

			par1MovingObjectPosition.entityHit.attackEntityFrom(causeSeedDamage(this, this.getThrower()), i0);
			if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase)
			{
				((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 300));
			}
			this.worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
			this.setDead();
		}
	}

	public void setKnockbackStrength(int par1)
	{
		this.knockbackStrength = par1;
	}

	public void setDamage(double par1)
	{
		this.damage = par1;
	}

	public double getDamage()
	{
		return this.damage;
	}

	/**Whether the projectile has a stream of critical hit particles flying behind it.*/
	public void setIsCritical(boolean par1)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		this.dataWatcher.updateObject(16, par1 ? Byte.valueOf((byte)(b0 | 1)) : Byte.valueOf((byte)(b0 & -2)));
	}

	/**Whether the projectile has a stream of critical hit particles flying behind it.*/
	public boolean getIsCritical()
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		return (b0 & 1) != 0;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.worldObj.isRemote && !this.isDead)
		{
			if (this.isInWater())
			{
				for (int var1 = 0; var1 < 3; var1++)
				{
					float var2 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * var2, this.posY - this.motionY * var2, this.posZ - this.motionZ * var2, this.motionX, this.motionY, this.motionZ);
				}
			}

			if (this.isBurning())
			{
				for (int var1 = 0; var1 < 3; var1++)
				{
					float var2 = 0.25F;
					this.worldObj.spawnParticle("flame", this.posX - this.motionX * var2, this.posY - this.motionY * var2, this.posZ - this.motionZ * var2, this.motionX, this.motionY, this.motionZ);
				}
			}

			for (int var3 = 0; var3 < 2; var3++)
			{
				this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}

			if (this.getIsCritical())
			{
				for (int var4 = 0; var4 < 3; var4++)
				{
					this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double)var4 / 4.0D, this.posY + this.motionY * (double)var4 / 4.0D, this.posZ + this.motionZ * (double)var4 / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
				}
			}
		}
	}

	@Override
	public boolean canRenderOnFire()
	{
		return !this.isDead && this.isBurning();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setDouble("damage", this.damage);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		this.damage = par1NBTTagCompound.getDouble("damage");
	}

	public static DamageSource causeSeedDamage(EntitySeeds par1EntitySeeds, Entity par2Entity)
	{
		return new EntityDamageSourceIndirect("arrow", par1EntitySeeds, par2Entity).setProjectile();
	}

	public Item getSeedType()
	{
		return this.seedType;
	}
}
