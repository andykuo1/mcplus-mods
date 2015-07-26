package net.minecraftplus._api.minecraft.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;

public final class EntityLivingUtil
{
	private EntityLivingUtil() {}

	public static ItemStack getCurrentEquippedHelmet(EntityLivingBase parEntityLiving)
	{
		return parEntityLiving.getCurrentArmor(3);
	}

	public static ItemStack getCurrentEquippedChestplate(EntityLivingBase parEntityLiving)
	{
		return parEntityLiving.getCurrentArmor(2);
	}

	public static ItemStack getCurrentEquippedLeggings(EntityLivingBase parEntityLiving)
	{
		return parEntityLiving.getCurrentArmor(1);
	}

	public static ItemStack getCurrentEquippedBoots(EntityLivingBase parEntityLiving)
	{
		return parEntityLiving.getCurrentArmor(0);
	}

	public static EntityLivingBase replace(EntityLivingBase parEntityLiving, EntityLivingBase parEntity)
	{
		parEntityLiving.setDead();

		copy(parEntityLiving, parEntity);

		parEntityLiving.worldObj.spawnEntityInWorld(parEntity);
		parEntityLiving.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, parEntityLiving.posX, parEntityLiving.posY + (double)(parEntityLiving.height / 2.0F), parEntityLiving.posZ, 0.0D, 0.0D, 0.0D);

		return parEntity;
	}

	public static EntityLivingBase copy(EntityLivingBase parEntityLiving, EntityLivingBase parEntity)
	{
		parEntity.copyLocationAndAnglesFrom(parEntityLiving);
		parEntity.copyDataFromOld(parEntityLiving);
		parEntity.setHealth(parEntityLiving.getHealth());
		parEntity.renderYawOffset = parEntityLiving.renderYawOffset;
		parEntity.setCustomNameTag(parEntityLiving.getCustomNameTag());

		return parEntity;
	}
}
