package net.minecraftplus.shatter;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventArrowSpawnEvent
{
	private final Random rand = new Random();

	@SubscribeEvent
	public void onArrowLoose(ArrowLooseEvent par1Event)
	{
		int j = par1Event.charge;
		boolean flag = par1Event.entityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1Event.bow) > 0;

		if (flag || par1Event.entityPlayer.inventory.hasItem(Items.arrow))
		{
			float f = (float)j / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;

			if ((double)f < 0.1D)
			{
				return;
			}

			if (f > 1.0F)
			{
				f = 1.0F;
			}

			EntityArrow entityarrow = new EntityArrowShatter(par1Event.entity.worldObj, par1Event.entityPlayer, f * 2.0F);

			if (f == 1.0F)
			{
				entityarrow.setIsCritical(true);
			}

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1Event.bow);

			if (k > 0)
			{
				entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
			}

			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1Event.bow);

			if (l > 0)
			{
				entityarrow.setKnockbackStrength(l);
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1Event.bow) > 0)
			{
				entityarrow.setFire(100);
			}

			par1Event.bow.damageItem(1, par1Event.entityPlayer);
			par1Event.entity.worldObj.playSoundAtEntity(par1Event.entityPlayer, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

			if (flag)
			{
				entityarrow.canBePickedUp = 2;
			}
			else
			{
				par1Event.entityPlayer.inventory.consumeInventoryItem(Items.arrow);
			}

			if (!par1Event.entity.worldObj.isRemote)
			{
				par1Event.entity.worldObj.spawnEntityInWorld(entityarrow);
			}
		}

		par1Event.setCanceled(true);
	}
}
